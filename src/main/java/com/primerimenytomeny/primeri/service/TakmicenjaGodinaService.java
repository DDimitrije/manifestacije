package com.primerimenytomeny.primeri.service;


import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.primerimenytomeny.primeri.model.TakmicenjaGodina;

public interface TakmicenjaGodinaService {
	    Page<TakmicenjaGodina> findAll(int pageNum);
		//List<TakmicenjaGodina> findAll(int pageNum);
		TakmicenjaGodina findOne(Long id);
		void save(TakmicenjaGodina takmicenjaGodina);
		void remove(Long id);
		
		Page<TakmicenjaGodina> pretraga(
			@Param("nazivTrke") String nazivTrke, 
			int page);
		
	
		

}
