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

import lombok.Getter;
import lombok.Setter;



/**
 * @author Rizauddin Mohammad
 * It is a provider Entity class with
 *  @ManytoMany relationship  with product Entity class 
 * 
 */
@Entity(name = "provider")
@Getter
@Setter
public class Provider implements Serializable {

	private static final long serialVersionUID = -8972483857355112104L;

	@Id
	@GeneratedValue
	private long Id;

	@Column(nullable = false, length = 50, unique = true)
	private String providerName;

	@Column(nullable = false, length = 150, unique = true)
	private String email;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "products_providers", joinColumns = {
			@JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, updatable = false) })
	private Set<Product> products = new HashSet<>();

	public Provider() {
	}

	public Provider(String providerName, String email) {

		this.providerName = providerName;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Provider [Id=" + Id + ", providerName=" + providerName + ", email=" + email + "]";
	}

}
