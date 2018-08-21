package com.primerimenytomeny.primeri.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import com.primerimenytomeny.primeri.model.TakmicenjaGodina;

import com.primerimenytomeny.primeri.web.dto.TakmicenjaGodinaDTO;

@Component
public class TakmicenjaGodinaTOTakmicenjaGodinaDTO implements Converter<TakmicenjaGodina, TakmicenjaGodinaDTO> {

	@Override
	public TakmicenjaGodinaDTO convert(TakmicenjaGodina takmicenjaGodina) {
		TakmicenjaGodinaDTO takmicenjaGodinaDTO = new TakmicenjaGodinaDTO();
		takmicenjaGodinaDTO.setId(takmicenjaGodina.getId());
		takmicenjaGodinaDTO.setNazivTrke(takmicenjaGodina.getNazivTrke());
//		takmicenjaGodinaDTO.setNaziv(takmicenjaGodina.getNaziv());
//		takmicenjaGodinaDTO.setPIB(takmicenjaGodina.getPIB());
//		takmicenjaGodinaDTO.setDrzava(takmicenjaGodina.getDrzava());
		return takmicenjaGodinaDTO;
	}

	public List<TakmicenjaGodinaDTO> convert(List<TakmicenjaGodina> takmicenjaGodinaLista) {
		List<TakmicenjaGodinaDTO> ret = new ArrayList<>();

		for (TakmicenjaGodina i : takmicenjaGodinaLista) {
			ret.add(convert(i));
		}

		return ret;
	}

}
