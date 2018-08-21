package com.primerimenytomeny.primeri.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.primerimenytomeny.primeri.model.Manifestacija;
import com.primerimenytomeny.primeri.model.TakmicenjaGodina;

@Repository
public interface TakmicenjaGodinaRepository extends JpaRepository<TakmicenjaGodina, Long> {

	//Page<TakmicenjaGodina> findByTakmicenjaGodinaId(Long mestoId, Pageable pageRequest);
	
	@Query("SELECT k FROM TakmicenjaGodina k WHERE "
			+ "(:nazivTrke IS NULL or k.nazivTrke like :nazivTrke ) "
			)

	Page<TakmicenjaGodina> pretraga(
			@Param("nazivTrke") String nazivTrke, 
			Pageable pageRequest);
}
