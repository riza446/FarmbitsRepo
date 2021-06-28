package com.jiratec.farmbits.ui.model.resquest;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rizauddin Mohammad
 * It is a POJO class which take details from Client Or UI
 * 
 */

@Getter
@Setter
public class ProductDetailsRequest {
	
	private String productName;
	private String description;
	private double price;
	private double discount;
	private String providerName;
	private String categoryName;

}
