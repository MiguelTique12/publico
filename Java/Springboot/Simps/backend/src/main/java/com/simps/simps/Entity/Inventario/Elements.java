package com.simps.simps.Entity.Inventario;




import com.simps.simps.Entity.BaseModel.BaseModel;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "elements")
public class Elements  extends BaseModel{
	
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre debe contener solo letras")
    @NotBlank(message = "El nombre no puede estar en blanco")
	@Column (name = "name", nullable = false, length = 50)
	private String name;
	
    @NotBlank(message = "La imagen no puede estar en blanco")
	@Column (name = "image", columnDefinition = "TEXT")
	private String image;
	
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre debe contener solo letras")
    @NotBlank(message = "El nombre no puede estar en blanco")
	@Column (name = "description", nullable = false, length = 90)
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brands brandId;
   
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "type_element_id", nullable = false)
	private TypesElements typesElements;
	
	

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the brandId
	 */
	public Brands getBrandId() {
		return brandId;
	}

	/**
	 * @param brandId the brandId to set
	 */
	public void setBrandId(Brands brandId) {
		this.brandId = brandId;
	}

	/**
	 * @return the typesElements
	 */
	public TypesElements getTypesElements() {
		return typesElements;
	}

	/**
	 * @param typesElements the typesElements to set
	 */
	public void setTypesElements(TypesElements typesElements) {
		this.typesElements = typesElements;
	}


	
	

}


