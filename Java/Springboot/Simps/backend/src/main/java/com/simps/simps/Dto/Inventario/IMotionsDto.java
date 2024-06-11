package com.simps.simps.Dto.Inventario;

import java.time.LocalDateTime;

public interface IMotionsDto {
	
	Long getId();
	
	Boolean getState();

	Integer getAmount();

	LocalDateTime getDate();

	Long getClassroomsDestinationId();

	Long getElementClassroomId();
	
	Long getElementId();

	Long getUserId();

	Long getQuantity();
}
