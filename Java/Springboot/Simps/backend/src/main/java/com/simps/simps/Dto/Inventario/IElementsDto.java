package com.simps.simps.Dto.Inventario;

public interface IElementsDto {

	Long getId();

	Boolean getState();

	/**
	 * Obtiene el primer nombre de persona.
	 *
	 * @return el primer nombre de persona
	 */
	String getName();

	/**
	 * Obtiene el segundo nombre de persona.
	 *
	 * @return el segundo nombre de persona
	 */
	String getDescription();

	/**
	 * Obtiene el primer apellido de persona.
	 *
	 * @return el primer apellido de persona
	 */
	String getBrandId();

	/**
	 * Obtiene el segundo apellido de persona.
	 *
	 * @return el segundo apellido de persona
	 */
	String getTypeElementId();
	String getImage();

	Long getQuantity();

}
