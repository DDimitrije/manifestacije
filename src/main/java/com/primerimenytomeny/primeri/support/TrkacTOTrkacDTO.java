package com.primerimenytomeny.primeri.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.primerimenytomeny.primeri.model.Trkac;
import com.primerimenytomeny.primeri.web.dto.TrkacDTO;

@Component
public class TrkacTOTrkacDTO implements Converter<Trkac, TrkacDTO> {

	@Override
	public TrkacDTO convert(Trkac source) {
		TrkacDTO dto = new TrkacDTO();
		dto.setId(source.getId());
		dto.setIme(source.getIme());
		dto.setPrezime(source.getPrezime());
		dto.setPol(source.getPol());
		dto.setVelicinaMajce(source.getVelicinaMajce());
		dto.setAdresa(source.getAdresa());
		dto.setGrad(source.getGrad());
		dto.setDrzava(source.getDrzava());
		dto.setNajBoljeVreme(source.getNajBoljeVreme());
		dto.setKlub(source.getKlub());
		dto.setTrkaId(source.getTrka().getId());
		dto.setDuzinaStaze(source.getTrka().getDuzinaStaze());
		//dto.setIzdavacId(source.getIzdavac().getId());
		
		//dto.setTrkaNaziv(source.getTrka().getManifestacija().getNaziv());
		// dto.setVrstaPivaId(source.getVrstaPiva().getId());
		// dto.setVrstaPivaNaziv(source.getVrstaPiva().getNaziv());

		return dto;
	}

	public List<TrkacDTO> convert(List<Trkac> trkaci) {
		List<TrkacDTO> ret = new ArrayList<>();

		for (Trkac k : trkaci) {
			ret.add(convert(k));
		}

		return ret;
	}

}
