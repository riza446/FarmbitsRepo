package com.jiratec.farmbits.shared.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 5954435919564615093L;
	private long id;
	private String productName;
	private String description;
	private double price;
	private double discount;

}
