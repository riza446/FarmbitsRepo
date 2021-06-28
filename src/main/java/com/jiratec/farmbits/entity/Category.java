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
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rizauddin Mohammad
 * It is a category Entity class with
 *  @OnetoMany relationship  with product Entity class 
 * 
 */

@Entity(name = "category")
@Getter
@Setter
public class Category implements Serializable {

	private static final long serialVersionUID = -562835637992739720L;

	@Id
	@GeneratedValue
	private long Id;

	@Column(nullable = false, length = 50, unique = true)
	private String categoryName;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Product> products=new HashSet<>();

	public Category() {
	}

	public Category(String categoryName) {
		this.categoryName = categoryName;

	}

	@Override
	public String toString() {
		return "Category [Id=" + Id + ", categoryName=" + categoryName + ", products=" + products + "]";
	}

}
