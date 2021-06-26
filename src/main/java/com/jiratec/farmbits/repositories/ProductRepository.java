package com.jiratec.farmbits.repositories;

import org.springframework.stereotype.Repository;

import com.jiratec.farmbits.entity.Category;
import com.jiratec.farmbits.entity.Product;
import com.jiratec.farmbits.entity.Provider;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	Product findByProductName(String productName);
	
	List<Product> getProdcutsByCategory(Category category);

	List<Product> findByProductNameContaining(String productName);
	
	List<Product> findProductByProvider(Provider provider);

}
