package com.primerimenytomeny.primeri.web.controller;

import java.util.List;

import javax.persistence.Column;

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

import com.primerimenytomeny.primeri.model.Trka;
import com.primerimenytomeny.primeri.service.TrkaService;
import com.primerimenytomeny.primeri.support.TrkaDTOTOTrka;
import com.primerimenytomeny.primeri.support.TrkaTOTrkaDTO;
import com.primerimenytomeny.primeri.web.dto.TrkaDTO;

	
	@RestController
	@RequestMapping("/api/trke")
	public class ApiTrkaController {
		@Autowired
		private TrkaService trkaService;
		
		@Autowired
		private TrkaTOTrkaDTO toTrkaDTO;

		@Autowired
		private TrkaDTOTOTrka toTrka;
		
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<TrkaDTO>> get(
				@RequestParam(required=false) String duzinaStaze,
				@RequestParam(required=false) String kategorija,
//				@RequestParam(required=false) Double minI,
//				@RequestParam(required=false) Double maxI,
//				//@RequestParam(required=false) Integer maxKolicina,
//				@RequestParam(required=false) String trkeraNaziv,
//				@RequestParam(required=false) Integer kolicina,
//				@RequestParam Boolean proveraNestalo, // dugme nestalo
				@RequestParam(defaultValue="0") int pageNum){
			

			
			Page<Trka> trke = null;
			//(String naziv, Double minI, Double maxI, String trkeraNaziv, Integer kolicina, int page
			if(duzinaStaze != null || kategorija != null ) { //|| nazivPivare != null ||  kolicina != null) {
				trke = trkaService.pretraga(duzinaStaze, kategorija, pageNum); //nazivPivare,  kolicina, pageNum); //nazivPivare,
			//Dugme Nestalo
			}else{
				trke = trkaService.findAll(pageNum);
//			if(proveraNestalo == true){
//					trke = trkaService.nestalo(pageNum);
//				}else{
//				trke = trkaService.findAll(pageNum);
//			}
		}	
			HttpHeaders headers = new HttpHeaders();
			headers.add("totalPages", Integer.toString(trke.getTotalPages()) );
			return  new ResponseEntity<>(
					toTrkaDTO.convert(trke.getContent()),
					headers,
					HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.GET,
						value="/{id}")
		public ResponseEntity<TrkaDTO> get(
				@PathVariable Long id){
			Trka trka = trkaService.findOne(id);
			
			if(trka==null){
				return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(
					toTrkaDTO.convert(trka),
					HttpStatus.OK);	
		}
		
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<TrkaDTO> add(
				@RequestBody TrkaDTO novaTrka){
			
			Trka trka = toTrka.convert(novaTrka); 
			trkaService.save(trka);
			
			return new ResponseEntity<>(toTrkaDTO.convert(trka),
					HttpStatus.CREATED);
		}
		
//		@RequestMapping(method=RequestMethod.POST, value="/{id}/kupovina")
//		public ResponseEntity<KupovinaTrkaDTO> buy(@PathVariable Long id){
//			
//			KupovinaTrka k = kupovinaTrkaService.buyABook(id);
//			
//			if(k == null) {
//				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//			}else {
//				return new ResponseEntity<>(toKupovinaDTO.convert(k), HttpStatus.CREATED);
//			}
//			
//		}
		
		@RequestMapping(method=RequestMethod.PUT,
				value="/{id}")
		public ResponseEntity<TrkaDTO> edit(
				@PathVariable Long id,
				@RequestBody TrkaDTO izmenjen){
			
			if(!id.equals(izmenjen.getId())){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			Trka trka = toTrka.convert(izmenjen); 
			trkaService.save(trka);
			
			return new ResponseEntity<>(toTrkaDTO.convert(trka),
					HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.DELETE,
				value="/{id}")
		public ResponseEntity<TrkaDTO> delete(@PathVariable Long id){
			trkaService.remove(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
}

//---------------------------------------------
//import java.util.List;
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
//import com.primerimenytomeny.primeri.model.Trkac;
//
//import com.primerimenytomeny.primeri.model.Trka;
//import com.primerimenytomeny.primeri.service.TrkacService;
//import com.primerimenytomeny.primeri.service.TrkaService;
//import com.primerimenytomeny.primeri.support.TrkacTOTrkacDTO;
//import com.primerimenytomeny.primeri.support.TrkaTOTrkaDTO;
//import com.primerimenytomeny.primeri.web.dto.TrkacDTO;
//import com.primerimenytomeny.primeri.web.dto.TrkaDTO;
//
//@RestController
//@RequestMapping(value = "/api/trka")
//public class ApiTrkaController {// pivarai
//	@Autowired
//	private TrkaService trkaService;
//	@Autowired
//	private TrkacService trkacService;
//	@Autowired
//	private TrkaTOTrkaDTO toDTO;
//	@Autowired
//	private TrkacTOTrkacDTO toTrkacDTO;
//
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List<TrkaDTO>> get() {
//		return new ResponseEntity<>(toDTO.convert(trkaService.findAll()), HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<TrkaDTO> get(@PathVariable Long id) {
//
//		Trka trka = trkaService.findOne(id);
//
//		if (trka == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//
//		return new ResponseEntity<>(toDTO.convert(trka), HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/{trkaId}/trkaci")
//	public ResponseEntity<List<TrkacDTO>> trkacTrka(@PathVariable Long trkaId,
//
//			@RequestParam(defaultValue = "0") int pageNum) {
//		Page<Trkac> trkac = trkacService.findByTrkaId(pageNum, trkaId);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("totalPages", Integer.toString(trkac.getTotalPages()));
//		return new ResponseEntity<>(toTrkacDTO.convert(trkac.getContent()), headers, HttpStatus.OK);
//	}
//}
//
