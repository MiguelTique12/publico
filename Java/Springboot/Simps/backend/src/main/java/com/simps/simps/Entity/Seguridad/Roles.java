package com.simps.simps.Entity.Seguridad;


import java.util.HashSet;
import java.util.Set;

import com.simps.simps.Entity.BaseModel.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "roles")
public class Roles extends BaseModel{
	

	@NotBlank(message = "El código no puede estar en blanco")
    @Pattern(regexp = "^[0-9]+$", message = "El código debe contener solo números")
	@Column (name = "code", nullable = false, unique = true, length = 50)
	private String code;
	
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "La descripción debe contener solo letras")
    @NotBlank(message = "La descripción no puede estar en blanco")
	@Column (name = "description", nullable = false, length = 50)
	private String description;
	
	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(name = "views_roles", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name="view_id"))
	private Set<Views> views = new HashSet<>();

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

	public Set<Views> getViews() {
		return views;
	}

	public void setViews(Set<Views> views) {
		this.views = views;
	}

	
	
	
	
	
}
