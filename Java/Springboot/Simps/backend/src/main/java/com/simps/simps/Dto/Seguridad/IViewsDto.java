package com.simps.simps.Dto.Seguridad;

public interface IViewsDto {	
	
	Long getId();
	
	 /**
     * Obtiene el código de la vista.
     *
     * @return el código de la vista
     */
    String getCode();
    
    /**
     * Obtiene la ruta de la vista.
     *
     * @return la ruta de la vista
     */
    String getRoute();
   
    /**
     * Obtiene la etiqueta de la vista.
     *
     * @return la etiqueta de la vista
     */
    String getLabel();
    
    /**
     * Obtiene el módulo asociado.
     *
     * @return el módulo asociado
     */
    String getModule();
    
    /**
     * Obtiene el estado de la vista.
     *
     * @return el estado de la vista
     */
    Boolean getState();
    
    /**
     * Obtiene la cantidad de la vista.
     *
     * @return la cantidad de la vista
     */
    Integer getQuantity();
}
