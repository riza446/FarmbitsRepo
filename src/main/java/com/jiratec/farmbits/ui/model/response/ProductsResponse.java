package com.jiratec.farmbits.ui.model.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rizauddin Mohammad
 * It is a POJO class which send response  to Client Or UI
 * 
 */
@Getter
@Setter
public class ProductsResponse {
	
	private String api;
	private String message;
	private List<ProductDetails> products;
	
	
	public ProductsResponse() {
		
	}
	public ProductsResponse(String api, String message) {
		super();
		this.api = api;
		this.message = message;
		
	}
	public ProductsResponse(String api, String message, List<ProductDetails> products) {
		super();
		this.api = api;
		this.message = message;
		this.products = products;
	}
	

}
