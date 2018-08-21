package com.primerimenytomeny.primeri.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;





@Entity
@Table
public class Trka {	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String duzinaStaze;
	@Column
	private String kategorija;

	
	@ManyToOne(fetch=FetchType.EAGER)
	private Manifestacija manifestacija;
	//private TakmicenjaGodina takmicenjaGodina;
	
	//Trkaci
	@OneToMany(mappedBy="trka",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Trkac> trkaci = new ArrayList<>();
	
	
	
//	@OneToMany(fetch=FetchType.EAGER)
//	private Trkac trkac;
	
//	@OneToMany(mappedBy="pivo",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
//	private List<KupovinaPivo> kupovine = new ArrayList<>();
	
	
	
public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDuzinaStaze() {
		return duzinaStaze;
	}
	public void setDuzinaStaze(String duzinaStaze) {
		this.duzinaStaze = duzinaStaze;
	}
	public String getKategorija() {
		return kategorija;
	}
	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

//	public TakmicenjaGodina getTakmicenjaGodina() {
//		return takmicenjaGodina;
//	}
//	public void setTakmicenjaGodina(TakmicenjaGodina takmicenjaGodina) {
//		this.takmicenjaGodina = takmicenjaGodina;
//		if(takmicenjaGodina!=null && !takmicenjaGodina.getTrke().contains(this)){
//			takmicenjaGodina.getTrke().add(this);
//		}
//	}
	
	
	public Manifestacija getManifestacija() {
		return manifestacija;
	}
	public void setManifestacija(Manifestacija manifestacija) {
		this.manifestacija = manifestacija;
		if(manifestacija!=null && !manifestacija.getTrke().contains(this)){
			manifestacija.getTrke().add(this);
		}
	}
	
	
	
	public List<Trkac> getTrkaci() {
		return trkaci;
	}
	public void setTrkaci(List<Trkac> trkaci) {
		this.trkaci = trkaci;
	}
	public void addTrkac(Trkac trkac){
		this.trkaci.add(trkac);
		if(!this.equals(trkac.getTrka())){
			trkac.setTrka(this);
		}
	}
	
	
	
//	public void setTrkac(List<Trkac> trkaci) {
//		this.trkaci = trkaci;
//	}
	
	// add convenience methods for bi-directional relationship
	
//	public void add(Trkac tempTrkac) {
//		
//		if (trkaci == null) {
//			trkaci = new ArrayList<>();
//		}
//		
//		trkaci.add(tempTrkac);
//		
//		tempTrkac.setTrka(this);
//	}
	
}
