package com.simps.simps.Entity.Parametrizacion;

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
@Table(name = "floors")
public class Floors extends BaseModel{

	
    @NotBlank(message = "El número del piso no puede estar en blanco")
    @Pattern(regexp = "^[0-9]+$", message = "El número del piso debe contener solo números")
	@Column(name= "floor_number", nullable = false, length = 50, unique=true)
	private Integer floorNumber;
	

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "headquater_id", nullable = false)
    private Headquaters headquaterId;

	/**
	 * @return the floorNumber
	 */
	public Integer getFloorNumber() {
		return floorNumber;
	}

	/**
	 * @param floorNumber the floorNumber to set
	 */
	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

	/**
	 * @return the headquaterId
	 */
	public Headquaters getHeadquaterId() {
		return headquaterId;
	}

	/**
	 * @param headquaterId the headquaterId to set
	 */
	public void setHeadquaterId(Headquaters headquaterId) {
		this.headquaterId = headquaterId;
	}

	
	
}
