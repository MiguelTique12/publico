package com.simps.simps.Dto.Inventario;


import java.sql.Time;
import java.util.Date;

public interface IInventoriesDto {

	Long getId();

	Boolean getState();

	/**
	 * Obtiene el primer nombre de persona.
	 *
	 * @return el primer nombre de persona
	 */
	Date getDate();

	/**
	 * Obtiene el segundo nombre de persona.
	 *
	 * @return el segundo nombre de persona
	 */
	Time getHour();

	/**
	 * Obtiene el primer apellido de persona.
	 *
	 * @return el primer apellido de persona
	 */
	Long getUserId();

	/**
	 * Obtiene el segundo apellido de persona.
	 *
	 * @return el segundo apellido de persona
	 */
	Long getElementClassroomsId();

	String getDescription();

	Long getQuantity();

}
