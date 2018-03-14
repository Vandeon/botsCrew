package com.firstBots.model.simpleMessage;

import com.firstBots.model.Recipient;
import com.firstBots.model.Sender;

public class Messaging {

	private Sender sender;
	
	private Recipient recipient;
	
	private long timestamp;
	
	private Message message;

	public Messaging() {}
	
	public Messaging(Sender sender, Recipient recipient, int timestamp, Message message) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.timestamp = timestamp;
		this.message = message;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Messaging [sender=" + sender + ", recipient=" + recipient + ", message=" + message + "]";
	}
	
	
	
}
