package com.simps.simps.Dto.Seguridad;

public interface IModulesDto {
	
	Long getId();
	
	/**
     * Obtiene el código del modulo.
     *
     * @return el estado del modulo
     */
    String getCode();
    
    /**
     * Obtiene la ruta del modulo.
     *
     * @return la ruta del modulo
     */
    String getRoute();
    
    /**
     * Obtiene la etiqueta del modulo.
     *
     * @return la etiqueta del modulo
     */
    String getDescription();
    
    /**
     * Obtiene el estado del modulo.
     *
     * @return el estado del modulo
     */
    Boolean getState();
    
    /**
     * Obtiene la cantidad del modulo.
     *
     * @return la cantidad del modulo
     */
    String getIcon();
    
    Integer getQuantity();
}
