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
@Table(name = "subjects")
public class Subjects extends BaseModel{

	
	public Subjects(Long id) {
		 this.setId(id);
	}
	
	

	public Subjects() {

	}

	@NotBlank(message = "El código no puede estar en blanco")
	@Pattern(regexp = "^[0-9]+$", message = "El código debe contener solo números")
	@Column(name= "code", nullable = false, length = 10, unique=true)
	private String code;
	
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre debe contener solo letras")
    @NotBlank(message = "El nombre no puede estar en blanco")
	@Column(name= "name", nullable = false, length = 50, unique=true)
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teachers teacherId;

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

	/**
	 * @return the teacherId
	 */
	public Teachers getTeacherId() {
		return teacherId;
	}

	/**
	 * @param teacherId the teacherId to set
	 */
	public void setTeacherId(Teachers teacherId) {
		this.teacherId = teacherId;
	}

	
}
