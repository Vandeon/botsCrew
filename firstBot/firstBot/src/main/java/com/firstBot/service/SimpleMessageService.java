package com.firstBot.service;

import com.firstBot.entity.User;
import com.firstBots.model.IncommingMessage;

public interface SimpleMessageService {

	void introduse(User user, IncommingMessage message);
	
}
