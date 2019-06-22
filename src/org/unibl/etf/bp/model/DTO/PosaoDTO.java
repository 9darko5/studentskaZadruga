package org.unibl.etf.bp.model.DTO;

public class PosaoDTO {
	private float cijenaPoSatu;
	private boolean zavrsen;
	private int brojPotrebnihRadnika;
	private String opisPosla;
	private String aJmb;
	private int poslodavacId;
	
	public PosaoDTO() {
		super();
	}
	
	public PosaoDTO(float cijenaPoSatu, int brojPotrebnihRadnika, String opisPosla,
			String aJmb, int poslodavacId) {
		super();
		this.cijenaPoSatu = cijenaPoSatu;
		this.brojPotrebnihRadnika = brojPotrebnihRadnika;
		this.opisPosla = opisPosla;
		this.aJmb = aJmb;
		this.poslodavacId = poslodavacId;
	}



	public float getCijenaPoSatu() {
		return cijenaPoSatu;
	}

	public void setCijenaPoSatu(float cijenaPoSatu) {
		this.cijenaPoSatu = cijenaPoSatu;
	}

	public boolean isZavrsen() {
		return zavrsen;
	}

	public void setZavrsen(boolean zavrsen) {
		this.zavrsen = zavrsen;
	}

	public int getBrojPotrebnihRadnika() {
		return brojPotrebnihRadnika;
	}

	public void setBrojPotrebnihRadnika(int brojPotrebnihRadnika) {
		this.brojPotrebnihRadnika = brojPotrebnihRadnika;
	}

	public String getOpisPosla() {
		return opisPosla;
	}

	public void setOpisPosla(String opisPosla) {
		this.opisPosla = opisPosla;
	}

	public String getaJmb() {
		return aJmb;
	}

	public void setaJmb(String aJmb) {
		this.aJmb = aJmb;
	}

	public int getPoslodavacId() {
		return poslodavacId;
	}

	public void setPoslodavacId(int poslodavacId) {
		this.poslodavacId = poslodavacId;
	}
	
}
