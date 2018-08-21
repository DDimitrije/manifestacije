package com.primerimenytomeny.primeri.service.impl;


import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.primerimenytomeny.primeri.service.TrkaService;

import com.primerimenytomeny.primeri.model.Trka;
import com.primerimenytomeny.primeri.repository.TrkaRepository;


@Service
@Transactional
public class JpaTrkaServiceImpl implements TrkaService {
	@Autowired
	private TrkaRepository trkaRepository;

//	@Override
//	public List<Trka> findAll(int pageNum) {
//		return trkaRepository.findAll();
//	}
	@Override
	public Page<Trka> findAll(int pageNum) {
		return trkaRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Trka findOne(Long id) {
		return trkaRepository.findOne(id);
	}

	@Override
	public void save(Trka trka) {
		trkaRepository.save(trka);
	}

	@Override
	public void remove(Long id) {
		trkaRepository.delete(id);
	}

	@Override
	public Page<Trka> pretraga(String duzinaStaze, String kategorija, int page) {
		// TODO Auto-generated method stub
		return trkaRepository.pretraga(duzinaStaze, kategorija,  new PageRequest(page, 5));
	}
}
