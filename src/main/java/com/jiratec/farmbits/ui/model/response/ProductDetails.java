package com.jiratec.farmbits.ui.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * @author Rizauddin Mohammad
 * It is a POJO class which send product details to Client Or UI
 * 
 */
public class ProductDetails {

	private String productName;
	private String description;
	private double price;
	private double discount;
}
