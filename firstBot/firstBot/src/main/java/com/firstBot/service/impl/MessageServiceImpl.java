package com.firstBot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.firstBot.entity.Genre;
import com.firstBot.entity.User;
import com.firstBot.service.FilmService;
import com.firstBot.service.GenreService;
import com.firstBot.service.MessageService;
import com.firstBot.service.UserService;
import com.firstBots.model.IncommingMessage;
import com.firstBots.model.Recipient;
import com.firstBots.model.quickReply.MessageQR;
import com.firstBots.model.quickReply.QuickReplay;
import com.firstBots.model.quickReply.TemplateQR;
import com.firstBots.model.quickReply.UserInfo;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	UserService userService;

	@Autowired
	FilmService filmService;

	@Autowired
	GenreService genreService;

	@Value("${access}")
	private String secret;

	@Value("${boturl}")
	private String url;

	public void getMessage(IncommingMessage message) {

		String incomeText = message.getEntry().get(0).getMessaging().get(0).getMessage().getText();
		String messengerUserId = message.getEntry().get(0).getMessaging().get(0).getSender().getId();
		String url1 = "https://graph.facebook.com/v2.6/";
		String url2 = "?fields=first_name&access_token=";

		RestTemplate rt = new RestTemplate();
		UserInfo userInfo = rt.getForObject(url1 + messengerUserId + url2 + secret, UserInfo.class);
		String userName = userInfo.getFirst_name();
		String greetings = "Hi, " + userName
				+ "! Nice to meet you.\r\nMy name is FilmoZavr. I am bot that helps people to find interesting and cool films. What film genre do you like?";

		User user = checkUser(messengerUserId, userName);
		System.out.println("hi");
		List<Genre> offeredGenres = genreService.findByNameNotIn(user.getMessengerUserId());
		System.out.println(offeredGenres);
		List<Genre> allGenres = genreService.findAll();
//		List<String> userGenresNames = user.getGenres();

		// !!!

//		List<Genre> offeredGenres = new ArrayList<Genre>();
//		if (userGenres != null) {
//			for (int i = 0; i < userGenres.size(); i++) {
//				userGenresNames.add(userGenres.get(i).getName());
//			}
//			offeredGenres = (List<Genre>) genreService.findByNameNotIn(userGenresNames);
//			System.out.println(offeredGenres);
//		} else {
//			offeredGenres = genreService.findAll();
//		}

		System.out.println(incomeText);
		// rt.postForObject(url+secret, new OutputtingMessage("RESPONSE", new
		// Recipient(message.getEntry().get(0).getMessaging().get(0).getSender().getId()),
		// new MessageOut("Hello bro!")), String.class);
		// OutputtingMessage om = new OutputtingMessage("RESPONSE", new
		// Recipient(message.getEntry().get(0).getMessaging().get(0).getSender().getId()),
		// new MessageOut("Hello bro!"));
		// System.out.println(om);

		// quickreplay

		List<QuickReplay> list = new ArrayList<QuickReplay>();
		list.add(new QuickReplay("text", "Comedy", "", ""));
		list.add(new QuickReplay("text", "Crime", "", ""));
		list.add(new QuickReplay("text", "Drama", "", ""));
		list.add(new QuickReplay("text", "Fantasy", "", ""));
		list.add(new QuickReplay("text", "Horror", "", ""));
		list.add(new QuickReplay("text", "Action", "", ""));
		list.add(new QuickReplay("text", "Advanture", "", ""));
		list.add(new QuickReplay("text", "It's all", "", ""));
		list.add(new QuickReplay("text", "Chose again", "", ""));
		MessageQR mes = new MessageQR(greetings, list);

		TemplateQR template = new TemplateQR(
				new Recipient(message.getEntry().get(0).getMessaging().get(0).getSender().getId()), mes);
		rt.postForObject(url + secret, template, String.class);

		// SYSO JSON
		// ObjectMapper om = new ObjectMapper();
		// try {
		// System.out.println(om.writeValueAsString(template));
		// } catch (JsonProcessingException e) {
		// e.printStackTrace();
		// }

	}

	private User checkUser(String messengerUserId, String userName) {
		User user = userService.findByMessengerUserId(messengerUserId);
		if (user == null) {
			user = new User();
			user.setMessengerUserId(messengerUserId);
			user.setName(userName);
			userService.save(user);
		}
		return user;
	}

}
