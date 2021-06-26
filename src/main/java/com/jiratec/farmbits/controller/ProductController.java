package com.jiratec.farmbits.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jiratec.farmbits.model.reponse.KeyValueDetails;
import com.jiratec.farmbits.model.reponse.ProductDetails;
import com.jiratec.farmbits.service.ProductService;
import com.jiratec.farmbits.shared.dto.ProductDto;

@RestController
@RequestMapping("/riza-farmbits")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/getproducts")
	public KeyValueDetails getProducts() {
		KeyValueDetails keyVal;
		List<ProductDetails> productDetails = new ArrayList<ProductDetails>();

		List<ProductDto> products = productService.getProducts();
		if (!CollectionUtils.isEmpty(products)) {
			products.forEach(i -> {
				ProductDetails product = new ProductDetails();
				BeanUtils.copyProperties(i, product);
				productDetails.add(product);

			});

			keyVal = new KeyValueDetails("getproducts", "success", productDetails);
		} else
			keyVal = new KeyValueDetails("getproducts", "Products table is empty", productDetails);
		return keyVal;

	}

	@PostMapping("/addProviderForProducts")
	public KeyValueDetails addProviderForProducts(@RequestParam(value = "providerName") String providerName,
			@RequestParam(value = "productName") String productName) {	
		return new KeyValueDetails("addProviderForProducts", productService.addProviderToProduct(providerName, productName));
	}

	@PutMapping("/changeProductCategory")
	public ProductDetails updateProductCategory(@RequestParam(value = "productName") String productName,
			@RequestParam(value = "categoryName") String categoryName) {
		ProductDetails productDetails = new ProductDetails();
		ProductDto product = productService.updateProductCategory(categoryName, productName);

		BeanUtils.copyProperties(product, productDetails);
		return productDetails;
	}

	@GetMapping("/getProductsByCategory/{Id}")
	public KeyValueDetails getProductsByCategory(@PathVariable("Id") String id) {
		KeyValueDetails keyVal=null;
		List<ProductDetails> productDetails = new ArrayList<>();

		List<ProductDto> products = productService.getProductsByCategory(id);
		
		products.forEach(i -> {
			ProductDetails product = new ProductDetails();
			BeanUtils.copyProperties(i, product);
			productDetails.add(product);

		});

		keyVal=new KeyValueDetails("getProductsByCategory", "success", productDetails);
		return keyVal;

	}

	@GetMapping("/getProductsByProvider/{Id}")
	public KeyValueDetails getProductsByProvider(@PathVariable("Id") String id) {
		List<ProductDetails> productDetails = new ArrayList<>();
		KeyValueDetails keyVal=null;
		List<ProductDto> products = productService.getProductsByProvider(id);
		products.forEach(i -> {
			ProductDetails product = new ProductDetails();
			BeanUtils.copyProperties(i, product);
			productDetails.add(product);

		});

		keyVal=new KeyValueDetails("getProductsByProvider", "success", productDetails);
		return keyVal;

	}

	@GetMapping("/getProductsByDiscount/{Id}")
	public KeyValueDetails getProductsByDiscount(@PathVariable("Id") double id) {
		List<ProductDetails> productDetails = new ArrayList<>();
		KeyValueDetails keyVal=null;
		List<ProductDto> products = productService.getProductsByDiscount(id);
		products.forEach(i -> {
			ProductDetails product = new ProductDetails();
			BeanUtils.copyProperties(i, product);
			productDetails.add(product);

		});

		keyVal=new KeyValueDetails("getProductsByDiscount", "success", productDetails);
		return keyVal;

	}

}
