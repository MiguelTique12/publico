package com.simps.simps.IRepository.Parametrizacion;


import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Parametrizacion.IGradesDto;
import com.simps.simps.Entity.Parametrizacion.Grades;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IGradesRepository extends IObjectTRepository<Grades, Long>{

	@Query(value = "SELECT id, name, state " +
            "FROM grades " +
            "WHERE (:search IS NULL OR name LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<IGradesDto> getDatatable(Pageable pageable, String search);


	
	@Query(value = "SELECT "
		+ " 	count(name) as quantity "
		+ " FROM "
		+ " 	grades "
		+ " WHERE name = :name ", nativeQuery = true)
	Optional<IGradesDto> getValidate(String name);
	
}
