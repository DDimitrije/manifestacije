package com.primerimenytomeny.primeri.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.primerimenytomeny.primeri.service.ManifestacijaService;

import com.primerimenytomeny.primeri.model.Manifestacija;
import com.primerimenytomeny.primeri.repository.ManifestacijaRepository;

@Service
@Transactional
public class JpaManifestacijaServiceImpl implements ManifestacijaService {

	@Autowired
	private ManifestacijaRepository manifestacijaRepository;
	
	@Override
	public Page<Manifestacija> findAll(int pageNum) {
		return manifestacijaRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Manifestacija findOne(Long id) {
		return manifestacijaRepository.findOne(id);
	}

	@Override
	public void save(Manifestacija manifestacija) {
		manifestacijaRepository.save(manifestacija);
	}

	@Override
	public void remove(Long id) {
		manifestacijaRepository.delete(id);
	}

	@Override
	public Page<Manifestacija> findByTakmicenjaGodinaId(int pageNum, Long takmicenjaGodinaId) {

		return manifestacijaRepository.findByTakmicenjaGodinaId(takmicenjaGodinaId, new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Manifestacija> pretraga(String naziv, String datumOdrzavanja, String mestoOdrzavanja,  int page) {// String
																														// nazivPivare,
																										// Strin																												// nazivPivare
		if (naziv != null) {
			naziv = "%" + naziv + "%";
		}
		return manifestacijaRepository.pretraga(naziv, datumOdrzavanja, mestoOdrzavanja,  new PageRequest(page, 5));// nazivPivare
																											// nazivPivare,
																											// kolicina
																											// ,
	}

	// dugme nestalo
//	@Override
//	public Page<Manifestacija> nestalo(int page) {
//
//		return manifestacijaRepository.nestalo(new PageRequest(page, 5));
//	}
	
}
