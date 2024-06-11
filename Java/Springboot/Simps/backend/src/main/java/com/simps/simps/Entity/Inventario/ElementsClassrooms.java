package com.simps.simps.Entity.Inventario;


import com.simps.simps.Entity.BaseModel.BaseModel;
import com.simps.simps.Entity.Parametrizacion.Classrooms;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "elements_classrooms")
public class ElementsClassrooms  extends BaseModel{	
	
	@Column (name = "amount", nullable = false, length = 50)
	private Integer amount;
		
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "element_id", nullable = false)
    private Elements elementId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classrooms classroomId;

	/**
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * @return the elementId
	 */
	public Elements getElementId() {
		return elementId;
	}

	/**
	 * @param elementId the elementId to set
	 */
	public void setElementId(Elements elementId) {
		this.elementId = elementId;
	}

	public Classrooms getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(Classrooms classroomId) {
		this.classroomId = classroomId;
	}

	
	
	
}
