package com.simps.simps.Entity.Parametrizacion;

import com.simps.simps.Entity.BaseModel.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "techniques")
public class Techniques extends BaseModel{

	@NotBlank(message = "El código no puede estar en blanco")
	@Pattern(regexp = "^[0-9]+$", message = "El código debe contener solo números")
	@Column(name= "code", nullable = false, length = 10, unique=true)
	private String code;
	
	@NotBlank(message = "El nombre no puede estar en blanco")
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre debe contener solo letras")  
	@Column(name= "name", nullable = false, length = 50, unique=true)
	private String name;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
