package org.unibl.etf.bp.model.DTO;

public class ZaposleniDTO {

	private String JMB;
	private String ime;
	private String prezime;
	private String korisnickoIme;
	private String lozinka;
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
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public ZaposleniDTO() {
		super();
	}
	public ZaposleniDTO(String jMB, String ime, String prezime, String korisnickoIme, String lozinka) {
		super();
		JMB = jMB;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}
}
