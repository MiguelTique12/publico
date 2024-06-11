package com.simps.simps.Entity.Seguridad;


import com.simps.simps.Entity.BaseModel.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class Users extends BaseModel{

	
 
	@Column(name = "username", nullable = false, unique = true, length = 50)
	private String userName;
	
	@NotBlank(message = "La contraseña no puede estar en blanco")
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-\\,]+$", message = "La contraseña debe ser válida")
	@Column (name = "password", nullable = false, length = 40)
	private String password;
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private Persons personId;
	
	@NotBlank(message = "La imagen no puede estar en blanco")
	@Column (name = "image", columnDefinition = "TEXT")
	private String image;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Roles roleId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Persons getPersonId() {
		return personId;
	}

	public void setPersonId(Persons personId) {
		this.personId = personId;
	}

	public Roles getRoleId() {
		return roleId;
	}

	public void setRoleId(Roles roleId) {
		this.roleId = roleId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	

	
	
	
}
