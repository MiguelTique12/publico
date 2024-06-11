package com.simps.simps.Dto.Seguridad;


/**
 * Interfaz que define los métodos para acceder a los atributos de un objeto UserDto.
 */
public interface IUsersDto {

	Long getId();

	String getImage();
    /**
     * Obtiene el nombre de usuario.
     *
     * @return el nombre de usuario
     */
    String getUserName();

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario
     */
    String getPassword();
    
    /**
     * Obtiene la persona asociada al usuario.
     *
     * @return la persona asociada al usuario
     */
    String getPersonId();
    
    /**
     * Obtiene el estado del usuario.
     *
     * @return el estado del usuario
     */
    Boolean getState();
    
    /**
     * Obtiene la cantidad del usuario.
     *
     * @return la cantidad del usuario
     */
    Integer getQuantity();
    
    String getRoleId();
    
}
