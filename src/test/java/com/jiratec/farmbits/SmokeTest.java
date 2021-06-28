package com.jiratec.farmbits;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jiratec.farmbits.ui.controller.CategoryController;
import com.jiratec.farmbits.ui.controller.ProductController;
import com.jiratec.farmbits.ui.controller.ProviderController;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private ProductController productController;
	
	@Autowired
	private  ProviderController providerController;
	
	@Autowired
	private CategoryController categoryController;
	
	
	@Test
	void contextLoads() {
		assertThat(productController).isNotNull();
		assertThat(providerController).isNotNull();
		assertThat(categoryController).isNotNull();
	}
	
}
