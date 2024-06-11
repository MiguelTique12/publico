package com.simps.simps.Entity.Inventario;

import java.time.LocalDateTime;

import com.simps.simps.Entity.BaseModel.BaseModel;
import com.simps.simps.Entity.Seguridad.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "inventories")
public class Inventories  extends BaseModel{
	
	
	@Column (name = "date", length = 30)
	private LocalDateTime date;
	
	@Column (name = "inventory_success", length = 30)
	private boolean inventorySuccess;
	
	@NotBlank(message = "El número no puede estar en blanco")
	@Column (name = "description", length = 200)
	private String description;
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "user_id")
    private Users userId;


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public boolean isInventorySuccess() {
		return inventorySuccess;
	}


	public void setInventorySuccess(boolean inventorySuccess) {
		this.inventorySuccess = inventorySuccess;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Users getUserId() {
		return userId;
	}


	public void setUserId(Users userId) {
		this.userId = userId;
	}

	

}








