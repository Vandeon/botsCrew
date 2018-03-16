package com.firstBots.model;

public class GenericTemplate {

	private Recipient recipient;
	
	private MessageGT message;

	public GenericTemplate() {}
	
	public GenericTemplate(Recipient recipient, MessageGT message) {
		super();
		this.recipient = recipient;
		this.message = message;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public MessageGT getMessage() {
		return message;
	}

	public void setMessage(MessageGT message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "GenericTemplate [recipient=" + recipient + ", message=" + message + "]";
	}

	
	
}
