package com.jiratec.farmbits;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jiratec.farmbits.entity.Category;
import com.jiratec.farmbits.entity.Product;
import com.jiratec.farmbits.entity.Provider;
import com.jiratec.farmbits.repositories.CategoryRepository;
import com.jiratec.farmbits.repositories.ProductRepository;
import com.jiratec.farmbits.repositories.ProviderRepository;

@SpringBootApplication
public class RizaFarmbitsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RizaFarmbitsProjectApplication.class, args);
	}
	@Bean
	public CommandLineRunner mappingCategoryProducts(CategoryRepository categoryRepository, ProductRepository productRepository, ProviderRepository providerRepository) {
		return args->{
			
			//creating categories
			Category category=new Category("test");
			categoryRepository.save(category);
			Category category2=new Category("test2");
			categoryRepository.save(category2);
			
			//Creating a products
			Product p1=new Product(  "testing", "sdas", 10.20, 30.3, category);
			Product p2=new Product(  "testing2", "sdas", 20.20, 5.3, category);
			Product p3=new Product(  "testing3", "sdas", 30.20, 10.3, category2); 
			Product p4=new Product(  "testing4", "sdas", 40.20, 15.3, category2);
			
			/*
			 * productRepository.save(p1); productRepository.save(p2);
			 * productRepository.save(p3); productRepository.save(p4);
			 */
			productRepository.saveAll(Arrays.asList(p1,p2,p3,p4));
			
			//Creating product providers
			Provider pp1=new Provider("xyz", "xyz@gmail.com");
			Provider pp2=new Provider("xyz2", "xyz2@gmail.com");
			Provider pp3=new Provider("xyz3", "xyz3@gmail.com");
			
			providerRepository.saveAll(Arrays.asList(pp1,pp2,pp3));
			
			
			  p1.getProvider().addAll(Arrays.asList(pp1,pp2));
			  
			  productRepository.save(p1);
			  
			  p2.getProvider().addAll(Arrays.asList(pp1,pp3));
			  
			  productRepository.save(p2);
			  
			  p3.getProvider().addAll(Arrays.asList(pp2,pp3));
			  
			  productRepository.save(p3);
			  
			  p4.getProvider().addAll(Arrays.asList(pp1,pp2,pp3));
			  
			  productRepository.save(p4);
			 
			
			
		};
	}
}
