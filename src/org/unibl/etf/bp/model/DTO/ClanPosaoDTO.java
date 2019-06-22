package org.unibl.etf.bp.model.DTO;

import java.util.Date;

public class ClanPosaoDTO {
	private float brojRadnihSati;
	private float zaradjeno;
	private int posaoId;
	private String cJMB;
	
	private String ime;
	private String prezime;
	private String opisPosla;
	private Date datum;
	
	public ClanPosaoDTO(float brojRadnihSati, float zaradjeno, int posaoId, String cJMB, String opisPosla) {
		super();
		this.opisPosla=opisPosla;
		this.zaradjeno=zaradjeno;
		this.brojRadnihSati = brojRadnihSati;
		this.posaoId = posaoId;
		this.cJMB = cJMB;
		this.datum=new Date();
	}
	
	public ClanPosaoDTO(String ime, String prezime, float ukupanBrojRadnihSati, float ukupnoZaradjeno, String opisPosla, Date datum) {
		super();
		this.opisPosla=opisPosla;
		this.ime = ime;
		this.prezime = prezime;
		this.brojRadnihSati = ukupanBrojRadnihSati;
		this.zaradjeno = ukupnoZaradjeno;
		this.datum=datum;
	}
	
	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getOpisPosla() {
		return opisPosla;
	}
	public void setOpisPosla(String opisPosla) {
		this.opisPosla = opisPosla;
	}
	public ClanPosaoDTO() {
		super();
	}
	
	public float getZaradjeno() {
		return zaradjeno;
	}
	public void setZaradjeno(float zaradjeno) {
		this.zaradjeno = zaradjeno;
	}
	public float getBrojRadnihSati() {
		return brojRadnihSati;
	}
	public void setBrojRadnihSati(float brojRadnihSati) {
		this.brojRadnihSati = brojRadnihSati;
	}
	public int getPosaoId() {
		return posaoId;
	}
	public void setPosaoId(int posaoId) {
		this.posaoId = posaoId;
	}
	public String getcJMB() {
		return cJMB;
	}
	public void setcJMB(String cJMB) {
		this.cJMB = cJMB;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
}
