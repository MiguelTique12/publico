package com.simps.simps.Entity.Parametrizacion;

import com.simps.simps.Entity.BaseModel.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "curses")
public class Curses extends BaseModel{
	
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre debe contener solo letras")
    @NotBlank(message = "El nombre no puede estar en blanco")
	@Column(name= "name", nullable = false, length = 50, unique=true)
	private String name;
	
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "grade_id", nullable = false)
    private Grades gradeId;
	
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "classrooms_id", nullable = false, unique=true)
    private Classrooms classroomsId;
	
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "technique_id", nullable = false)
    private Techniques techniqueId;

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
	 * @return the gradeId
	 */
	public Grades getGradeId() {
		return gradeId;
	}

	/**
	 * @param gradeId the gradeId to set
	 */
	public void setGradeId(Grades gradeId) {
		this.gradeId = gradeId;
	}

	/**
	 * @return the techniqueId
	 */
	public Techniques getTechniqueId() {
		return techniqueId;
	}

	/**
	 * @param techniqueId the techniqueId to set
	 */
	public void setTechniqueId(Techniques techniqueId) {
		this.techniqueId = techniqueId;
	}

	public Classrooms getClassroomsId() {
		return classroomsId;
	}

	public void setClassroomsId(Classrooms classroomsId) {
		this.classroomsId = classroomsId;
	}

	

	
	
}
