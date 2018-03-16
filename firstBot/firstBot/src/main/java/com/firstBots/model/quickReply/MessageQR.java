package com.firstBots.model.quickReply;

import java.util.List;

public class MessageQR {

	private String text;
	
	private List<QuickReply> quick_replies;

	public MessageQR(String text, List<QuickReply> quick_replies) {
		super();
		this.text = text;
		this.quick_replies = quick_replies;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<QuickReply> getQuick_replies() {
		return quick_replies;
	}

	public void setQuick_replies(List<QuickReply> quick_replies) {
		this.quick_replies = quick_replies;
	}

	@Override
	public String toString() {
		return "MessageQR [text=" + text + ", quick_replies=" + quick_replies + "]";
	}
	
}
