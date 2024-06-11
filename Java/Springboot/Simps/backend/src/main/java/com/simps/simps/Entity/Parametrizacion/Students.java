package com.simps.simps.Entity.Parametrizacion;

import java.util.Date;

import com.simps.simps.Entity.BaseModel.BaseModel;
import com.simps.simps.Entity.Seguridad.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Students extends BaseModel{

	
	@Column(name= "enrollment_date", nullable = false, length = 50)
	private Date enrollmentDate;
	
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique=true)
    private Users userId;
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "curse_id", nullable = false)
    private Curses curseId;


	public Date getEnrollmentDate() {
		return enrollmentDate;
	}


	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}


	public Users getUserId() {
		return userId;
	}


	public void setUserId(Users userId) {
		this.userId = userId;
	}


	public Curses getCurseId() {
		return curseId;
	}


	public void setCurseId(Curses curseId) {
		this.curseId = curseId;
	}
	
	

	
	

	
	
	
}