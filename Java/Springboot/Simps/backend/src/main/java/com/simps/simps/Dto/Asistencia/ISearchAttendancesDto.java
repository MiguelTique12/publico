package com.simps.simps.Dto.Asistencia;

import java.time.LocalDateTime;

public interface ISearchAttendancesDto {

	LocalDateTime getDate();
	
	String getTypeAssistance();
	
	String getDescription();
	
	String getName();
	
	String getFirstName();
	
	String getSecondName();
	
	String getFirstLastName();
	
	String getEntryTime();
	
	
	
}
