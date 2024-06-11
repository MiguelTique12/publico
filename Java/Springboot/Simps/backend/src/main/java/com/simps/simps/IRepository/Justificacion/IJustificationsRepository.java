package com.simps.simps.IRepository.Justificacion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Justifications.IJustificationsDto;
import com.simps.simps.Dto.Justifications.ISubjectsIdDto;
import com.simps.simps.Entity.Justificacion.Justifications;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IJustificationsRepository extends IObjectTRepository<Justifications, Long>{

	
	@Query(value = " SELECT su.id, "
			+ "		su.name "
			+ "			 	FROM users u "
			+ "			 		INNER JOIN students s ON u.id = s.user_id  "
			+ "			 		INNER JOIN curses cu ON s.curse_id = cu.id  "
			+ "			 		INNER JOIN schendules_subjects ss ON cu.id = ss.curse_id  "
			+ "			 		INNER JOIN schendules sc ON ss.schendule_id = sc.id  "
			+ "			 		INNER JOIN subjects su ON ss.subject_id = su.id  "
			+ "			 	WHERE sc.day = :day AND u.id = :userId ", nativeQuery = true)
	List<ISubjectsIdDto> getSubjectId(String day, Long userId);
	
	
	@Query(value = "SELECT subject, "
			+ "	   		   date, "
			+ "	   		   subject_id as subjectId, "
			+ "	           description, "
			+ "	           type_assistance as typeAssistance, "
			+ "	           file "
			+ "     FROM justifications "
			+ "     WHERE (:date IS NULL OR date = TO_DATE(:date, 'YYYY-MM-DD')) AND   "
			+ "	          (:subjectId is null or subject_id = :subjectId) AND"
			+ "			   student_id = :studentId ", nativeQuery = true)
	List<IJustificationsDto> getJustifications(String date, Long subjectId, Long studentId);
	
}
