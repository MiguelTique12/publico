package com.simps.simps.IService.Asistencia;

import com.simps.simps.Dto.Asistencia.ICursesFilterDto;
import com.simps.simps.Dto.Asistencia.IFilterStudentsDto;
import com.simps.simps.Dto.Asistencia.ISearchAttendancesDto;
import com.simps.simps.Entity.Asistencia.RecordsAttendances;

import java.util.List;

public interface IRecordsAttendancesService{
	
	 public RecordsAttendances save(RecordsAttendances recordsAttendances, String rfid) throws Exception;

	 public List<ISearchAttendancesDto> getAttendances(String date, String subject, Long personId); 
	 
	public List<ISearchAttendancesDto> getAllAttendances(Long personId);
	
	public List<ICursesFilterDto> getCursesFilter(Long gradeId);
	
	public List<IFilterStudentsDto> getNameStudent(Long curseId);
	 
}
