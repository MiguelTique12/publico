package com.simps.simps.Dto.Parametrizacion;

import java.util.Date;

public interface ITeachersDto {

	Long getId();
	
	Date getContractDate();
	
	String getUserId();

    Boolean getState();
	
	Long getQuantity();
	
	String getCurseName();

}
