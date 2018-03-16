package com.firstBot.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.firstBot.entity.Film;
import com.firstBot.entity.Genre;
import com.firstBot.entity.User;
import com.firstBot.service.FilmService;
import com.firstBot.service.GenreService;
import com.firstBot.service.QuickReplyService;
import com.firstBot.service.UserService;
import com.firstBots.model.Attachment;
import com.firstBots.model.AttachmentType;
import com.firstBots.model.Button;
import com.firstBots.model.ButtonType;
import com.firstBots.model.ButtonUrl;
import com.firstBots.model.Element;
import com.firstBots.model.GenericTemplate;
import com.firstBots.model.MessageGT;
import com.firstBots.model.Payload;
import com.firstBots.model.QuickReplyType;
import com.firstBots.model.Recipient;
import com.firstBots.model.TemplateType;
import com.firstBots.model.quickReply.MessageQR;
import com.firstBots.model.quickReply.QuickReply;
import com.firstBots.model.quickReply.TemplateQR;
import com.firstBots.model.simpleMessage.MessageOut;
import com.firstBots.model.simpleMessage.OutputtingMessage;

@Service
public class QuickReplyServiceImpl implements QuickReplyService{

	@Autowired
	private UserService userService; 

	@Autowired
	private GenreService genreService;
	
	@Autowired
	private FilmService filmService;

	@Value("${access}")
	String access;
	
	@Value("${url.bot}")
	String urlbot;
	
	@Value("${qr.cancel.title}")
	String cancelTitle;

	@Value("${qr.cancel.payload}")
	String cancelPayload;

	@Value("${qr.done.title}")
	String doneTitle;

	@Value("${qr.done.payload}")
	String donePayload;

	@Value("${qr.back.title}")
	String backTitle;

	@Value("${qr.back.payload}")
	String backPayload;

	@Value("${trailer}")
	String trailer;
	
	@Override
	public void doIt(User user, QuickReply quickReply) {
		
		String incomePayload = quickReply.getPayload();
		
		if (incomePayload.equals(donePayload)) {

			List<QuickReply> list = new ArrayList<QuickReply>();
			int tempYear = Calendar.getInstance().get(Calendar.YEAR);
			for (int k = 3; k >=0; k--) {
				String years = "-" + (tempYear-5*k);
				years = (tempYear-5*(k+1))+years;				
				list.add(new QuickReply(QuickReplyType.text, years, years, ""));
			}
			if (user.getFromYear() != 0 || user.getToYear() != 0) {
//				list.add(new QuickReply(QuickReplyType.text, doneTitle, donePayload, ""));
//				list.add(new QuickReply(QuickReplyType.text, cancelTitle, cancelPayload, ""));
			}
			list.add(new QuickReply(QuickReplyType.text, backTitle, backPayload, ""));
			MessageQR mes = new MessageQR("Choose years please.", list);
			String userURL = userService.getUserUrl(user);
			RestTemplate rt = new RestTemplate();
			TemplateQR template = new TemplateQR(new Recipient(user.getMessengerUserId()), mes);
			rt.postForObject(urlbot+access, template, String.class);
		
		} else if (incomePayload.equals(cancelPayload)) {
			user.setGenres(new ArrayList<Genre>());
			user.setFromYear(0);
			user.setToYear(0);
			userService.save(user);
	//		
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
	//
		}else if(incomePayload.equals(backPayload)) {
			
		} 
			else if(incomePayload.contains("-")) {
			String years[] = incomePayload.split("-");
			user.setFromYear(Integer.parseInt(years[0]));
			user.setToYear(Integer.parseInt(years[1]));
			userService.save(user);
			
			List<Film> offeredFilms = filmService.getOfferedFilms(user.getMessengerUserId());
			List<Element> elementList = new ArrayList<Element>();
			
			for(int i = 0; i < offeredFilms.size(); i++) {
				Film film = offeredFilms.get(i);
				List<Button> buttons = new ArrayList<Button>();
				
					ButtonUrl buttonUrl = new ButtonUrl(ButtonType.web_url, film.getTrailerUrl(), trailer);
					buttons.add(buttonUrl);

				elementList.add(new Element(film.getName(), film.getFilmUrl(), film.getDescription(), buttons));
			}
			

			RestTemplate rt = new RestTemplate();
			if(elementList.size()>0) {
			GenericTemplate gt = new GenericTemplate(new Recipient(user.getMessengerUserId()),
					new MessageGT(new Attachment(AttachmentType.template, new Payload(TemplateType.generic, elementList))));
			rt.postForObject(urlbot+access, gt, String.class);
			}else {
				OutputtingMessage template = new OutputtingMessage("", new Recipient(user.getMessengerUserId()), new MessageOut("Oooops... There are no films due to your parameters."));
				rt.postForObject(urlbot+access, template, String.class);
			}
		}
			else {
			Genre possibleGenre = genreService.findOne(Integer.parseInt(incomePayload));
			List<Genre> userGenres = user.getGenres();
			userGenres.add(possibleGenre);
			user.setGenres(userGenres);
			userService.save(user);
			
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
		
	}

}
