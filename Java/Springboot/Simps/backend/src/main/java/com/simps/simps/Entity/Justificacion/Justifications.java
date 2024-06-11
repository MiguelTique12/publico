package com.simps.simps.Entity.Justificacion;

import java.time.LocalDate;

import com.simps.simps.Entity.BaseModel.BaseModel;
import com.simps.simps.Entity.Parametrizacion.Students;
import com.simps.simps.Entity.Parametrizacion.Subjects;

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
@Table(name = "justifications")
public class Justifications extends BaseModel{
	
	public enum justificationType{MEDICA, PERSONAL, FAMILIAR}
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "student_id")
    private Students studentId;
	
	@Column(name = "subject", length = 100)
	private String subject;
	
	@Column(name = "date")
	private LocalDate date;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "subject_id")
    private Subjects subjectId;
	
	@Column(name = "description", length = 500)
	private String description;
	
	@NotNull(message = "El tipo de jutificación no puede estar en blanco")
	@Enumerated(EnumType.STRING)
	@Column(name = "type_assistance", nullable = false, length = 40)
    private justificationType justificationType;
	
	@Column(name = "file", columnDefinition = "TEXT")
	private String file;

	public Students getStudentId() {
		return studentId;
	}

	public void setStudentId(Students studentId) {
		this.studentId = studentId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Subjects getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Subjects subjectId) {
		this.subjectId = subjectId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public justificationType getJustificationType() {
		return justificationType;
	}

	public void setJustificationType(justificationType justificationType) {
		this.justificationType = justificationType;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	
	
	
}
