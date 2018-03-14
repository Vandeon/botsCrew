package com.firstBots.model.quickReply;

import com.firstBots.model.Recipient;

public class TemplateQR {

	private Recipient recipient;
	
	private MessageQR message;

	public TemplateQR(Recipient recipient, MessageQR message) {
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

	public MessageQR getMessage() {
		return message;
	}

	public void setMessage(MessageQR message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TemplateQR [recipient=" + recipient + ", message=" + message + "]";
	}
	
}
