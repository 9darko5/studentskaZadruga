package org.unibl.etf.bp.model.DTO;

public class FizickoLiceDTO extends PoslodavacDTO {
	private String ime;
	private String prezime;
	private String brojLK;
	public FizickoLiceDTO() {
		super();
	}
	public FizickoLiceDTO(int zIPcode, String ime, String prezime, String brojLK) {
		super(zIPcode);
		this.ime=ime;
		this.prezime=prezime;
		this.brojLK=brojLK;
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
	public String getBrojLK() {
		return brojLK;
	}
	public void setBrojLK(String brojLK) {
		this.brojLK = brojLK;
	}
}
