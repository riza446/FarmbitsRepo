package com.jiratec.farmbits.model.reponse;

import java.util.List;

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

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ProductDetails> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetails> products) {
		this.products = products;
	}
	

}
