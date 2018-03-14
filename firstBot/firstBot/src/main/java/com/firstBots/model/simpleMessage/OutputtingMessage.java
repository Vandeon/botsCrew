package com.firstBots.model.simpleMessage;

import com.firstBots.model.Recipient;

public class OutputtingMessage {

	private String type;
	
	private Recipient recipient;
	
	private MessageOut message;

	public OutputtingMessage() {}
	
	public OutputtingMessage(String type, Recipient recipient, MessageOut message) {
		super();
		this.type = type;
		this.recipient = recipient;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public MessageOut getMessage() {
		return message;
	}

	public void setMessage(MessageOut message) {
		this.message = message;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	

	@Override
	public String toString() {
		return "OutputtingMessage [type=" + type + ", recipient=" + recipient + ", message=" + message + "]";
	}
	
}
