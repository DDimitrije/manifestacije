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


import com.primerimenytomeny.primeri.model.Trkac;

import com.primerimenytomeny.primeri.service.TrkacService;

import com.primerimenytomeny.primeri.support.TrkacDTOTOTrkac;
import com.primerimenytomeny.primeri.support.TrkacTOTrkacDTO;

import com.primerimenytomeny.primeri.web.dto.TrkacDTO;

	
	@RestController
	@RequestMapping("/api/trkaci")
	public class ApiTrkacController {
		@Autowired
		private TrkacService trkacService;
		@Autowired
		private TrkacTOTrkacDTO toTrkacDTO;
		@Autowired
		private TrkacDTOTOTrkac toTrkac;
		
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<TrkacDTO>> get(
				@RequestParam(required=false) String ime,
				@RequestParam(required=false) String prezime,
				@RequestParam(required=false) String pol,
				@RequestParam(required=false) String velicinaMajce,
				@RequestParam(required=false) String adresa,
				@RequestParam(required=false) String grad,
				@RequestParam(required=false) String drzava,
				@RequestParam(required=false) String najBoljeVreme,
				@RequestParam(required=false) String klub,
				//@RequestParam(required=false) String trka,
				//@RequestParam(required=false) Integer kolicina,
				//@RequestParam Boolean proveraNestalo, // dugme nestalo
				@RequestParam(defaultValue="0") int pageNum){
	
			
			Page<Trkac> trkaci;
			//(String naziv, Double minI, Double maxI, String trkaciraNaziv, Integer kolicina, int page
			if(ime != null || prezime != null || pol!= null || velicinaMajce != null || adresa!= null ||  grad!= null || drzava!= null ||  najBoljeVreme!= null ||  klub!= null ) { //
				trkaci = trkacService.pretraga(ime, prezime, pol,velicinaMajce, adresa, grad, drzava, najBoljeVreme, klub, pageNum); //nazivPivare,  kolicina, pageNum); //nazivPivare,
			//Dugme Nesta
			}else{
				trkaci = trkacService.findAll(pageNum);
//			if(proveraNestalo == true){
//					trkaci = trkacService.nestalo(pageNum);
//				}else{
//				trkaci = trkacService.findAll(pageNum);
//			}
		}	
			HttpHeaders headers = new HttpHeaders();
			headers.add("totalPages", Integer.toString(trkaci.getTotalPages()) );
			return  new ResponseEntity<>(
					toTrkacDTO.convert(trkaci.getContent()), headers, HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.GET, value="/{id}")
		public ResponseEntity<TrkacDTO> get(
				@PathVariable Long id){
			Trkac trkac = trkacService.findOne(id);
			
			if(trkac==null){
				return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(
					toTrkacDTO.convert(trkac),
					HttpStatus.OK);	
		}
		
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<TrkacDTO> add(
				@RequestBody TrkacDTO novaTrkac){
			
			Trkac trkac = toTrkac.convert(novaTrkac); 
			trkacService.save(trkac);
			
			return new ResponseEntity<>(toTrkacDTO.convert(trkac),
					HttpStatus.CREATED);
		}
		
//		@RequestMapping(method=RequestMethod.POST, value="/{id}/kupovina")
//		public ResponseEntity<KupovinaTrkacDTO> buy(@PathVariable Long id){
//			
//			KupovinaTrkac k = kupovinaTrkacService.buyABook(id);
//			
//			if(k == null) {
//				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//			}else {
//				return new ResponseEntity<>(toKupovinaDTO.convert(k), HttpStatus.CREATED);
//			}
//			
//		}
		
		@RequestMapping(method=RequestMethod.PUT, value="/{id}")
		public ResponseEntity<TrkacDTO> edit(
				@PathVariable Long id,
				@RequestBody TrkacDTO izmenjen){
			
			if(!id.equals(izmenjen.getId())){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			Trkac trkac = toTrkac.convert(izmenjen); 
			trkacService.save(trkac);
			
			return new ResponseEntity<>(toTrkacDTO.convert(trkac),
					HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
		public ResponseEntity<TrkacDTO> delete(@PathVariable Long id){
			trkacService.remove(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
}
