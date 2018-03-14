package com.firstBots.model.simpleMessage;

public class MessageOut {

	private String text;

	public MessageOut() {}
	
	public MessageOut(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "MessageOut [text=" + text + "]";
	}
	
	
	
}
