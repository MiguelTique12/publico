package com.simps.simps.Dto.Inventario;

public interface IElementsClassroomsDto {

	Long getId();

	Boolean getState();

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
	String getElementId();

	/**
	 * Obtiene el primer apellido de persona.
	 *
	 * @return el primer apellido de persona
	 */
	String getClassroomId();

	Long getQuantity();
}
