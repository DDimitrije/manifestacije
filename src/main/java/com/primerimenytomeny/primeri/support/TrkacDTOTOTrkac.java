package com.primerimenytomeny.primeri.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import com.primerimenytomeny.primeri.model.Trkac;
import com.primerimenytomeny.primeri.service.TrkaService;
import com.primerimenytomeny.primeri.service.TrkacService;
import com.primerimenytomeny.primeri.web.dto.TrkacDTO;


@Component
public class TrkacDTOTOTrkac implements Converter<TrkacDTO, Trkac> {

	@Autowired
	private TrkacService trkacService;
	@Autowired
	private TrkaService trkaService;

	@Override
	public Trkac convert(TrkacDTO source) {
		Trkac trkac;
		if (source.getId() == null) {
			trkac = new Trkac();
			trkac.setTrka(trkaService.findOne(source.getTrkaId()));
			// pivo.setVrstaPiva(vrstaService.findOne(source.getVrstaPivaId()));
		} else {
			trkac = trkacService.findOne(source.getId());
		}
		trkac.setIme(source.getIme());
		trkac.setPrezime(source.getPrezime());
		trkac.setPol(source.getPol());
		trkac.setVelicinaMajce(source.getVelicinaMajce());
		trkac.setAdresa(source.getAdresa());
		trkac.setGrad(source.getGrad());
		trkac.setDrzava(source.getDrzava());
		trkac.setNajBoljeVreme(source.getNajBoljeVreme());
		trkac.setKlub(source.getKlub());
		//trkac.setNaziv(trkaService.findOne(source.getTrkaNaziv()));
		trkac.setTrka(trkaService.findOne(source.getTrkaId()));

		return trkac;
	}
	
}
