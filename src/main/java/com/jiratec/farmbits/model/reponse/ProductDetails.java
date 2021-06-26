package com.jiratec.farmbits.model.reponse;

public class ProductDetails {

	private long Id;
	private String productName;
	private String description;
	private double price;
	private double discount;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
