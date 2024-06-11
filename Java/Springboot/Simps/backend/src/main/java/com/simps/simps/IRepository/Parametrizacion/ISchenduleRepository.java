package com.simps.simps.IRepository.Parametrizacion;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Parametrizacion.ISchendulesDto;
import com.simps.simps.Entity.Parametrizacion.Schendules;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface ISchenduleRepository extends IObjectTRepository<Schendules, Long> {

	@Query(value = "SELECT id, " + "CAST(start_time AS VARCHAR) as startTime, "
			+ " CAST(end_time AS VARCHAR) as endTime, " + "state, day " + "FROM schendules " + "WHERE (:search IS NULL "
			+ " OR CAST(start_time AS VARCHAR) LIKE '%' || :search || '%' "
			+ " OR CAST(end_time AS VARCHAR) LIKE '%' || :search || '%' OR day LIKE '%' || :search || '%')", nativeQuery = true)
	Page<ISchendulesDto> getDatatable(Pageable pageable, String search);

	@Query(value = "SELECT " + " 	count(start_time) as quantity " + " FROM " + " 	schendules "
			+ " WHERE start_time = :startTime " + " AND end_time = :endTime ", nativeQuery = true)
	Optional<ISchendulesDto> getValidate(LocalTime startTime, LocalTime endTime);

}
