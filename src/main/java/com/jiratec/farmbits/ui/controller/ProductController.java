package com.jiratec.farmbits.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiratec.farmbits.service.ProductService;
import com.jiratec.farmbits.shared.dto.ProductDto;
import com.jiratec.farmbits.ui.model.response.ProductsResponse;
import com.jiratec.farmbits.ui.model.response.ProductsResponse;
import com.jiratec.farmbits.ui.model.response.ProductDetails;
import com.jiratec.farmbits.ui.model.resquest.ProductDetailsRequest;
import com.jiratec.farmbits.util.ApiNameUntil;
import com.jiratec.farmbits.util.ConstantUtil;

/**
 * @author Rizauddin Mohmmad
 * This is the controller class which has all the Restapi  calls
 * like adding product, changing product's category 
 * It also does getting products based on category, provider and discount
 * 
 *
 */
@RestController
@RequestMapping("/riza-farmbits")
public class ProductController {

	@Autowired
	ProductService productService;
	
	

	@PostMapping("/addProduct")
	public ProductsResponse addProduct(@RequestBody ProductDetailsRequest productsDetails) {
		if (!(StringUtils.hasLength(productsDetails.getCategoryName())
				&& StringUtils.hasLength(productsDetails.getProductName())
				&& StringUtils.hasLength(productsDetails.getProviderName()))) {
			return new ProductsResponse(ApiNameUntil.API_ADD_PROUDUCT, ConstantUtil.INPUTS_EMPTY);
		}
		ProductDto product = new ProductDto();
		BeanUtils.copyProperties(productsDetails, product);
		System.out.println("provider name :" + productsDetails.getProviderName());
		return new ProductsResponse(ApiNameUntil.API_ADD_PROUDUCT, productService.addProduct(product,
				productsDetails.getProviderName(), productsDetails.getCategoryName()));
	}
	
	
	@PutMapping("/changeProductDiscount")
	public ProductsResponse changeProductDiscount(@RequestParam(value = "productName") String productName,
			@RequestParam(value = "discount") double discount) {
		if (! StringUtils.hasLength(productName)) {
			return new ProductsResponse(ApiNameUntil.API_CHANGE_DISCOUNT_TO_PRODUCT,
					ConstantUtil.INPUTS_EMPTY);
		}

		return new ProductsResponse(ApiNameUntil.API_CHANGE_DISCOUNT_TO_PRODUCT,
				productService.changeProductDiscount(productName ,discount));

	}

	@PutMapping("/changeProductPrice")
	public ProductsResponse changeProductPrice(@RequestParam(value = "productName") String productName,
			@RequestParam(value = "price") double price) {
		if (! StringUtils.hasLength(productName)) {
			return new ProductsResponse(ApiNameUntil.API_CHANGE_PRICE_TO_PRODUCT,
					ConstantUtil.INPUTS_EMPTY);
		}

		return new ProductsResponse(ApiNameUntil.API_CHANGE_PRICE_TO_PRODUCT,
				productService.changeProductPrice(productName ,price));

	}

	@PutMapping("/changeProductCategory")
	public ProductsResponse updateProductCategory(@RequestParam(value = "productName") String productName,
			@RequestParam(value = "categoryName") String categoryName) {
		if (!(StringUtils.hasLength(categoryName) && StringUtils.hasLength(productName))) {
			return new ProductsResponse(ApiNameUntil.API_CHANGE_CATEGORY_TO_PRODUCT,
					ConstantUtil.INPUTS_EMPTY);
		}

		return new ProductsResponse(ApiNameUntil.API_CHANGE_CATEGORY_TO_PRODUCT,
				productService.updateProductCategory(categoryName, productName));

	}
	
	@DeleteMapping("/deleteProduct")
	public ProductsResponse deleteProduct(@RequestParam(value = "productName") String productName) {
		if (! StringUtils.hasLength(productName)) {
			return new ProductsResponse(ApiNameUntil.API_DELETE_PROUDUCT,
					ConstantUtil.INPUTS_EMPTY);
		}

		return new ProductsResponse(ApiNameUntil.API_DELETE_PROUDUCT,
				productService.deleteProduct(productName));

	}

