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

import com.primerimenytomeny.primeri.service.ManifestacijaService;
//import com.primerimenytomeny.primeri.support.KupovinaManifestacijaToKupovinaManifestacijaDTO;
import com.primerimenytomeny.primeri.support.ManifestacijaDTOTOManifestacija;
import com.primerimenytomeny.primeri.support.ManifestacijaTOManifestacijaDTO;
//import com.primerimenytomeny.primeri.web.dto.KupovinaManifestacijaDTO;
import com.primerimenytomeny.primeri.web.dto.ManifestacijaDTO;


	
@RestController
@RequestMapping("/api/manifestacije")
public class ApiMenifestacijaController {
		@Autowired
		private ManifestacijaService manifestacijaService;
//		@Autowired
//		private KupovinaMenifestacijaService kupovinaMenifestacijaService;
		@Autowired
		private ManifestacijaTOManifestacijaDTO toMenifestacijaDTO;
//		@Autowired
//		private KupovinaMenifestacijaToKupovinaMenifestacijaDTO toKupovinaDTO;
		@Autowired
		private ManifestacijaDTOTOManifestacija toManifestacija;
		
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<ManifestacijaDTO>> get(
				@RequestParam(required=false) String naziv,
				@RequestParam(required=false) String datumOdrzavanja,
				@RequestParam(required=false) String mestoOdrzavanja,
//				@RequestParam(required=false) Double maxI,
//				//@RequestParam(required=false) Integer maxKolicina,
//				@RequestParam(required=false) String manifestacijeraNaziv,
//				@RequestParam(required=false) Integer kolicina,
//				@RequestParam Boolean proveraNestalo, // dugme nestalo
				@RequestParam(defaultValue="0") int pageNum){
			
			
			
			Page<Manifestacija> manifestacije;
			//(String naziv, Double minI, Double maxI, String manifestacijeraNaziv, Integer kolicina, int page
			if(naziv != null || datumOdrzavanja != null || mestoOdrzavanja != null ) { //|| nazivPivare != null ||  kolicina != null) {
				manifestacije = manifestacijaService.pretraga(naziv, datumOdrzavanja, mestoOdrzavanja, pageNum); //nazivPivare,  kolicina, pageNum); //nazivPivare,
			//Dugme Nestalo
			}else{
				manifestacije = manifestacijaService.findAll(pageNum);
//			if(proveraNestalo == true){
//					manifestacije = manifestacijaService.nestalo(pageNum);
//				}else{
//				manifestacije = manifestacijaService.findAll(pageNum);
//			}
		}	
			HttpHeaders headers = new HttpHeaders();
			headers.add("totalPages", Integer.toString(manifestacije.getTotalPages()) );
			return  new ResponseEntity<>(
					toMenifestacijaDTO.convert(manifestacije.getContent()),
					headers,
					HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.GET, value="/{id}")
		public ResponseEntity<ManifestacijaDTO> get(
				@PathVariable Long id){
			Manifestacija manifestacija = manifestacijaService.findOne(id);
			
			if(manifestacija==null){
				return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(toMenifestacijaDTO.convert(manifestacija), HttpStatus.OK);	
		}
		
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<ManifestacijaDTO> add(
				@RequestBody ManifestacijaDTO novaManifestacija){
			
			Manifestacija manifestacija = toManifestacija.convert(novaManifestacija); 
			manifestacijaService.save(manifestacija);
			
			return new ResponseEntity<>(toMenifestacijaDTO.convert(manifestacija),
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
		public ResponseEntity<ManifestacijaDTO> edit(
				@PathVariable Long id,
				@RequestBody ManifestacijaDTO izmenjen){
			
			if(!id.equals(izmenjen.getId())){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			Manifestacija manifestacija = toManifestacija.convert(izmenjen); 
			manifestacijaService.save(manifestacija);
			
			return new ResponseEntity<>(toMenifestacijaDTO.convert(manifestacija), HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
		public ResponseEntity<ManifestacijaDTO> delete(@PathVariable Long id){
			manifestacijaService.remove(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
}
