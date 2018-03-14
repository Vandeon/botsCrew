package com.firstBot.content;

import java.util.ArrayList;
import java.util.List;

import com.firstBot.entity.Film;
import com.firstBot.entity.Genre;
import com.firstBot.service.FilmService;
import com.firstBot.service.GenreService;

public class ContentMaker {
	
	public void makeContent(FilmService fs, GenreService gs) {
		
		String genres[] = {"Crime","Drama", "Fantasy", "Horror", "Action", "Advanture", "Animation", "Family", "Comedy"};
		for(int i = 0; i < genres.length; i++) {
			Genre genre = new Genre();
			genre.setName(genres[i]);
			gs.save(genre);
		}
		
		String filmsName[] = {"Black Panther", "The graetest showman", "Wonder", "Coco", "Lady Bird", "Dunkirk", "Wonder women", "Baywatch", "Guardians of the Galaxy 2", "Deadpool"};
		int filmsYear[] = {2018, 2017, 2017, 2017, 2017, 2017, 2017, 2017, 2017, 2016};
		String filmUrls[] = {"","","","","","","","","",""};
		String filmsGenres[] = {"Action Adventure", "Drama", "Drama Family", "Animation Adventure Comedy", "Drama Comedy", "Action Drama", "Action Adventure Fantasy", "Action Comedy Crime", "Action Adventure", "Action Adventure Comedy"};
		
		for(int i = 0; i < filmsName.length; i++) {
			Film film = new Film();
			film.setName(filmsName[i]);
			film.setYear(filmsYear[i]);
			film.setFilmUrl(filmUrls[i]);
			String temp[] = filmsGenres[i].split(" ");
			List<Genre> filmGenres = new ArrayList<Genre>();
			for(int j = 0; j < temp.length; j++) {
				System.out.println("temp"+ j +" = " + temp[j]);
				Genre genre = new Genre();
				genre = gs.findByName(temp[j]);
				filmGenres.add(genre);
				System.out.println("genre - " + genre);
			}
			System.out.println(filmGenres);
			film.setGenre(filmGenres);
			fs.save(film);
		}
	}
	
}