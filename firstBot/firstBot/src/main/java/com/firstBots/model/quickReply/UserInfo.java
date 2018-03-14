package com.firstBots.model.quickReply;

public class UserInfo {

	private String first_name;
	
	private String id;

	public UserInfo() {}
	
	public UserInfo(String first_name, String id) {
		super();
		this.first_name = first_name;
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserInfo [first_name=" + first_name + ", id=" + id + "]";
	}
	
	
	
}
