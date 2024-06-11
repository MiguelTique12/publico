package com.simps.simps.Dto.Inventario;

public interface ITypesElementsDto {
	
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

			Long getQuantity();
			
		    
		}



