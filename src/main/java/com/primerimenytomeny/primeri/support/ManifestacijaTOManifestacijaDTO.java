package com.primerimenytomeny.primeri.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.primerimenytomeny.primeri.model.Manifestacija;
import com.primerimenytomeny.primeri.web.dto.ManifestacijaDTO;

@Component
public class ManifestacijaTOManifestacijaDTO implements Converter<Manifestacija, ManifestacijaDTO> {

	@Override
	public ManifestacijaDTO convert(Manifestacija source) {
		ManifestacijaDTO dto = new ManifestacijaDTO();
		dto.setId(source.getId());
		dto.setNaziv(source.getNaziv());
		dto.setDatumOdrzavanja(source.getDatumOdrzavanja());
		dto.setMestoOdrzavanja(source.getMestoOdrzavanja());
		dto.setTakmicenjaGodinaId(source.getTakmicenjaGodina().getId());
		dto.setTakmicenjaGodinaNazivTrke(source.getTakmicenjaGodina().getNazivTrke());
		
//		dto.setProcenat_alkohola(source.getProcenat_alkohola());
//		dto.setKolicina(source.getKolicina());
//		dto.setPivaraId(source.getPivara().getId());
//		dto.setPivaraNaziv(source.getPivara().getNaziv());

		return dto;
	}

	public List<ManifestacijaDTO> convert(List<Manifestacija> manifestacije) {
		List<ManifestacijaDTO> ret = new ArrayList<>();

		for (Manifestacija k : manifestacije) {
			ret.add(convert(k));
		}

		return ret;
	}
}
