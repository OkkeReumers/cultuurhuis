package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Voorstelling implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String titel;
	private String uitvoerders;
	private Date datum;
	private int genreid;
	private BigDecimal prijs;
	private int vrijeplaatsen;
	
	public Voorstelling(int id, String titel, String uitvoerders, Date datum,
			int genreid, BigDecimal prijs, int vrijeplaatsen) {
		this.id = id;
		this.titel = titel;
		this.uitvoerders = uitvoerders;
		this.datum = datum;
		this.genreid = genreid;
		this.prijs = prijs;
		this.vrijeplaatsen = vrijeplaatsen;
	}
	
	public int getId() {
		return id;
	}

	public String getTitel() {
		return titel;
	}

	public String getUitvoerders() {
		return uitvoerders;
	}

	public Date getDatum() {
		return datum;
	}

	public int getGenreid() {
		return genreid;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public int getVrijeplaatsen() {
		return vrijeplaatsen;
	}	
	
}
