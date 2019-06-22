package org.unibl.etf.bp.model.DTO;

public class KompanijaDTO extends PoslodavacDTO {
	private String naziv;
	private String adresa;
	private String JIB;
	
	
	public KompanijaDTO() {
		super();
	}
	public KompanijaDTO(int zIPcode, String naziv, String adresa, String JIB) {
		super(zIPcode);
		this.naziv=naziv;
		this.adresa=adresa;
		this.JIB=JIB;
	}
	
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa; 
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getJIB() {
		return JIB;
	}
	public void setJIB(String jIB) {
		JIB = jIB;
	}
	
}
