package com.simps.simps.Dto.Seguridad;

public interface IRolesDto {
	
	Long getId();
	
	  /**
     * Obtiene el código del rol.
     *
     * @return el código del rol
     */
	String getCode();
	
    /**
     * Obtiene la descripción del rol.
     *
     * @return la descripción del rol
     */
    String getDescription();   
    
    /**
     * Obtiene el estado del rol.
     *
     * @return el estado del rol
     */
    Boolean getState();
   
    /**
     * Obtiene la cantidad del rol.
     *
     * @return la cantidad del rol
     */
    Integer getQuantity();
    
    String getViewId();
}
