package com.firstBots.model;

public class MessageGT {

	private Attachment attachment;

	public MessageGT(Attachment attachment) {
		super();
		this.attachment = attachment;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	@Override
	public String toString() {
		return "MessageGT [attachment=" + attachment + "]";
	}	
	
}
