package com.jiratec.farmbits.service;

import java.util.List;

import com.jiratec.farmbits.shared.dto.ProductDto;

public interface ProductService {

	List<ProductDto> getProducts();

	ProductDto updateProductCategory(String categoryName, String productName);

	String addProviderToProduct(String providerName, String productName);

	List<ProductDto> getProductsByCategory(String categoryName);

	List<ProductDto> getProductsByProvider(String providerName);

	List<ProductDto> getProductsByDiscount(double discount);

}
