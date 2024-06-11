package com.simps.simps.IRepository.Parametrizacion;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Parametrizacion.IClassroomsDto;
import com.simps.simps.Entity.Parametrizacion.Classrooms;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IClassroomsRepository extends IObjectTRepository<Classrooms, Long>{

	@Query(value = "SELECT cl.id, cl.name, fl.floor_number as floorId, cl.state " +
            "FROM classrooms cl "
            + "	inner join floors fl on cl.floor_id = fl.id " +
            "WHERE (:search IS NULL OR cl.name LIKE '%' || :search || '%' "
            + " OR CAST(fl.floor_number AS text) LIKE '%' || :search || '%') ",
	    nativeQuery = true)
	Page<IClassroomsDto> getDatatable(Pageable pageable, String search);


	
	@Query(value = "SELECT "
			+ " 	count(name) as quantity "
			+ " FROM "
			+ " 	classrooms "
			+ " WHERE name = :name", nativeQuery = true)
    Optional<IClassroomsDto> getValidate(String name);
	
}
