package com.simps.simps.Dto.Asistencia;

import java.time.LocalDateTime;
import java.time.LocalTime;


public interface IRecordsAttendancesDto {

	String getTypeAssistance();
	
	LocalDateTime getDate();
	
	LocalTime getStartTime();
	
	LocalTime getEndTime();
	
	Long getPersonId();
	
	Long getSubjectId();
	
	Integer getQuantity();
	
}
