package com.firstBots.model.quickReply;

import java.util.List;

public class MessageQR {

	private String text;
	
	private List<QuickReplay> quick_replies;

	public MessageQR(String text, List<QuickReplay> quick_replies) {
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

	public List<QuickReplay> getQuick_replies() {
		return quick_replies;
	}

	public void setQuick_replies(List<QuickReplay> quick_replies) {
		this.quick_replies = quick_replies;
	}

	@Override
	public String toString() {
		return "MessageQR [text=" + text + ", quick_replies=" + quick_replies + "]";
	}
	
}
