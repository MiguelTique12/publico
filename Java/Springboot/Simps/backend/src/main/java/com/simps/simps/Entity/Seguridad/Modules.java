package com.simps.simps.Entity.Seguridad;


import com.simps.simps.Entity.BaseModel.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="modules")
public class Modules extends BaseModel{
	
	@NotBlank(message = "El código no puede estar en blanco")
    @Pattern(regexp = "^[0-9]+$", message = "El código debe contener solo números")
	@Column (name = "code", nullable = false, unique = true, length = 10)
	private String code;
	
	
	@NotBlank(message = "El icono no puede estar en blanco")
	@Column(name = "icon", length = 150, unique = true)
	private String icon;
	
	
	@NotBlank(message = "La etiqueta no puede estar en blanco")
	@Column(name = "route", length = 150, unique = true)
	private String route;
	
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "La descripción debe contener solo letras")
    @NotBlank(message = "La descripción no puede estar en blanco")
	@Column (name = "description", nullable = false, length = 50, unique = true)
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
	
}
