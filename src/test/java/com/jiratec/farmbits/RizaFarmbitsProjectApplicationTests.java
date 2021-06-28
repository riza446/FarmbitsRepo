package com.jiratec.farmbits;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jiratec.farmbits.service.ProductService;
import com.jiratec.farmbits.shared.dto.ProductDto;

@SpringBootTest
class RizaFarmbitsProjectApplicationTests {
	
	@Autowired
	ProductService productService;

	@Test
	void contextLoads() {
	}
		
	@Test
	void getAllProducts() {
		List<ProductDto> product=productService.getProducts();
		System.out.println(product.toString());
		
	}
	
	@Test
	void getAllProductsByDiscount() {
		List<ProductDto> product=productService.getProductsByDiscount(30);
		System.out.println(product.toString());
		
	}
	
	

}
