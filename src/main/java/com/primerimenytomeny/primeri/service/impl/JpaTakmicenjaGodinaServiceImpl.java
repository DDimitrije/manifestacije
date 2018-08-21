package com.primerimenytomeny.primeri.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.primerimenytomeny.primeri.service.TakmicenjaGodinaService;
import com.primerimenytomeny.primeri.model.Manifestacija;
import com.primerimenytomeny.primeri.model.TakmicenjaGodina;
import com.primerimenytomeny.primeri.repository.TakmicenjaGodinaRepository;

@Service
@Transactional
public class JpaTakmicenjaGodinaServiceImpl implements TakmicenjaGodinaService {
	@Autowired
	private TakmicenjaGodinaRepository takmicenjaGodinaRepository;

	@Override
	public Page<TakmicenjaGodina> findAll(int pageNum) {
		return takmicenjaGodinaRepository.findAll(new PageRequest(pageNum, 5));
	}
//	@Override
//	public List<TakmicenjaGodina> findAll() {
//		return takmicenjaGodinaRepository.findAll();
//	}

	@Override
	public TakmicenjaGodina findOne(Long id) {
		return takmicenjaGodinaRepository.findOne(id);
	}

	@Override
	public void save(TakmicenjaGodina takmicenjaGodina) {
		takmicenjaGodinaRepository.save(takmicenjaGodina);
	}

	@Override
	public void remove(Long id) {
		takmicenjaGodinaRepository.delete(id);
	}
	public Page<TakmicenjaGodina> pretraga(String nazivTrke,   int page) {// String
		// nazivPivare,
		// Strin																												// nazivPivare
		
		return takmicenjaGodinaRepository.pretraga(nazivTrke,   new PageRequest(page, 5));// nazivPivare

	}
	
}