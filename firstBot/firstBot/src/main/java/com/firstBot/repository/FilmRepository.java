package com.firstBot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firstBot.entity.Film;
import com.firstBot.entity.Genre;

public interface FilmRepository extends JpaRepository<Film, Integer>{

	List<Film> findByGenre(List<Genre> genres);
	
	List<Film> findByYear(int year);
	
}
