package com.primerimenytomeny.primeri.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.primerimenytomeny.primeri.model.TakmicenjaGodina;
import com.primerimenytomeny.primeri.model.Trka;
import com.primerimenytomeny.primeri.service.ManifestacijaService;
import com.primerimenytomeny.primeri.service.TakmicenjaGodinaService;
import com.primerimenytomeny.primeri.service.TrkaService;
import com.primerimenytomeny.primeri.web.dto.TakmicenjaGodinaDTO;
import com.primerimenytomeny.primeri.web.dto.TrkaDTO;


@Component
public class TrkaDTOTOTrka implements Converter<TrkaDTO, Trka> {
	@Autowired
	private TrkaService trkaService;
//	@Autowired
//	private TakmicenjaGodinaService takmicenjaGodinaService;

	
	@Override
	public Trka convert(TrkaDTO source) {
		Trka trka = new Trka();
//		if (source.getId() == null) {
//			takmicenjaGodina = new TakmicenjaGodina();
//			takmicenjaGodina.setTakmicenjaGodina(takmicenjaGodinaService.findOne(source.getManifestacijaId()));
//			// pivo.setVrstaPiva(vrstaService.findOne(source.getVrstaPivaId()));
//		} else {
//			takmicenjaGodina = takmicenjaGodinaService.findOne(source.getId());
//		}
		trka.setId(source.getId());
		trka.setDuzinaStaze(source.getDuzinaStaze());
		trka.setKategorija(source.getKategorija());
//		takmicenjaGodina.setMestoOdrzavanja(source.getMestoOdrzavanja());
//		manifestacija.setTakmicenjaGodina(takmicenjaGodinaService.findOne(source.getTakmicenjaGodinaId()));
//		manifestacija.setTakmicenjaGodina(takmicenjaGodinaService.findOne(source.getTakmicenjaGodinaNazivTrke()));
		//manifestacija.setTakmicenjaGodinaNazivTrke(source.getTakmicenjaGodina().getNazivTrke());
		//manifestacija.setTakmicenjaGodina(source.getTakmicenjaGodinaId());
		
		return trka;
	}
}
