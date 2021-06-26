package com.jiratec.farmbits.reponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyValueDetails {
	
	private String api;
	private String message;
	private List<ProductDetails> products;
	
	public KeyValueDetails() {
		
	}
	public KeyValueDetails(String api, String message) {
		super();
		this.api = api;
		this.message = message;
		
	}
	public KeyValueDetails(String api, String message, List<ProductDetails> products) {
		super();
		this.api = api;
		this.message = message;
		this.products = products;
	}

}
