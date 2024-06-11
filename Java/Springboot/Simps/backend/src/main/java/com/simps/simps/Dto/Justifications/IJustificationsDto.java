package com.simps.simps.Dto.Justifications;

import java.time.LocalDate;

public interface IJustificationsDto {

	String getSubject();
	
	LocalDate getDate();
	
	Long getSubjectId();
	
	String getDescription();
	
	String getTypeAssistance();
	
	String getFile();
	
	Integer getQuantity();
	
}
