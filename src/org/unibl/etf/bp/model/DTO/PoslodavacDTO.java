package org.unibl.etf.bp.model.DTO;

public class PoslodavacDTO {
	private int poslodavacId;
	private int ZIPkod;
	
	
	
	public PoslodavacDTO(int zIPkod) {
		super();
		ZIPkod = zIPkod;
	}
	public PoslodavacDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPoslodavacId() {
		return poslodavacId;
	}
	public void setPoslodavacId(int poslodavacId) {
		this.poslodavacId = poslodavacId;
	}
	public int getZIPkod() {
		return ZIPkod;
	}
	public void setZIPkod(int zIPkod) {
		ZIPkod = zIPkod;
	}
	
	
}