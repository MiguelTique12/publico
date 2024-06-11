package com.simps.simps.Dto.Inventario;

import java.time.LocalDateTime;

public interface IDamagesLossesDto {

	Long getId();

	/**
	 * Obtiene el primer nombre de persona.
	 *
	 * @return el primer nombre de persona
	 */
	Integer getAmount();

	/**
	 * Obtiene el segundo nombre de persona.
	 *
	 * @return el segundo nombre de persona
	 */
	LocalDateTime getDate();

	/**
	 * Obtiene el segundo apellido de persona.
	 *
	 * @return el segundo apellido de persona
	 */
	String getDescription();

	/**
	 * Obtiene la cantidad de persona.
	 *
	 * @return la cantidad de persona
	 */
	Boolean getState();

	/**
	 * Obtiene el tipo de documento de persona.
	 *
	 * @return el tipo de documento de persona
	 */
	Long getElementClassroomsId();

	Long getQuantity();

}
