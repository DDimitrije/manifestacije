package com.primerimenytomeny.primeri.web.dto;


public class ManifestacijaDTO {

	private Long id;
	private String naziv;
	private String datumOdrzavanja;
	private String mestoOdrzavanja;

	private Long takmicenjaGodinaId;
	private String takmicenjaGodinaNazivTrke;
	
//	private Long vrstaPivaId;
//	private String vrstaPivaNaziv;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getDatumOdrzavanja() {
		return datumOdrzavanja;
	}
	public void setDatumOdrzavanja(String datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}
	public String getMestoOdrzavanja() {
		return mestoOdrzavanja;
	}
	public void setMestoOdrzavanja(String mestoOdrzavanja) {
		this.mestoOdrzavanja = mestoOdrzavanja;
	}
	
	public Long getTakmicenjaGodinaId() {
		return takmicenjaGodinaId;
	}
	public void setTakmicenjaGodinaId(Long takmicenjaGodinaId) {
		this.takmicenjaGodinaId = takmicenjaGodinaId;
	}
	public String getTakmicenjaGodinaNazivTrke() {
		return takmicenjaGodinaNazivTrke;
	}
	public void setTakmicenjaGodinaNazivTrke(String takmicenjaGodinaNazivTrke) {
		this.takmicenjaGodinaNazivTrke = takmicenjaGodinaNazivTrke;
	}
	
	
//	public Long getIzdavacId() {
//		return pivaraId;
//	}
//	public void setIzdavacId(Long pivaraId) {
//		this.pivaraId = pivaraId;
//	}
//	public String getIzdavacNaziv() {
//		return pivaraNaziv;
//	}
//	public void setIzdavacNaziv(String pivaraNaziv) {
//		this.pivaraNaziv = pivaraNaziv;
//	}
//	public String getNaziv() {
//		return naziv;
//	}
//	public void setNaziv(String naziv) {
//		this.naziv = naziv;
//	}
//	public String getVrsta() {
//		return vrsta;
//	}
//	public void setVrsta(String vrsta) {
//		this.vrsta = vrsta;
//	}
//	public Double getIBU() {
//		return IBU;
//	}
//	public void setIBU(Double IBU) {
//		this.IBU= IBU;
//	}
//	public Integer getKolicina() {
//		return kolicina;
//	}
//	public void setKolicina(Integer kolicina) {
//		this.kolicina = kolicina;
//	}
//	public Double getProcenat_alkohola() {
//		return procenat_alkohola;
//	}
//	public void setProcenat_alkohola(Double procenat_alkohola) {
//		this.procenat_alkohola = procenat_alkohola;
//	}
//	public String getPivaraNaziv() {
//		return pivaraNaziv;
//	}
//	public void setPivaraNaziv(String pivaraNaziv) {
//		this.pivaraNaziv = pivaraNaziv;
//	}
//	public Long getPivaraId() {
//		return pivaraId;
//	}
//	public void setPivaraId(Long pivaraId) {
//		this.pivaraId = pivaraId;
//	}
	
}
