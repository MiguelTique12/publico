package com.simps.simps.IRepository.Parametrizacion;


import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Parametrizacion.ICursesDto;
import com.simps.simps.Entity.Parametrizacion.Curses;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface ICursesRepository extends IObjectTRepository<Curses, Long>{

	@Query(value = "SELECT cu.id, cu.name, gr.name as gradeId, cl.name as classroomId, te.name as techniqueId, cu.state " +
            "FROM curses cu "
            + " inner join grades gr on cu.grade_id = gr.id "
            + " inner join classrooms cl on cu.classrooms_id = cl.id "
            + "	inner join techniques te on cu.technique_id = te.id " +
            "WHERE (:search IS NULL OR cu.name LIKE '%' || :search || '%' "
            + " OR cl.name LIKE '%' || :search || '%' OR te.name LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<ICursesDto> getDatatable(Pageable pageable, String search);


	
	@Query(value = "SELECT "
		+ " 	count(grade_id) as quantity "
		+ " FROM "
		+ " 	curses "
		+ " WHERE name = :name "
		+ " OR classrooms_id = :classroomsId ", nativeQuery = true)
	Optional<ICursesDto> getValidate(String name, Long classroomsId);
		
}
