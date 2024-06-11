package com.simps.simps.Dto.Inventario;


import java.sql.Timestamp;


public interface IInventoryFilterDto {

	Long getInventoryId();
	
	Timestamp getDate();
	
	String getDescription();
	
	String getFirstName();
	
	String getSecondName();
	
	String getFirstLastName();
	
	String getNameElement();
	
	String getBrandId();
	
	String getDescriptionElement();
	
	String getImage();
	
	Integer getAmount();
	
	Long getClassroomId();
	
}
