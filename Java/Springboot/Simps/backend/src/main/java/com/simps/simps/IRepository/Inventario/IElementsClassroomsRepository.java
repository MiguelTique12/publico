package com.simps.simps.IRepository.Inventario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Inventario.IElementsClassroomsDto;
import com.simps.simps.Entity.Inventario.ElementsClassrooms;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IElementsClassroomsRepository  extends IObjectTRepository<ElementsClassrooms, Long>{
	
	@Query(value = "SELECT ec.id, ec.amount, el.name as elementId, cl.name as classroomId, ec.state " +
	        "FROM elements_classrooms ec "
	        + "inner join elements el on ec.element_id = el.id "
	        + "inner join classrooms cl on ec.classroom_id = cl.id " +
	        "WHERE (:search IS NULL OR CAST(ec.amount AS TEXT) LIKE '%' || :search || '%'"
	        + " OR el.name LIKE '%' || :search || '%' OR cl.name LIKE '%' || :search || '%')",
	nativeQuery = true)
	Page<IElementsClassroomsDto> getDatatable(Pageable pageable, String search);

	@Query(value = "SELECT "
		+ " 	count(element_id) as quantity "
		+ " FROM "
		+ " 	elements_classrooms "
		+ " WHERE classroom_id = :classroomId "
		+ " AND amount = :amount"
		+ " AND element_id = :elementId",nativeQuery = true)
	Optional<IElementsClassroomsDto> getValidate(Long classroomId, Integer amount, Long elementId);

}