package com.primerimenytomeny.primeri.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.primerimenytomeny.primeri.model.Manifestacija;
import com.primerimenytomeny.primeri.model.TakmicenjaGodina;
import com.primerimenytomeny.primeri.service.ManifestacijaService;
import com.primerimenytomeny.primeri.service.TakmicenjaGodinaService;
//import com.primerimenytomeny.primeri.support.KupovinaManifestacijaToKupovinaManifestacijaDTO;
import com.primerimenytomeny.primeri.support.ManifestacijaDTOTOManifestacija;
import com.primerimenytomeny.primeri.support.ManifestacijaTOManifestacijaDTO;
import com.primerimenytomeny.primeri.support.TakmicenjaGodinaDTOTOTakmicenjaGodina;
import com.primerimenytomeny.primeri.support.TakmicenjaGodinaTOTakmicenjaGodinaDTO;
//import com.primerimenytomeny.primeri.web.dto.KupovinaManifestacijaDTO;
import com.primerimenytomeny.primeri.web.dto.ManifestacijaDTO;
import com.primerimenytomeny.primeri.web.dto.TakmicenjaGodinaDTO;


	
@RestController
@RequestMapping("/api/takmicenjaGodinae")
public class ApiTakmicenjaGodinaController {
		@Autowired
		private TakmicenjaGodinaService takmicenjaGodinaService;
//		@Autowired
//		private KupovinaMenifestacijaService kupovinaMenifestacijaService;
		@Autowired
		private TakmicenjaGodinaTOTakmicenjaGodinaDTO toTakmicenjaGodinaDTO;
//		@Autowired
//		private KupovinaMenifestacijaToKupovinaMenifestacijaDTO toKupovinaDTO;
		@Autowired
		private TakmicenjaGodinaDTOTOTakmicenjaGodina toTakmicenjaGodina;
		
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<TakmicenjaGodinaDTO>> get(
				@RequestParam(required=false) String nazivTrke,
				//@RequestParam(required=false) String datumOdrzavanja,
				//@RequestParam(required=false) String mestoOdrzavanja,
//				@RequestParam(required=false) Double maxI,
//				//@RequestParam(required=false) Integer maxKolicina,
//				@RequestParam(required=false) String manifestacijeraNaziv,
//				@RequestParam(required=false) Integer kolicina,
//				@RequestParam Boolean proveraNestalo, // dugme nestalo
				@RequestParam(defaultValue="0") int pageNum){
			
			
			
			Page<TakmicenjaGodina> takmicenjaGodinaee;
			//(String naziv, Double minI, Double maxI, String manifestacijeraNaziv, Integer kolicina, int page
			if(nazivTrke != null ) { //|| nazivPivare != null ||  kolicina != null) {
				takmicenjaGodinaee = takmicenjaGodinaService.pretraga(nazivTrke , pageNum); //nazivPivare,  kolicina, pageNum); //nazivPivare,
			//Dugme Nestalo
			}else{
				takmicenjaGodinaee = takmicenjaGodinaService.findAll(pageNum);
//			if(proveraNestalo == true){
//					manifestacije = manifestacijaService.nestalo(pageNum);
//				}else{
//				manifestacije = manifestacijaService.findAll(pageNum);
//			}
		}	
			HttpHeaders headers = new HttpHeaders();
			headers.add("totalPages", Integer.toString(takmicenjaGodinaee.getTotalPages()) );
			return  new ResponseEntity<>(
					toTakmicenjaGodinaDTO.convert(takmicenjaGodinaee.getContent()),
					headers,
					HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.GET, value="/{id}")
		public ResponseEntity<TakmicenjaGodinaDTO> get(
				@PathVariable Long id){
			TakmicenjaGodina takmicenjaGodina = takmicenjaGodinaService.findOne(id);
			
			if(takmicenjaGodina==null){
				return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(toTakmicenjaGodinaDTO.convert(takmicenjaGodina), HttpStatus.OK);	
		}
		
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<TakmicenjaGodinaDTO> add(
				@RequestBody TakmicenjaGodinaDTO novaTakmicenjaGodina){
			
			TakmicenjaGodina takmicenjaGodina = toTakmicenjaGodina.convert(novaTakmicenjaGodina); 
			takmicenjaGodinaService.save(takmicenjaGodina);
			
			return new ResponseEntity<>(toTakmicenjaGodinaDTO.convert(takmicenjaGodina),
					HttpStatus.CREATED);
		}
		
//		@RequestMapping(method=RequestMethod.POST, value="/{id}/kupovina")
//		public ResponseEntity<KupovinaManifestacijaDTO> buy(@PathVariable Long id){
//			
//			KupovinaManifestacija k = kupovinaManifestacijaService.buyABook(id);
//			
//			if(k == null) {
//				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//			}else {
//				return new ResponseEntity<>(toKupovinaDTO.convert(k), HttpStatus.CREATED);
//			}
//			
//		}
		
		@RequestMapping(method=RequestMethod.PUT, value="/{id}")
		public ResponseEntity<TakmicenjaGodinaDTO> edit(
				@PathVariable Long id,
				@RequestBody TakmicenjaGodinaDTO izmenjen){
			
			if(!id.equals(izmenjen.getId())){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			TakmicenjaGodina takmicenjaGodina = toTakmicenjaGodina.convert(izmenjen); 
			takmicenjaGodinaService.save(takmicenjaGodina);
			
			return new ResponseEntity<>(toTakmicenjaGodinaDTO.convert(takmicenjaGodina), HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
		public ResponseEntity<TakmicenjaGodinaDTO> delete(@PathVariable Long id){
			takmicenjaGodinaService.remove(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}


////------------------------------------------------------------------------------------------


//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.primerimenytomeny.primeri.model.Manifestacija;
//import com.primerimenytomeny.primeri.model.TakmicenjaGodina;
//import com.primerimenytomeny.primeri.service.ManifestacijaService;
//import com.primerimenytomeny.primeri.service.TakmicenjaGodinaService;
//import com.primerimenytomeny.primeri.support.ManifestacijaTOManifestacijaDTO;
//import com.primerimenytomeny.primeri.support.TakmicenjaGodinaTOTakmicenjaGodinaDTO;
//import com.primerimenytomeny.primeri.web.dto.ManifestacijaDTO;
//import com.primerimenytomeny.primeri.web.dto.TakmicenjaGodinaDTO;
//
//@RestController
//@RequestMapping(value = "/api/takmicenjaGodinae")
//public class ApiTakmicenjaGodinaController {// pivarai
//	@Autowired
//	private TakmicenjaGodinaService takmicenjaGodinaService;
//	@Autowired
//	private ManifestacijaService manifestacijaService;
//	@Autowired
//	private TakmicenjaGodinaTOTakmicenjaGodinaDTO toDTO;
//	@Autowired
//	private ManifestacijaTOManifestacijaDTO toManifestacijaDTO;
//
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List<TakmicenjaGodinaDTO>> get() {
//		
//		return new ResponseEntity<>(toDTO.convert(takmicenjaGodinaService.findAll()), HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<TakmicenjaGodinaDTO> get(@PathVariable Long id) {
//
//		TakmicenjaGodina takmicenjaGodina = takmicenjaGodinaService.findOne(id);
//
//		if (takmicenjaGodina == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//
//		return new ResponseEntity<>(toDTO.convert(takmicenjaGodina), HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/{takmicenjaGodinaId}/manifestacije")
//	public ResponseEntity<List<ManifestacijaDTO>> manifestacijeTakmicenjaGodina(@PathVariable Long takmicenjaGodinaId,
//			@RequestParam(defaultValue = "0") int pageNum) {
//		Page<Manifestacija> manifestacije = manifestacijaService.findByTakmicenjaGodinaId(pageNum, takmicenjaGodinaId);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("totalPages", Integer.toString(manifestacije.getTotalPages()));
//		return new ResponseEntity<>(toManifestacijaDTO.convert(manifestacije.getContent()), headers, HttpStatus.OK);
//	}
}
