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
public class Film {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String filmUrl;

	@ManyToMany
	@JoinTable(name="film_genre", joinColumns = @JoinColumn(name="id_film"),
	inverseJoinColumns = @JoinColumn(name="id_genre"))
	private List<Genre> genre = new ArrayList<Genre>();
	
	private int year;

	public Film() {}
	
	public Film(int id, String name, String filmUrl, List<Genre> genre, int year) {
		super();
		this.id = id;
		this.name = name;
		this.filmUrl = filmUrl;
		this.genre = genre;
		this.year = year;
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

	public String getFilmUrl() {
		return filmUrl;
	}

	public void setFilmUrl(String filmUrl) {
		this.filmUrl = filmUrl;
	}

	public List<Genre> getGenre() {
		return genre;
	}

	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", name=" + name + ", filmUrl=" + filmUrl + ", genre=" + genre + ", year=" + year
				+ "]";
	}

	
	
}
