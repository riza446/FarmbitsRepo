package com.jiratec.farmbits.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jiratec.farmbits.entity.Category;
import com.jiratec.farmbits.entity.Product;
import com.jiratec.farmbits.entity.Provider;
import com.jiratec.farmbits.repositories.CategoryRepository;
import com.jiratec.farmbits.repositories.ProductRepository;
import com.jiratec.farmbits.repositories.ProviderRepository;
import com.jiratec.farmbits.service.ProductService;
import com.jiratec.farmbits.shared.dto.ProductDto;
import com.jiratec.farmbits.util.ConstantUtil;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProviderRepository providerRepository;

	@Override
	public List<ProductDto> getProducts() {
		List<ProductDto> returnValue = new ArrayList<>();
		try {
			List<Product> products = (List<Product>) productRepository.findAll();
			products.forEach(i -> {
				ProductDto productDto = new ProductDto();
				BeanUtils.copyProperties(i, productDto);
				returnValue.add(productDto);
			});
			
		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.PRODUCT_NOT_AVAILABLE, e);
		}
		
		return returnValue;
	}

	@Override
	public String addProviderToProduct(String providerName, String productName) {
		try {
			Provider providerEntity = providerRepository.findByProviderName(providerName);
			if (providerEntity == null) {
				return ConstantUtil.PROVIDER_NOT_AVAILABLE;
			}

			Product product = productRepository.findByProductName(productName);
			if (product == null) {
				return ConstantUtil.PRODUCT_NOT_AVAILABLE;
			}
			product.getProvider().addAll(Arrays.asList(providerEntity));

			product = productRepository.save(product);
			return product != null ? ConstantUtil.SUCCESS : ConstantUtil.FAILURE;

		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.PROVIDER_NOT_AVAILABLE + "for product {} and provider {}", productName, providerName,
					e);
			return ConstantUtil.FAILURE;
		}
	}

	@Override
	public ProductDto updateProductCategory(String categoryName, String productName) {
		ProductDto returnValue = new ProductDto();
		try {
			Category categoryEntity = categoryRepository.findByCategoryName(categoryName);
			Product product = productRepository.findByProductName(productName);

			product.setProductName(productName);
			product.setCategory(categoryEntity);
			Product finalproduct = productRepository.save(product);
			BeanUtils.copyProperties(finalproduct, returnValue);
		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.CATEGORY_NOT_AVAILABLE+ "for product {} and category {}",productName,categoryName, e);
		}
		return returnValue;
	}

	@Override
	public List<ProductDto> getProductsByCategory(String categoryName) {
		List<ProductDto> returnValue = new ArrayList<>();
		Category categoryEntity = null;
		List<Product> products = null;
		try {
			categoryEntity = categoryRepository.findByCategoryName(categoryName);
			if (categoryEntity != null) {
				products = (List<Product>) productRepository.getProdcutsByCategory(categoryEntity);
				products.forEach(i -> {
					ProductDto productDto = new ProductDto();
					BeanUtils.copyProperties(i, productDto);
					returnValue.add(productDto);
				});
			}
		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.CATEGORY_OR_PRODUCT_NOT_AVAILABLE + "for category {} and product {}", categoryEntity,
					products, e);
		}
		return returnValue;
	}

	@Override
	public List<ProductDto> getProductsByProvider(String providerName) {

		List<ProductDto> returnValue = new ArrayList<>();
		Provider providerEntity = null;
		List<Product> products = null;
		try {
		providerEntity = providerRepository.findByProviderName(providerName);
		if(providerEntity!=null) {
			products = (List<Product>) productRepository.findProductByProvider(providerEntity);
			products.forEach(i -> {
				ProductDto productDto = new ProductDto();
				BeanUtils.copyProperties(i, productDto);
				returnValue.add(productDto);
			});
		}
		
		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.PROVIDER_OR_PRODUCT_NOT_AVAILABLE + "for provider {} and product {}", providerEntity,
					products, e);
		}
		return returnValue;
	}

	@Override
	public List<ProductDto> getProductsByDiscount(double discount) {
		List<ProductDto> returnValue = new ArrayList<>();

		try {
			if (discount != 0) {
				List<Product> products = (List<Product>) productRepository.findAll();
				products.stream().filter(i -> i.getDiscount() >= discount).forEach(i -> {
					ProductDto productDto = new ProductDto();
					BeanUtils.copyProperties(i, productDto);
					returnValue.add(productDto);
				});
			}

		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.PRODUCT_NOT_AVAILABLE, e);
		}

		return returnValue;
	}

}
