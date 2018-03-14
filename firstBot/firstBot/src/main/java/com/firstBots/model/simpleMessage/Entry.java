package com.firstBots.model.simpleMessage;

import java.util.List;

public class Entry {

	private String id;
	
	private long time;
	
	private List<Messaging> messaging;

	public Entry() {}
	
	public Entry(String id, int time, List<Messaging> messaging) {
		super();
		this.id = id;
		this.time = time;
		this.messaging = messaging;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public List<Messaging> getMessaging() {
		return messaging;
	}

	public void setMessaging(List<Messaging> messaging) {
		this.messaging = messaging;
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", time=" + time + ", messaging=" + messaging + "]";
	}
	
	
	
}
