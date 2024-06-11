package com.simps.simps.IRepository.Parametrizacion;


import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Parametrizacion.ITechniquesDto;
import com.simps.simps.Entity.Parametrizacion.Techniques;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface ITechniquesRepository extends IObjectTRepository<Techniques, Long>{

	@Query(value = "SELECT id, code, name, state " +
            "FROM techniques " +
            "WHERE (:search IS NULL OR code LIKE '%' || :search || '%' OR name LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<ITechniquesDto> getDatatable(Pageable pageable, String search);


	
	@Query(value = "SELECT "
			+ " 	count(code) as quantity "
			+ " FROM "
			+ " 	techniques "
			+ " WHERE code = :code "
			+ " OR name = :name ", nativeQuery = true)
    Optional<ITechniquesDto> getValidate(String code, String name);
	
}
