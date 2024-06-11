package com.simps.simps.Entity.Seguridad;


import com.simps.simps.Entity.BaseModel.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "views")
public class Views extends BaseModel{

	
	@NotBlank(message = "La etiqueta no puede estar en blanco")
	@Pattern(regexp = "^[0-9]+$", message = "El código del piso debe contener solo números")
	@Column(name = "code", length = 150, unique = true)
	private String code;
	
	@NotBlank(message = "La etiqueta no puede estar en blanco")
	@Column(name = "route", length = 150, unique = true)
	private String route;
	
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "La etiqueta debe contener solo letras")
    @NotBlank(message = "La etiqueta no puede estar en blanco")
	@Column (name = "label", nullable = false, length = 100)
	private String label;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "module_id", nullable = false)
    private Modules moduleId;

	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Modules getModuleId() {
		return moduleId;
	}

	public void setModuleId(Modules moduleId) {
		this.moduleId = moduleId;
	}


	
	
}
