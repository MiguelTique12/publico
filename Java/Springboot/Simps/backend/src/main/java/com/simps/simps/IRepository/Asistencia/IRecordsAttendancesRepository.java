package com.simps.simps.IRepository.Asistencia;

import java.time.LocalTime;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Asistencia.ICursesFilterDto;
import com.simps.simps.Dto.Asistencia.IFilterStudentsDto;
import com.simps.simps.Dto.Asistencia.IHoursClassesDto;
import com.simps.simps.Dto.Asistencia.IRecordsAttendancesDto;
import com.simps.simps.Dto.Asistencia.ISearchAttendancesDto;
import com.simps.simps.Entity.Asistencia.RecordsAttendances;
import com.simps.simps.Entity.Parametrizacion.Subjects;
import com.simps.simps.Entity.Seguridad.Persons;

public interface IRecordsAttendancesRepository  extends JpaRepository<RecordsAttendances, Long>{

	//Valida que un estudiante no pueda marcar dos veces asistencia en una misma clase
	@Query(value = "SELECT COUNT(person_id) as quantity  "
			+ "	FROM records_attendances  "
			+ "	WHERE type_assistance = :typeAssistance  "
			+ "    	AND TO_CHAR(date, 'YYYY-MM-DD') = :date  "
			+ "    	AND EXTRACT(HOUR FROM date) * 3600 + EXTRACT(MINUTE FROM date) * 60 + EXTRACT(SECOND FROM date) >= EXTRACT(HOUR FROM CAST(:startTime AS TIME)) * 3600 + EXTRACT(MINUTE FROM CAST(:startTime AS TIME)) * 60 + EXTRACT(SECOND FROM CAST(:startTime AS TIME)) "
			+ "		AND EXTRACT(HOUR FROM date) * 3600 + EXTRACT(MINUTE FROM date) * 60 + EXTRACT(SECOND FROM date) <= EXTRACT(HOUR FROM CAST(:endTime AS TIME)) * 3600 + EXTRACT(MINUTE FROM CAST(:endTime AS TIME)) * 60 + EXTRACT(SECOND FROM CAST(:endTime AS TIME))"
			+ "    	AND person_id = :personId  "
			+ "    	AND subject_id = :subjectId", nativeQuery = true)
    Optional<IRecordsAttendancesDto> getValidate(String typeAssistance, String date, LocalTime startTime, LocalTime endTime, Long personId, Long subjectId);

	//Obtiene a la persona según su tarjeta RFID 
	@Query(value = "SELECT id FROM Persons WHERE rfid = :rfid", nativeQuery = true)
	Optional<Persons> getPersonRFID(String rfid);
	
	//Obtiene el id de la asistencia del estudiante en esa hora y día en especifico
	@Query(value = " SELECT su.id "
			+ "	FROM persons pe "
			+ "		INNER JOIN users u ON pe.id = u.person_id "
			+ "		INNER JOIN students s ON u.id = s.user_id "
			+ "		INNER JOIN curses cu ON s.curse_id = cu.id "
			+ "		INNER JOIN schendules_subjects ss ON cu.id = ss.curse_id "
			+ "		INNER JOIN schendules sc ON ss.schendule_id = sc.id "
			+ "		INNER JOIN subjects su ON ss.subject_id = su.id "
			+ "	WHERE :horaIngresada between TO_CHAR(EXTRACT(HOUR FROM sc.start_time), 'FM00') AND TO_CHAR(EXTRACT(HOUR FROM sc.end_time), 'FM00') AND sc.day = :day AND pe.id = :personId limit 1", nativeQuery = true)
	Optional<Subjects> getSubjectId(String horaIngresada, String day, Long personId );
	
