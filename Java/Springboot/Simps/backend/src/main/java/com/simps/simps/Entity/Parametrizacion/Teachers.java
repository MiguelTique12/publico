package com.simps.simps.Entity.Parametrizacion;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.simps.simps.Entity.BaseModel.BaseModel;
import com.simps.simps.Entity.Seguridad.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "teachers")
public class Teachers extends BaseModel{
	
	
	@Column(name= "contract_date", nullable = false, length = 50)
	private Date contractDate;
	
	@NotBlank(message = "El usuario no puede estar en blanco")
	@OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique=true)
    private Users userId;
	
	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(name = "curses_teachers",
    joinColumns = @JoinColumn(name = "teacher_id"), 
    inverseJoinColumns = @JoinColumn(name="curse_id"))
	private Set<Curses> curse = new HashSet<>();

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public Set<Curses> getCurse() {
		return curse;
	}

	public void setCurse(Set<Curses> curse) {
		this.curse = curse;
	}

	


	
}