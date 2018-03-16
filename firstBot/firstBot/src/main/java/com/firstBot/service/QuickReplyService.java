package com.firstBot.service;

import com.firstBot.entity.User;
import com.firstBots.model.quickReply.QuickReply;

public interface QuickReplyService {

	void doIt(User user, QuickReply quickReply);
	
}