	//Metodo que mira la hora a la que inicia la clase y así validar si ingresó a tiempo o no
	@Query(value = " SELECT sc.start_time as startTime,"
			+ "				sc.end_time as endTime "
			+ " 	FROM persons pe  "
			+ " 		INNER JOIN users u ON pe.id = u.person_id  "
			+ " 		INNER JOIN students s ON u.id = s.user_id  "
			+ " 		INNER JOIN curses cu ON s.curse_id = cu.id  "
			+ " 		INNER JOIN schendules_subjects ss ON cu.id = ss.curse_id  "
			+ " 		INNER JOIN schendules sc ON ss.schendule_id = sc.id  "
			+ " 		INNER JOIN subjects su ON ss.subject_id = su.id  "
			+ " 	WHERE :horaIngresada between TO_CHAR(EXTRACT(HOUR FROM sc.start_time), 'FM00') AND TO_CHAR(EXTRACT(HOUR FROM sc.end_time), 'FM00')  AND sc.day = :day AND pe.id = :personId limit 1", nativeQuery = true)
	Optional<IHoursClassesDto> getStartTime(String horaIngresada, String day, Long personId);
	
	//METODO PARA CONSULTAR ASISTENCIA POR ASIGNARTURA Y/O FECHA
	
	@Query(value = "SELECT ra.date,  "
			+ "			       ra.type_assistance as typeAssistance,  "
			+ "			 	   ra.description,  "
			+ "			 	   s.name,  "
			+ "			 	   pe.first_name as firstName,  "
			+ "			 	   pe.second_name as secondName,  "
			+ "			 	   pe.first_last_name  as firstLastName, "
			+ "				   TO_CHAR(ra.date, 'HH24:MI') as entryTime"
			+ "			 	   FROM records_attendances ra  "
			+ "			 	   INNER JOIN subjects s ON ra.subject_id = s.id  "
			+ "			 	   INNER JOIN teachers te  ON s.teacher_id = te.id  "
			+ "			 	   INNER JOIN users u ON te.user_id = u.id   "
			+ "			 	   INNER JOIN persons pe ON u.person_id = pe.id  "
			+ "			 	   WHERE (:date is null or TO_CHAR(ra.date, 'YYYY-MM-DD') = :date) AND  "
			+ "				   (:subject is null or s.name LIKE '%' || :subject || '%') AND"
			+ "					ra.person_id = :personId ", nativeQuery = true)
	List<ISearchAttendancesDto> getAttendances(String date, String subject, Long personId);
	
	
	//METODO PARA CONSULTAR TODAS LAS ASISTENCIAS DEL ESTUDIANTE
	
	@Query(value = "SELECT ra.date,    "
			+ "			 			 			       ra.type_assistance as typeAssistance,    "
			+ "			 			 			 	   ra.description,    "
			+ "			 			 			 	   s.name,    "
			+ "			 			 			 	   pe.first_name as firstName,    "
			+ "			 			 			 	   pe.second_name as secondName,    "
			+ "			 			 			 	   pe.first_last_name  as firstLastName, "
			+ "										   TO_CHAR(ra.date, 'HH24:MI') as entryTime "
			+ "			 			 			 	   FROM records_attendances ra    "
			+ "			 			 			 	   INNER JOIN subjects s ON ra.subject_id = s.id    "
			+ "			 			 			 	   INNER JOIN teachers te  ON s.teacher_id = te.id    "
			+ "			 			 			 	   INNER JOIN users u ON te.user_id = u.id     "
			+ "			 			 			 	   INNER JOIN persons pe ON u.person_id = pe.id    "
			+ "			 			 			 	   WHERE ra.person_id = :personId", nativeQuery = true)
	List<ISearchAttendancesDto> getAllAttendances(Long personId);
	
//	Metodo para consultar los cursos de un grado
	
	@Query(value = "select id as curseId, name as curseName from curses where grade_id = :gradeId", nativeQuery = true)
	List<ICursesFilterDto> getCursesFilter(Long gradeId);
	
//	Metodo para consultar el nombre de los estudiantes de un salon especifico
	@Query(value = "select CONCAT(pe.first_name, ' ', pe.second_name, ' ', pe.first_last_name) as personName,"
			+ " pe.id as personId "
			+ "from students s  "
			+ "inner join users u on s.user_id = u.id "
			+ "inner join persons pe on u.person_id = pe.id "
			+ "where s.curse_id = :curseId ", nativeQuery = true)
	List<IFilterStudentsDto> getNameStudent(Long curseId);
	
}
