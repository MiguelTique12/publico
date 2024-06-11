package com.simps.simps.IRepository.Parametrizacion;


import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Parametrizacion.ISubjectsDto;
import com.simps.simps.Entity.Parametrizacion.Subjects;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface ISubjectsRepository extends IObjectTRepository<Subjects, Long>{

	@Query(value = "SELECT su.id, su.code, su.name, pe.first_name as teacherId, su.state " +
            "FROM subjects su "
            + "	inner join teachers te on su.teacher_id = te.id "
            + "	inner join users u on te.user_id = u.id"
            + " inner join persons pe on u.person_id = pe.id " +
            "WHERE (:search IS NULL OR su.code LIKE '%' || :search || '%' OR su.name LIKE '%' || :search || '%' OR u.username LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<ISubjectsDto> getDatatable(Pageable pageable, String search);


	
	@Query(value = "SELECT "
			+ " 	count(code) as quantity "
			+ " FROM subjects "
			+ " WHERE code = :code "
			+ " OR name = :name ", nativeQuery = true)
    Optional<ISubjectsDto> getValidate(String code, String name);
	
}
