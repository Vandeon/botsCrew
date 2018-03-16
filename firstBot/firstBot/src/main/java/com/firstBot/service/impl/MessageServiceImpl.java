package com.firstBot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.firstBot.entity.Genre;
import com.firstBot.entity.User;
import com.firstBot.service.FilmService;
import com.firstBot.service.GenreService;
import com.firstBot.service.MessageService;
import com.firstBot.service.QuickReplyService;
import com.firstBot.service.UserService;
import com.firstBots.model.IncommingMessage;
import com.firstBots.model.QuickReplyType;
import com.firstBots.model.Recipient;
import com.firstBots.model.quickReply.MessageQR;
import com.firstBots.model.quickReply.QuickReply;
import com.firstBots.model.quickReply.TemplateQR;
import com.firstBots.model.quickReply.UserInfo;
import com.firstBots.model.simpleMessage.Entry;
import com.firstBots.model.simpleMessage.MessageOut;
import com.firstBots.model.simpleMessage.Messaging;
import com.firstBots.model.simpleMessage.OutputtingMessage;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private UserService userService;

	@Autowired
	private FilmService filmService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private QuickReplyService quickReplyService;
	
	@Value("${access}")
	String access;
	
	@Value("${url.bot}")
	String urlbot;
	
	@Value("${secret}")
	String secret;

	@Value("${qr.cancel.title}")
	String cancelTitle;

	@Value("${qr.cancel.payload}")
	String cancelPayload;

	@Value("${qr.done.title}")
	String doneTitle;

	@Value("${qr.done.payload}")
	String donePayload;
	
	public void getMessage(IncommingMessage message) {

		UserInfo userInfo = getUserInfo(message);
		User user = checkUser(userInfo);

		List<Entry> entryList = message.getEntry();
		for (int i = 0; i < entryList.size(); i++) {
			List<Messaging> messagingList = entryList.get(i).getMessaging();
			for (int j = 0; j < messagingList.size(); j++) {
				QuickReply quickReply = messagingList.get(j).getMessage().getQuick_reply();

				if (quickReply != null) {
					quickReplyService.doIt(user, quickReply);
//					sendQuickReply(user);
				} else {
					sendTextMessage(user);
					sendQuickReply(user);
				}
			}
		}

	}

	private User checkUser(UserInfo userInfo) {

		String messengerUserId = userInfo.getId();
		String userName = userInfo.getFirst_name();
		User user = userService.findByMessengerUserId(messengerUserId);
		if (user == null) {
			user = new User();
			user.setMessengerUserId(messengerUserId);
			user.setName(userName);
			userService.save(user);
		}
		return user;
	}

	private UserInfo getUserInfo(IncommingMessage message) {
		String url1 = "https://graph.facebook.com/v2.6/";
		String url2 = "?fields=first_name&access_token=";
		String messengerUserId = message.getEntry().get(0).getMessaging().get(0).getSender().getId();
		String userURL = url1 + messengerUserId + url2;
		RestTemplate rt = new RestTemplate();
		UserInfo userInfo = rt.getForObject(userURL+access, UserInfo.class);
		return userInfo;
	}

	private void sendQuickReply(User user) {
		List<Genre> offeredGenres = genreService.findByNameNotIn(user.getMessengerUserId()); // Витягує з бази всі жанри, які ще не обрав юзер
		List<QuickReply> list = new ArrayList<QuickReply>();
		for (int k = 0; k < offeredGenres.size(); k++) {
			Genre tempGenre = offeredGenres.get(k);
			String genreName = tempGenre.getName();
			String genreId = "" + tempGenre.getId();
			list.add(new QuickReply(QuickReplyType.text, genreName, genreId, ""));
		}
		if (user.getGenres().size() > 0) {
			list.add(new QuickReply(QuickReplyType.text, doneTitle, donePayload, ""));
			list.add(new QuickReply(QuickReplyType.text, cancelTitle, cancelPayload, ""));
		}
		MessageQR mes = new MessageQR("Choose the genre please.", list);
		String userURL = userService.getUserUrl(user);
		RestTemplate rt = new RestTemplate();
		TemplateQR template = new TemplateQR(new Recipient(user.getMessengerUserId()), mes);
		rt.postForObject(urlbot+access, template, String.class);
	}
	
	private void sendTextMessage(User user) {
		String greetings = "Hi, " + user.getName()
		+ "! Nice to meet you.\r\nMy name is FilmoZavr. I am bot that helps people to find interesting and cool films. What film genre do you like?";
		RestTemplate rt = new RestTemplate();
		String userURL = userService.getUserUrl(user);
		OutputtingMessage template = new OutputtingMessage("", new Recipient(user.getMessengerUserId()), new MessageOut(greetings));
		rt.postForObject(urlbot+access, template, String.class);
	}
}

// SYSO JSON
// ObjectMapper om = new ObjectMapper();
// try {
// System.out.println(om.writeValueAsString(template));
// } catch (JsonProcessingException e) {
// e.printStackTrace();
// }
