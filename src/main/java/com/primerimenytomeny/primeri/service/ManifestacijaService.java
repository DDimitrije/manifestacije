package com.primerimenytomeny.primeri.service;

import java.util.Date;

//import java.util.Date;

//import javax.persistence.Column;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.primerimenytomeny.primeri.model.Manifestacija;


public interface ManifestacijaService {
	Page<Manifestacija> findAll(int pageNum);
	Manifestacija findOne(Long id);
	void save(Manifestacija manifestacija);
	void remove(Long id);
	Page<Manifestacija> findByTakmicenjaGodinaId(int pageNum, Long takmicenjaGodinaId);//pivaraID

	Page<Manifestacija> pretraga(
			@Param("naziv") String naziv, 
			@Param("datumOdrzavanja") String datumOdrzavanja, 
			@Param("mestoOdrzavanja") String mestoOdrzavanja, 
			int page);
	//Page<Manifestacija> pretraga(String naziv, Date datumOdrzavanja, String mestoOdrzavanja, int page);
	


}
