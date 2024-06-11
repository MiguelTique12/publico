package com.simps.simps.Dto.Inventario;

public interface IBrandsDto {

	Long getId();

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
	String getNit();

	/**
	 * Obtiene el primer apellido de persona.
	 *
	 * @return el primer apellido de persona
	 */
	String getDescription();

	/**
	 * Obtiene la cantidad de persona.
	 *
	 * @return la cantidad de persona
	 */
	Boolean getState();

	/**
	 * Obtiene la cantidad de persona.
	 *
	 * @return la cantidad de persona
	 */
	Integer getQuantity();

}
