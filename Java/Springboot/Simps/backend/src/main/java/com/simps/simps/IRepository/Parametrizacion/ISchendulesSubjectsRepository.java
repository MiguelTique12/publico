package com.simps.simps.IRepository.Parametrizacion;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Parametrizacion.ISchendulesSubjectsDto;
import com.simps.simps.Entity.Parametrizacion.SchendulesSubjects;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface ISchendulesSubjectsRepository extends IObjectTRepository<SchendulesSubjects, Long> {

	@Query(value = "SELECT ss.id, sub.name as subjectId, CAST(sch.start_time AS VARCHAR) as startTime, CAST(sch.end_time AS VARCHAR) as endTime, cu.name as curseId, ss.state "
			+ " FROM schendules_subjects ss " + "	inner join subjects sub on ss.subject_id = sub.id "
			+ "	inner join schendules sch on ss.schendule_id = sch.id"
			+ "	inner join curses cu on ss.curse_id = cu.id "
			+ "WHERE (:search IS NULL OR sub.name LIKE '%' || :search || '%' OR CAST(sch.start_time AS VARCHAR) LIKE '%' || :search || '%' OR cu.name LIKE '%' || :search || '%'"
			+ " OR CAST(sch.end_time AS VARCHAR) LIKE '%' || :search || '%')", nativeQuery = true)
	Page<ISchendulesSubjectsDto> getDatatable(Pageable pageable, String search);

	@Query(value = "SELECT " + " 	count(subject_id) as quantity " + " FROM " + " 	schendules_subjects "
			+ " WHERE subject_id = :subjectId " + " AND schendule_id = :schenduleId "
			+ " AND curse_id = :curseId ", nativeQuery = true)
	Optional<ISchendulesSubjectsDto> getValidate(Long subjectId, Long schenduleId, Long curseId);

}
