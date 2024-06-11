package com.simps.simps.Entity.Inventario;

import com.simps.simps.Entity.BaseModel.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name = "amounts_elements")
public class AmountsElements  extends BaseModel{

	
	@Positive
	@Column (name = "amount", length = 50)
	private Integer amounts;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "element_classrooom_id", nullable = false)
    private ElementsClassrooms elementsClassroomsId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "inventory_id")
    private Inventories inventoriesId;

	/**
	 * @return the amounts
	 */
	public Integer getAmounts() {
		return amounts;
	}

	/**
	 * @param amounts the amounts to set
	 */
	public void setAmounts(Integer amounts) {
		this.amounts = amounts;
	}

	/**
	 * @return the elementsClassroomsId
	 */
	public ElementsClassrooms getElementsClassroomsId() {
		return elementsClassroomsId;
	}

	/**
	 * @param elementsClassroomsId the elementsClassroomsId to set
	 */
	public void setElementsClassroomsId(ElementsClassrooms elementsClassroomsId) {
		this.elementsClassroomsId = elementsClassroomsId;
	}

	/**
	 * @return the inventoriesId
	 */
	public Inventories getInventoriesId() {
		return inventoriesId;
	}

	/**
	 * @param inventoriesId the inventoriesId to set
	 */
	public void setInventoriesId(Inventories inventoriesId) {
		this.inventoriesId = inventoriesId;
	}



}
