package com.primerimenytomeny.primeri.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.primerimenytomeny.primeri.model.Manifestacija;


@Repository
public interface ManifestacijaRepository extends JpaRepository<Manifestacija, Long> { 
	
	Page<Manifestacija> findByTakmicenjaGodinaId(Long mestoId, Pageable pageRequest);
	
	@Query("SELECT k FROM Manifestacija k WHERE "
			+ "(:naziv IS NULL or k.naziv like :naziv ) AND "
			+ "(:datumOdrzavanja IS NULL OR k.datumOdrzavanja  like :datumOdrzavanja ) AND "
			+ "(:mestoOdrzavanja IS NULL OR k.mestoOdrzavanja like :mestoOdrzavanja)"
			)

	Page<Manifestacija> pretraga(
			@Param("naziv") String naziv, 
			@Param("datumOdrzavanja") String datumOdrzavanja,
			@Param("mestoOdrzavanja") String mestoOdrzavanja,
			Pageable pageRequest);
	

}
