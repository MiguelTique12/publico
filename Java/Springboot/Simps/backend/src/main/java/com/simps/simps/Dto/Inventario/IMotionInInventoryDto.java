package com.simps.simps.Dto.Inventario;

import java.time.LocalDateTime;

public interface IMotionInInventoryDto {

	String getNameElement();
	
	String getNameClassroom();
	
	LocalDateTime getMotionDate();
	
	Integer getQuantityOne();
	
	Integer getQuantityTwo();
	
	Integer getQuantityThree();
}
