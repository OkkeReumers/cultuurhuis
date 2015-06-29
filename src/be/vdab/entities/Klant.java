package be.vdab.entities;

import java.io.Serializable;

public class Klant implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String voornaam;
	private String familienaam;
	private String straat;
	private String huisnr;
	private String postcode;
	private String gemeente;
	private String gebruikersnaam;
	private String paswoord;

	public Klant(int id, String voornaam, String familienaam, String straat,
			String huisnr, String postcode, String gemeente, String gebruikersnaam,
			String passwoord) {
		this.id = id;
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.straat = straat;
		this.huisnr = huisnr;
		this.postcode = postcode;
		this.gemeente = gemeente;
		this.gebruikersnaam = gebruikersnaam;
		this.paswoord = passwoord;
	}

	public int getId() {
		return id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public String getStraat() {
		return straat;
	}

	public String getHuisnr() {
		return huisnr;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getGemeente() {
		return gemeente;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public String getPaswoord() {
		return paswoord;
	}
	
	@Override 
	public String toString() { 
		return voornaam + " " + familienaam + " " + straat + " " + huisnr + " " + postcode + " " + gemeente ;
		}

}