	@GetMapping("/getProducts")
	public ProductsResponse getProducts() {
		ProductsResponse keyVal;
		List<ProductDetails> productDetails = new ArrayList<ProductDetails>();

		List<ProductDto> products = productService.getProducts();
		if (!CollectionUtils.isEmpty(products)) {
			products.forEach(i -> {
				ProductDetails product = new ProductDetails();
				BeanUtils.copyProperties(i, product);
				productDetails.add(product);

			});

			keyVal = new ProductsResponse(ApiNameUntil.API_GET_PRODUCTS, ConstantUtil.SUCCESS, productDetails);
		} else
			keyVal = new ProductsResponse(ApiNameUntil.API_GET_PRODUCTS, ConstantUtil.PRODUCT_NOT_AVAILABLE,
					productDetails);
		return keyVal;

	}

	@GetMapping("/getProductsByCategory/{Id}")
	public ProductsResponse getProductsByCategory(@PathVariable("Id") String id) {
		ProductsResponse keyVal = null;
		if (!StringUtils.hasLength(id) )
			return new ProductsResponse(ApiNameUntil.API_GET_PRODUCTS_BY_CATEGORY, ConstantUtil.INPUTS_EMPTY);
		List<ProductDetails> productDetails = new ArrayList<>();

		List<ProductDto> products = productService.getProductsByCategory(id);
		if (!CollectionUtils.isEmpty(products)) {
			products.forEach(i -> {
				ProductDetails product = new ProductDetails();
				BeanUtils.copyProperties(i, product);
				productDetails.add(product);

			});

			keyVal = new ProductsResponse(ApiNameUntil.API_GET_PRODUCTS_BY_CATEGORY, ConstantUtil.SUCCESS,
					productDetails);
			return keyVal;
		} else {
			keyVal = new ProductsResponse(ApiNameUntil.API_GET_PRODUCTS_BY_CATEGORY, ConstantUtil.PRODUCT_NOT_AVAILABLE,
					productDetails);
			return keyVal;
		}

	}

	@GetMapping("/getProductsByProvider/{Id}")
	public ProductsResponse getProductsByProvider(@PathVariable("Id") String id) {
		
		ProductsResponse keyVal = null;
		if (!StringUtils.hasLength(id) )
			return new ProductsResponse(ApiNameUntil.API_GET_PRODUCTS_BY_PROVIDER, ConstantUtil.INPUTS_EMPTY);
		List<ProductDetails> productDetails = new ArrayList<>();
		List<ProductDto> products = productService.getProductsByProvider(id);
		if (!CollectionUtils.isEmpty(products)) {
			products.forEach(i -> {
				ProductDetails product = new ProductDetails();
				BeanUtils.copyProperties(i, product);
				productDetails.add(product);

			});

			keyVal = new ProductsResponse(ApiNameUntil.API_GET_PRODUCTS_BY_PROVIDER, ConstantUtil.SUCCESS,
					productDetails);
			return keyVal;

		} else {
			keyVal = new ProductsResponse(ApiNameUntil.API_GET_PRODUCTS_BY_PROVIDER, ConstantUtil.PRODUCT_NOT_AVAILABLE,
					productDetails);
			return keyVal;
		}

	}

	@GetMapping("/getProductsByDiscount/{Id}")
	public ProductsResponse getProductsByDiscount(@PathVariable("Id") double id) {
		List<ProductDetails> productDetails = new ArrayList<>();
		ProductsResponse keyVal = null;
		List<ProductDto> products = productService.getProductsByDiscount(id);
		if (!CollectionUtils.isEmpty(products)) {
			products.forEach(i -> {
				ProductDetails product = new ProductDetails();
				BeanUtils.copyProperties(i, product);
				productDetails.add(product);

			});

			keyVal = new ProductsResponse(ApiNameUntil.API_GET_PRODUCTS_BY_DISCOUNT, ConstantUtil.SUCCESS,
					productDetails);
			return keyVal;
		} else {
			keyVal = new ProductsResponse(ApiNameUntil.API_GET_PRODUCTS_BY_DISCOUNT, ConstantUtil.PRODUCT_NOT_AVAILABLE,
					productDetails);
			return keyVal;
		}

	}

}
