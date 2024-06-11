package com.simps.simps.Entity.Asistencia;

import java.time.LocalDateTime;

import com.simps.simps.Entity.BaseModel.BaseModel;
import com.simps.simps.Entity.Parametrizacion.Subjects;
import com.simps.simps.Entity.Seguridad.Persons;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "records_attendances")
public class RecordsAttendances extends BaseModel{


	public enum TypeAssistance{HUELLA, RFID, MANUAL}
	
	@NotNull(message = "El tipo de asistencia no puede estar en blanco")
	@Enumerated(EnumType.STRING)
	@Column(name = "type_assistance", nullable = false, length = 40)
    private TypeAssistance typeAssistance;
	
	@Column(name = "date", length = 12)
    private LocalDateTime date;
	
	@Column(name = "description", length = 100)
    private String description;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "subject_id")
    private Subjects subjectId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "person_id")
    private Persons personId;

	public TypeAssistance getTypeAssistance() {
		return typeAssistance;
	}

	public void setTypeAssistance(TypeAssistance typeAssistance) {
		this.typeAssistance = typeAssistance;
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

	public Subjects getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Subjects subjectId) {
		this.subjectId = subjectId;
	}

	public Persons getPersonId() {
		return personId;
	}

	public void setPersonId(Persons personId) {
		this.personId = personId;
	}



	
}
