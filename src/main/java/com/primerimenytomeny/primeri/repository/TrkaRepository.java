package com.primerimenytomeny.primeri.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.primerimenytomeny.primeri.model.Trka;

@Repository
public interface TrkaRepository extends JpaRepository<Trka, Long> {

	
	@Query("SELECT k FROM Trka k WHERE "
			+ "(:duzinaStaze IS NULL or k.duzinaStaze like :duzinaStaze ) AND "
			+ "(:kategorija IS NULL OR k.kategorija  like :kategorija ) "
			)
	
	
	Page<Trka> pretraga(
			@Param("duzinaStaze") String nazivTrke, 
			@Param("kategorija") String kategorija, 
			Pageable pageRequest);
}

