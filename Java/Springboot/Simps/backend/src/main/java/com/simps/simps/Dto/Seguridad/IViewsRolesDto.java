package com.simps.simps.Dto.Seguridad;

public interface IViewsRolesDto {
	
	 /**
     * Obtiene la vista asociada.
     *
     * @return la vista asociada
     */
	Integer getView();
	
	 /**
     * Obtiene el rol asociado.
     *
     * @return el rol asociado
     */
	Integer getRole();
	
    /**
     * Obtiene el estado de la vistaRol.
     *
     * @return el estado de la vistaRol
     */
    Boolean getState();
	
    /**
     * Obtiene la cantidad de la vista-rol.
     *
     * @return la cantidad de la vista-rol
     */
    Integer getQuantity();
	
}
