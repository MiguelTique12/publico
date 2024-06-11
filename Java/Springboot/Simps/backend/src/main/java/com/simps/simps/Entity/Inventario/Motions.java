package com.simps.simps.Entity.Inventario;

import java.time.LocalDateTime;

import com.simps.simps.Entity.BaseModel.BaseModel;
import com.simps.simps.Entity.Parametrizacion.Classrooms;
import com.simps.simps.Entity.Seguridad.Persons;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "motions")
public class Motions extends BaseModel{


	@Positive
	@Column (name = "amount", nullable = false, length = 50)
	private Integer amount;
	
	@Column (name = "date", nullable = false, length = 40)
	private LocalDateTime date;
	
	
	@Column (name = "description", nullable = false, length = 50)
	private String description;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "classrooms_destination_id", nullable = false)
    private Classrooms classroomsDestinationId;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "element_classrooms_id", nullable = false)
    private ElementsClassrooms elementClassroomId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Persons userId;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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

	public Classrooms getClassroomsDestinationId() {
		return classroomsDestinationId;
	}

	public void setClassroomsDestinationId(Classrooms classroomsDestinationId) {
		this.classroomsDestinationId = classroomsDestinationId;
	}

	public ElementsClassrooms getElementClassroomId() {
		return elementClassroomId;
	}

	public void setElementClassroomId(ElementsClassrooms elementClassroomId) {
		this.elementClassroomId = elementClassroomId;
	}

	public Persons getUserId() {
		return userId;
	}

	public void setUserId(Persons userId) {
		this.userId = userId;
	}

	

	
	
}