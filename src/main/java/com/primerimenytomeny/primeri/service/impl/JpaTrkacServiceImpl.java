package com.primerimenytomeny.primeri.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.primerimenytomeny.primeri.service.TrkacService;

import com.primerimenytomeny.primeri.model.Trkac;
import com.primerimenytomeny.primeri.repository.TrkacRepository;

@Service
@Transactional
public class JpaTrkacServiceImpl implements TrkacService {
		
		@Autowired
		private TrkacRepository trkacRepository;

		@Override
		public Page<Trkac> findAll(int pageNum) {
			return trkacRepository.findAll(
					new PageRequest(pageNum, 5));
		}

		@Override
		public Trkac findOne(Long id) {
			return trkacRepository.findOne(id);
		}

		@Override
		public void save(Trkac trkac) {
			trkacRepository.save(trkac);
		}

		@Override
		public void remove(Long id) {
			trkacRepository.delete(id);
		}

		@Override
		public Page<Trkac> findByTrkaId(int pageNum, Long trkaId) {
			
			return trkacRepository.findByTrkaId(trkaId, new PageRequest(pageNum, 5));
		}

		@Override
		public Page<Trkac> pretraga(String ime, String prezime, String pol, String velicinaMajce, String adresa, String grad,  String drzava, String najBoljeVreme, String klub, int page) {//String nazivPivare, Integer kolicina,  String nazivPivare
//			if(naziv != null ){
//				naziv = "%" + naziv + "%";
//			}
			return trkacRepository.pretraga(ime, prezime, pol, velicinaMajce, adresa, grad,  drzava, najBoljeVreme, klub, new PageRequest(page, 5));//nazivPivare nazivPivare, 
		}
//		// dugme nestalo
//		@Override
//		public Page<Pivo> nestalo(int page) {
//			
//			return pivoRepository.nestalo(new  PageRequest(page, 5));
//		}

}
