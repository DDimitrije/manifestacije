package com.primerimenytomeny.primeri.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.primerimenytomeny.primeri.model.Manifestacija;
import com.primerimenytomeny.primeri.model.TakmicenjaGodina;
import com.primerimenytomeny.primeri.service.ManifestacijaService;
import com.primerimenytomeny.primeri.service.TakmicenjaGodinaService;
import com.primerimenytomeny.primeri.web.dto.ManifestacijaDTO;
import com.primerimenytomeny.primeri.web.dto.TakmicenjaGodinaDTO;


@Component
public class TakmicenjaGodinaDTOTOTakmicenjaGodina implements Converter<TakmicenjaGodinaDTO, TakmicenjaGodina> {
	@Autowired
	private ManifestacijaService manifestacijaService;
	@Autowired
	private TakmicenjaGodinaService takmicenjaGodinaService;

	
	@Override
	public TakmicenjaGodina convert(TakmicenjaGodinaDTO source) {
		TakmicenjaGodina takmicenjaGodina = new TakmicenjaGodina();
//		if (source.getId() == null) {
//			takmicenjaGodina = new TakmicenjaGodina();
//			takmicenjaGodina.setTakmicenjaGodina(takmicenjaGodinaService.findOne(source.getManifestacijaId()));
//			// pivo.setVrstaPiva(vrstaService.findOne(source.getVrstaPivaId()));
//		} else {
//			takmicenjaGodina = takmicenjaGodinaService.findOne(source.getId());
//		}
		takmicenjaGodina.setId(source.getId());
		takmicenjaGodina.setNazivTrke(source.getNazivTrke());
//		takmicenjaGodina.setMestoOdrzavanja(source.getMestoOdrzavanja());
//		manifestacija.setTakmicenjaGodina(takmicenjaGodinaService.findOne(source.getTakmicenjaGodinaId()));
//		manifestacija.setTakmicenjaGodina(takmicenjaGodinaService.findOne(source.getTakmicenjaGodinaNazivTrke()));
		//manifestacija.setTakmicenjaGodinaNazivTrke(source.getTakmicenjaGodina().getNazivTrke());
		//manifestacija.setTakmicenjaGodina(source.getTakmicenjaGodinaId());
		
		return takmicenjaGodina;
	}
}
