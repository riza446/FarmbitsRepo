package com.jiratec.farmbits.shared.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rizauddin Mohammad
 * It is a POJO class which is used to as intermediate object passing from business logic to client  and vice versa
 * 
 */

@Getter
@Setter
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 5954435919564615093L;
	private long id;
	private String productName;
	private String description;
	private double price;
	private double discount;
	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", productName=" + productName + ", description=" + description + ", price="
				+ price + ", discount=" + discount + "]";
	}
	
	

}
