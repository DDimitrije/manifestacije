package com.primerimenytomeny.primeri.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table
public class Trkac {	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String ime;
	@Column
	private String prezime;
	@Column
	private String pol;
	@Column
	private String velicinaMajce;
	@Column
	private String adresa;
	@Column
	private String grad;
	@Column
	private String drzava;
	@Column
	private String najBoljeVreme;
	@Column
	private String klub;
	

	@ManyToOne(fetch=FetchType.EAGER)
	private Trka trka;
	
//	@OneToMany(mappedBy="trkac",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
//	private List<Trkac> trkaci = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPol() {
		return pol;
	}
	public void setPol(String pol) {
		this.pol = pol;
	}
	public String getVelicinaMajce() {
		return velicinaMajce;
	}
	public void setVelicinaMajce(String velicinaMajce) {
		this.velicinaMajce = velicinaMajce;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	public String getNajBoljeVreme() {
		return najBoljeVreme;
	}
	public void setNajBoljeVreme(String najBoljeVreme) {
		this.najBoljeVreme = najBoljeVreme;
	}
	public String getKlub() {
		return klub;
	}
	public void setKlub(String klub) {
		this.klub = klub;
	}
	public Trka getTrka() {
		return trka;
	}
//	public void setTrka(Trka trka) {
//		this.trka = trka;
//	}
//	public List<KupovinaPivo> getKupovine() {
//		return kupovine;
//	}
//	public void setKupovine(List<KupovinaPivo> kupovine) {
//		this.kupovine = kupovine;
//	}
	
	public void setTrka(Trka trka) {
		this.trka = trka;
		if(trka!=null && !trka.getTrkaci().contains(this)){
			trka.getTrkaci().add(this);
		}
	}
	
//	public void setPivara(Pivara pivara) {
//		this.pivara = pivara;
//		if(pivara!=null && !pivara.getPiva().contains(this)){
//			pivara.getPiva().add(this);
//		}
//	}	
//	
	
//	public List<KupovinaPivo> getKupovine() {
//		return kupovine;
//	}
//	public void setKupovine(List<KupovinaPivo> kupovine) {
//		this.kupovine = kupovine;
//	}
//	public void addKupovina(KupovinaPivo kupovina){
//		this.kupovine.add(kupovina);
//		
//		if(!this.equals(kupovina.getPivo())){
//			kupovina.setPivo(this);
//		}
//	}
	
	
//	public VrstaPiva getVrstaPiva() {
//		return vrstaPiva;
//	}
//
//	public void setVrstaPiva(VrstaPiva vrstaPiva) {
//		this.vrstaPiva = vrstaPiva;
//	}
}

