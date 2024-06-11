package com.simps.simps.Entity.BaseModel;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "state")
	private Boolean state;
	
	@Column (name = "user_creation_id")
	private Long userCreationId;
	
	@Column (name = "user_modification_id")
	private Long userModificationId;
	
	@Column (name = "date_creation", columnDefinition = "TIMESTAMP")
	private LocalDateTime dateCreation;
	
	@Column (name = "date_modification", columnDefinition = "TIMESTAMP")
	private LocalDateTime dateModification;

	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		Id = id;
	}

	/**
	 * @return the state
	 */
	public Boolean getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Boolean state) {
		this.state = state;
	}

	/**
	 * @return the userCreationId
	 */
	public Long getUserCreationId() {
		return userCreationId;
	}

	/**
	 * @param userCreationId the userCreationId to set
	 */
	public void setUserCreationId(Long userCreationId) {
		this.userCreationId = userCreationId;
	}

	/**
	 * @return the userModificationId
	 */
	public Long getUserModificationId() {
		return userModificationId;
	}

	/**
	 * @param userModificationId the userModificationId to set
	 */
	public void setUserModificationId(Long userModificationId) {
		this.userModificationId = userModificationId;
	}

	/**
	 * @return the dateCreation
	 */
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the dateModification
	 */
	public LocalDateTime getDateModification() {
		return dateModification;
	}

	/**
	 * @param dateModification the dateModification to set
	 */
	public void setDateModification(LocalDateTime dateModification) {
		this.dateModification = dateModification;
	}

	
}
