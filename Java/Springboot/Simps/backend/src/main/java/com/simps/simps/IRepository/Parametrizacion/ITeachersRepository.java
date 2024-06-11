package com.simps.simps.IRepository.Parametrizacion;


import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Parametrizacion.ITeachersDto;
import com.simps.simps.Entity.Parametrizacion.Teachers;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface ITeachersRepository extends IObjectTRepository<Teachers, Long>{

	@Query(value = "SELECT te.id, te.contract_date as contractDate, CONCAT(pe.first_name, ' ', pe.second_name, ' ', pe.first_last_name) " +
			" as userId, te.state, cu.name as curseName " +
            " FROM teachers te " +
            " INNER JOIN users u on te.user_id = u.id "
            + " inner join persons pe on u.person_id = pe.id"
            + " inner join curses_teachers ct on te.id = ct.teacher_id"
            + " inner join curses cu on ct.curse_id = cu.id " +
            "WHERE (:search IS NULL OR DATE(te.contract_date) = TO_DATE(:search, 'yyyy-MM-dd') OR u.username LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<ITeachersDto> getDatatable(Pageable pageable, String search);


	
	@Query(value = "SELECT "
			+ " 	count(user_id) as quantity "
			+ " FROM "
			+ " 	teachers "
			+ " WHERE user_id = :userId ", nativeQuery = true)
    Optional<ITeachersDto> getValidate(Long userId);
	
}
