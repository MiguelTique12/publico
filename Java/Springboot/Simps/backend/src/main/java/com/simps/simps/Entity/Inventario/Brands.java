package com.simps.simps.Entity.Inventario;


import com.simps.simps.Entity.BaseModel.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "brands")
public class Brands  extends BaseModel{
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre debe contener solo letras")
    @NotBlank(message = "El nombre no puede estar en blanco")
	@Column (name = "name", nullable = false,unique = true, length = 50)
	private String name;
	
	@NotBlank(message = "El nit no puede estar en blanco")
    @Pattern(regexp = "^[0-9]+$", message = "El nit debe contener solo números")
	@Column (name = "nit", nullable = false, unique = true, length = 10)
	private String nit;
	
	
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

	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	

	
	
	
	
}
