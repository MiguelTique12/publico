package com.simps.simps.Dto.Inventario;

import java.time.LocalTime;

public interface IRandomCodesDto {

	Long getId();
	
	Integer getCode();
	
	LocalTime getDateCreation();
	
	Boolean getState();
	
	Boolean setAccess();
}
