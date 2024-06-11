package com.simps.simps.Service.Asistencia;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Asistencia.ICursesFilterDto;
import com.simps.simps.Dto.Asistencia.IFilterStudentsDto;
import com.simps.simps.Dto.Asistencia.IHoursClassesDto;
import com.simps.simps.Dto.Asistencia.IRecordsAttendancesDto;
import com.simps.simps.Dto.Asistencia.ISearchAttendancesDto;
import com.simps.simps.Entity.Asistencia.RecordsAttendances;
import com.simps.simps.Entity.Parametrizacion.Subjects;
import com.simps.simps.Entity.Seguridad.Persons;
import com.simps.simps.IRepository.Asistencia.IRecordsAttendancesRepository;
import com.simps.simps.IService.Asistencia.IRecordsAttendancesService;

import org.springframework.transaction.annotation.Transactional;


@Service
public class RecordsAttendancesService implements IRecordsAttendancesService{

	@Autowired
	private IRecordsAttendancesRepository repository;
	
	@Transactional(rollbackFor = Exception.class)
	public RecordsAttendances save(RecordsAttendances recordsAttendances, String rfid) throws Exception {
		
		Optional<Persons> getPersonId = repository.getPersonRFID(rfid);
	    recordsAttendances.setPersonId(getPersonId.get());
	    
		LocalDateTime today = LocalDateTime.now();
		DayOfWeek dayOfWeek = today.getDayOfWeek();
		String dayOfWeekStr = dayOfWeek.name();
		recordsAttendances.setDate(today);
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        String horaIngresada = formatter.format(today);
	    
    	Persons personIdPerson = getPersonId.get();
		Long personIdLong = personIdPerson.getId();
	
		Optional<Subjects> getSubjectId = repository.getSubjectId(horaIngresada, dayOfWeekStr, personIdLong);
		recordsAttendances.setSubjectId(getSubjectId.get());
		
		Subjects subjectIdSubject = getSubjectId.get();
		Long subjectIdLong = subjectIdSubject.getId();
		
		Persons persons = recordsAttendances.getPersonId();
		Long personId = persons.getId();
		
		Optional<IHoursClassesDto> getclassTime = repository.getStartTime(horaIngresada, dayOfWeekStr, personIdLong);
		LocalTime classStartTime = getclassTime.get().getStartTime();
		LocalTime classEndTime = classStartTime.plusMinutes(15); // Suma 15 minutos al inicio de la clase
		LocalTime finishClass = getclassTime.get().getEndTime();

		// Extrae la hora del LocalDateTime
		LocalTime studentTime = recordsAttendances.getDate().toLocalTime();

		if (studentTime.isBefore(classEndTime)) {
		    recordsAttendances.setDescription("Ingresó a tiempo");
		} else {
		    recordsAttendances.setDescription("Ingresó tarde");
		}
		
		DateTimeFormatter formatterToDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = formatterToDate.format(today);
		
		Optional<IRecordsAttendancesDto> op = repository.getValidate(recordsAttendances.getTypeAssistance().name(), formattedDate, classStartTime, finishClass, personId, subjectIdLong);
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Ya se ha registrado una asistencia para esta clase");
        }

    	
    	return repository.save(recordsAttendances);
	}

	@Override
	public List<ISearchAttendancesDto> getAttendances(String date, String subject, Long personId) {
		
		return repository.getAttendances(date, subject, personId);
	}

	@Override
	public List<ISearchAttendancesDto> getAllAttendances(Long personId) {
		// TODO Auto-generated method stub
		return repository.getAllAttendances(personId);
	}

	@Override
	public List<ICursesFilterDto> getCursesFilter(Long gradeId) {
		// TODO Auto-generated method stub
		return repository.getCursesFilter(gradeId);
	}

	@Override
	public List<IFilterStudentsDto> getNameStudent(Long curseId) {
		// TODO Auto-generated method stub
		return repository.getNameStudent(curseId);
	}

	
}
