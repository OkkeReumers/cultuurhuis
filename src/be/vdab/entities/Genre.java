package be.vdab.entities;

import java.io.Serializable;

public class Genre implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String naam;

	public Genre(int id, String naam) {
		setId(id);
		setNaam(naam);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

}
