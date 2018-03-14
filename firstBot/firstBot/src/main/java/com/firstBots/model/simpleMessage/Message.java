package com.firstBots.model.simpleMessage;

public class Message {

	private String mid;
	
	private int seq;
	
	private String text;

	public Message() {}
	
	public Message(String mid, int seq, String text) {
		super();
		this.mid = mid;
		this.seq = seq;
		this.text = text;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Message [mid=" + mid + ", seq=" + seq + ", text=" + text + "]";
	}	
	
}
