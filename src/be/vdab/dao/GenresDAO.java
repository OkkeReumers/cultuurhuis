package be.vdab.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import be.vdab.entities.Genre;

public class GenresDAO  {
	private static final Map<Long, Genre> GENRES = new ConcurrentHashMap<>();
	static { 
		GENRES.put(1L,new Genre(1, "Prosciutto"));
		GENRES.put(2L,new Genre(2, "Margehrita"));
		GENRES.put(3L,new Genre(3, "Calzone"));
		GENRES.put(4L,new Genre(4, "Fungi & Olive"));
	}
	public List<Genre> findAll() { 
		return new ArrayList<>(GENRES.values());
	}
	public Genre read(long id) {
		return GENRES.get(id);
	}
}