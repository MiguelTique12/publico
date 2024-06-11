package com.simps.simps.IRepository.Parametrizacion;


import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Parametrizacion.IFloorsDto;
import com.simps.simps.Entity.Parametrizacion.Floors;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IFloorsRepository extends IObjectTRepository<Floors, Long>{

	@Query(value = "SELECT fl.id, fl.floor_number as floorNumber, he.name as headquaterId, fl.state " +
	        "FROM floors fl " +
	        "INNER JOIN headquaters he ON fl.headquater_id = he.id " +
	        "WHERE (:search IS NULL OR CAST(fl.floor_number AS text) LIKE '%' || :search || '%' " +
	        "OR he.name LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<IFloorsDto> getDatatable(Pageable pageable, String search);


	
	@Query(value = "SELECT "
			+ " 	count(floor_number) as quantity "
			+ " FROM "
			+ " 	floors "
			+ " WHERE floor_number = :floorNumber ", nativeQuery = true)
    Optional<IFloorsDto> getValidate(Integer floorNumber);
	
}
