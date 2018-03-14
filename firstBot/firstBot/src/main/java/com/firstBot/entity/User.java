package com.firstBot.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="userr")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String messengerUserId;
	
	private String name;
	
	@ManyToMany
	@JoinTable(name="user_genre", joinColumns = @JoinColumn(name="id_user"),
	inverseJoinColumns = @JoinColumn(name="id_genre"))
	private List<Genre> genres = new ArrayList<Genre>();
	
	public User() {}

	public User(int id, String messengerUserId, String name, List<Genre> genres) {
		super();
		this.id = id;
		this.messengerUserId = messengerUserId;
		this.name = name;
		this.genres = genres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessengerUserId() {
		return messengerUserId;
	}

	public void setMessengerUserId(String messengerUserId) {
		this.messengerUserId = messengerUserId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", messengerUserId=" + messengerUserId + ", name=" + name + ", genres=" + genres
				+ "]";
	}

	

}
