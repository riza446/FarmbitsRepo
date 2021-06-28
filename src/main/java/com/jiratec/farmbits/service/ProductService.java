package com.jiratec.farmbits.service;

import java.util.List;

import com.jiratec.farmbits.shared.dto.ProductDto;

/**
 * @author Rizauddin Mohammad
 * It is an Interface for business logic
 */
public interface ProductService {
	
	
	
	
	String addProduct(ProductDto productToSave,String providerName,String categoryName);

	List<ProductDto> getProducts();

	String updateProductCategory(String categoryName, String productName);
	
	String deleteProduct(String productName);
	
	String changeProductDiscount(String productName, double discount);
	
	String changeProductPrice(String productName, double price);
	
	List<ProductDto> getProductsByName(String productName);

	List<ProductDto> getProductsByCategory(String categoryName);

	List<ProductDto> getProductsByProvider(String providerName);

	List<ProductDto> getProductsByDiscount(double discount);

}
