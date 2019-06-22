package org.unibl.etf.bp.model.DTO;

import java.util.Date;

public class ClanDTO {
	private String JMB;
	private String ime;
	private String prezime;
	private String adresa;
	private int ZIPkod;
	private int brojClanskeKarte;
	private String brojLK;
	private Date datumIstekaClanskeKarte;
	private String aJMB;
	
	public String getJMB() {
		return JMB;
	}
	public void setJMB(String jMB) {
		JMB = jMB;
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
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public int getZIPkod() {
		return ZIPkod;
	}
	public void setZIPkod(int zIPkod) {
		this.ZIPkod = zIPkod;
	}
	public int getBrojClanskeKarte() {
		return brojClanskeKarte;
	}
	public void setBrojClanskeKarte(int brojClanskeKarte) {
		this.brojClanskeKarte = brojClanskeKarte;
	}
	public String getBrojLK() {
		return brojLK;
	}
	public void setBrojLK(String brojLK) {
		this.brojLK = brojLK;
	}
	public Date getDatumIstekaClanskeKarte() {
		return datumIstekaClanskeKarte;
	}
	public void setDatumIstekaClanskeKarte(Date datumIstekaClanskeKarte) {
		this.datumIstekaClanskeKarte = datumIstekaClanskeKarte;
	}
	public String getaJMB() {
		return aJMB;
	}
	public void setaJMB(String aJMB) {
		this.aJMB = aJMB;
	}
	public ClanDTO() {
		super();
	}
	public ClanDTO(String jMB, String ime, String prezime, String adresa, int zIPkod,
			int brojClanskeKarte, String brojLK, Date datumIstekaClanskeKarte, String aJMB) {
		super();
		JMB = jMB;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		ZIPkod = zIPkod;
		this.brojClanskeKarte = brojClanskeKarte;
		this.brojLK = brojLK;
		this.datumIstekaClanskeKarte = datumIstekaClanskeKarte;
		this.aJMB = aJMB;
	}
	
	
}
