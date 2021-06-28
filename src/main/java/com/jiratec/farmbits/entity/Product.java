package com.jiratec.farmbits.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Rizauddin Mohammad
 * It is a Product Entity class with 
 * @ManytoOne relationship  with category Entity class and @ManyToMany relationship  with Provider Entity class 
 * 
 */
@Entity(name = "product")
@Getter
@Setter
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long Id;

	@Column(nullable = false, length = 50, unique = true)
	private String productName;

	private String description;

	private double price;

	private double discount;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_Id")
	private Category category;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "products_providers", joinColumns = {
			@JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false, updatable = false) })
	private Set<Provider> provider = new HashSet<>();

	public Product() {

	}

	public Product(String productName, String description, double price, double discount, Category category) {

		this.productName = productName;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [Id=" + Id + ", productName=" + productName + ", description=" + description + ", price="
				+ price + ", discount=" + discount + "]";
	}

}
