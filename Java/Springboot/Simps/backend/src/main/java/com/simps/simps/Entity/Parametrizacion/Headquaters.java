package com.simps.simps.Entity.Parametrizacion;

import com.simps.simps.Entity.BaseModel.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "headquaters")
public class Headquaters extends BaseModel{
	
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre debe contener solo letras")
    @NotBlank(message = "El nombre no puede estar en blanco")
	@Column(name= "name", nullable = false, length = 50, unique=true)
	private String name;
	
	@NotBlank(message = "La dirección no puede estar en blanco")
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-\\,]+$", message = "La dirección debe ser válida")
	@Column(name= "address", nullable = false, length = 50, unique=true)
	private String address;
	
	@NotBlank(message = "El número no puede estar en blanco")
	@Pattern(regexp = "^[0-9]+$", message = "El numero debe contener solo números")
	@Column(name= "phone_number", nullable = false, length = 50, unique=true)
	private String phoneNumber;
	

    @NotBlank(message = "El correo electrónico no puede estar en blanco")
    @Email(message = "El correo electrónico debe ser válido")
	@Column(name= "mail", nullable = false, length = 50, unique=true)
	private String mail;
	
    @NotBlank(message = "El nit no puede estar en blanco")
    @Pattern(regexp = "^[0-9]+$", message = "El nit debe contener solo números")
	@Column(name= "nit", nullable = false, length = 50, unique=true)
	private String nit;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "institute_id", nullable = false)
    private Institutes instituteId;

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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	/**
	 * @return the instituteId
	 */
	public Institutes getInstituteId() {
		return instituteId;
	}

	/**
	 * @param instituteId the instituteId to set
	 */
	public void setInstituteId(Institutes instituteId) {
		this.instituteId = instituteId;
	}

	
	
}

