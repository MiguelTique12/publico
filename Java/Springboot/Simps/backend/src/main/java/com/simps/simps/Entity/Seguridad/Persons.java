package com.simps.simps.Entity.Seguridad;



import com.simps.simps.Entity.BaseModel.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "persons")
public class Persons extends BaseModel{

	public Persons(Long id) {
	    this.setId(id);
	}
	
	public Persons() {

	}

	public enum DocumentType{CE, CC, TI, PP, DNI}

	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El primer nombre debe contener solo letras")
    @NotBlank(message = "El primer nombre no puede estar en blanco")
	@Column(name= "first_name", nullable = false, length = 30)
	private String firstName;
	
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El segundo nombre debe contener solo letras")
	@Column(name = "second_name", nullable = true, length = 30)
	private String secondName;
	
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El primer apellido debe contener solo letras")
    @NotBlank(message = "El primer apellido no puede estar en blanco")
	@Column (name = "first_last_name", nullable = false, length = 30)
	private String firstLastName;
	
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El segundo apellido debe contener solo letras")
    @NotBlank(message = "El segundo apellido no puede estar en blanco")
	@Column (name = "second_last_name", nullable = false, length = 30)
	private String secondLastName;
	

	@Enumerated(EnumType.STRING)
	@Column(name = "document_type", nullable = false, length = 5)
    private DocumentType documentType;
	
	@Pattern(regexp = "^[0-9]+$", message = "El document debe contener solo números")
    @NotBlank(message = "El nombre no puede estar en blanco")
	@Column(name = "document", nullable = false, unique = true, length = 12)
    private String document;
	
	@Column(name = "age", nullable = false, length = 2)
	@Digits(integer = 2, fraction = 0, message = "La edad debe contener solo números")
	private Integer age;
	
   
	@Column (name = "gender", nullable = false)
	private Boolean gender;
	
	@NotBlank(message = "El correo electrónico no puede estar en blanco")
	@Email(message = "El correo electrónico debe ser válido")
	@Column (name = "mail", nullable = false, length = 50)
	private String mail;
	
	@Column(name = "fingerprint", nullable = true, unique = true, length = 12)
	private byte[] fingerprint;
	
	@Column(name = "rfid", nullable = true, unique = true, length = 12)
	private String rfid;
	
	
    @Pattern(regexp = "^[0-9]+$", message = "El numero de télefono debe contener solo números")
	@Column(name = "phone_number", nullable = false, length = 10)
	private String phoneNumber;
	
	@NotBlank(message = "La dirección no puede estar en blanco")
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-\\,]+$", message = "La dirección debe ser válida")
	@Column (name = "address", nullable = false, length = 30)
	private String address;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getFirstLastName() {
		return firstLastName;
	}

	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}

	public String getSecondLastName() {
		return secondLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public byte[] getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(byte[] fingerprint) {
		this.fingerprint = fingerprint;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}
