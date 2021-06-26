package com.jiratec.farmbits.reponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetails {

	private long Id;
	private String productName;
	private String description;
	private double price;
	private double discount;

}
