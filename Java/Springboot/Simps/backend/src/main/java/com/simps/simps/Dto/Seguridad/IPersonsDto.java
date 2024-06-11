package com.simps.simps.Dto.Seguridad;

public interface IPersonsDto {
	
	Long getId();
	
	/**
     * Obtiene el primer nombre de persona.
     *
     * @return el primer nombre de persona
     */
	String getFirstName();
	
    /**
     * Obtiene el segundo nombre de persona.
     *
     * @return el segundo nombre de persona
     */
	String getSecondName();
	
    /**
     * Obtiene el primer apellido de persona.
     *
     * @return el primer apellido de persona
     */
	String getFirstLastName();
	
    /**
     * Obtiene el segundo apellido de persona.
     *
     * @return el segundo apellido de persona
     */
	String getSecondLastName();
	
    /**
     * Obtiene el tipo de documento de persona.
     *
     * @return el tipo de documento de persona
     */
	String getDocumentType();
	
    /**
     * Obtiene el documento de persona.
     *
     * @return el documento de persona
     */
	String getDocument();

    /**
     * Obtiene la edad de persona.
     *
     * @return la edad de persona
     */
	Byte getAge();
	
    /**
     * Obtiene el genero de persona.
     *
     * @return el genero de persona
     */
	Boolean getGender();
	
    /**
     * Obtiene el correo de persona.
     *
     * @return el correo de persona
     */
	String getMail();
	
	String getRfid();
	
	 /**
     * Obtiene la huella de la persona.
     *
     * @return la huella de la persona
     */
	String getFingerprint();
	
    /**
     * Obtiene el numero de telefono de persona.
     *
     * @return el numero de telefono de persona
     */
	String getPhoneNumber();
	
    /**
     * Obtiene la dirección de persona.
     *
     * @return la dirección de persona
     */
	String getAddress();
	
    Boolean getState();
    /**
     * Obtiene la cantidad de persona.
     *
     * @return la cantidad de persona
     */
    Integer getQuantity();
    
	
}
