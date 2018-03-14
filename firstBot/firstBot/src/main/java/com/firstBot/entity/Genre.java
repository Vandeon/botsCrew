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

@Entity
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@ManyToMany
	@JoinTable(name="film_genre", joinColumns = @JoinColumn(name="id_genre"),
	inverseJoinColumns = @JoinColumn(name="id_film"))
	private List<Film> film = new ArrayList<Film>();

	@ManyToMany
	@JoinTable(name="user_genre", joinColumns = @JoinColumn(name="id_genre"),
	inverseJoinColumns = @JoinColumn(name="id_user"))
	private List<User> genres = new ArrayList<User>();
	
	public Genre() {}

	public Genre(int id, String name, List<Film> film, List<User> genres) {
		super();
		this.id = id;
		this.name = name;
		this.film = film;
		this.genres = genres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Film> getFilm() {
		return film;
	}

	public void setFilm(List<Film> film) {
		this.film = film;
	}

	public List<User> getGenres() {
		return genres;
	}

	public void setGenres(List<User> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + ", genres=" + genres + "]";
	}

	

}
