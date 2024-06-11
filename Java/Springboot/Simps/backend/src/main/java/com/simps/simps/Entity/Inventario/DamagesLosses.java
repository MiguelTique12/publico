package com.simps.simps.Entity.Inventario;

import java.time.LocalDateTime;

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
@Table(name = "damages_losses")
public class DamagesLosses extends BaseModel {

	
	
	@Column(name = "amount", nullable = false, length = 50)
	private Integer amount;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "element_classrooms_id", nullable = false)
	private ElementsClassrooms elementClassroomId;

	@Column (name = "date", nullable = false, length = 40)
	private LocalDateTime date;

	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre debe contener solo letras")
	@NotBlank(message = "El nombre no puede estar en blanco")
	@Column(name = "description", nullable = false, length = 200)
	private String description;
	
	@Column(name = "type", nullable = false)
	private Boolean type;
	

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}


	public ElementsClassrooms getElementClassroomId() {
		return elementClassroomId;
	}

	public void setElementClassroomId(ElementsClassrooms elementClassroomId) {
		this.elementClassroomId = elementClassroomId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	
	
	
	
}
