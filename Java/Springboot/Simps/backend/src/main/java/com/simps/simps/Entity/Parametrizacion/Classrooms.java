	package com.simps.simps.Entity.Parametrizacion;


import com.simps.simps.Entity.BaseModel.BaseModel;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "classrooms")
public class Classrooms extends BaseModel{

	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre debe contener solo letras")

	@Column(name= "name", nullable = false, length = 50, unique=true)
	private String name;
	

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "floor_id", nullable = false)
    private Floors floorId;

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
	 * @return the floorId
	 */
	public Floors getFloorId() {
		return floorId;
	}

	/**
	 * @param floorId the floorId to set
	 */
	public void setFloorId(Floors floorId) {
		this.floorId = floorId;
	}
	

	
}
