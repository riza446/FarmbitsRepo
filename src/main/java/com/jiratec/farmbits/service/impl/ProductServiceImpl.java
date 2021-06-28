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


/**
* @author Rizauddin Mohammad
* It is an ProductService interface implementation class for business logic
* It is loose coupled with @ProductRepository, @CategoryRepository, @ProviderRepository  repositories USING @Autowired annotation
*/

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
	public String addProduct(ProductDto productToSave, String providerName, String categoryName) {

		try {

			Product product = new Product();
			BeanUtils.copyProperties(productToSave, product);
			Category categoryEntity = new Category();
			Product productSearch = productRepository.findByProductName(product.getProductName());
			if (productSearch != null) {
				return ConstantUtil.PRODUCT_ALREADY_AVAILABLE;
			}

			categoryEntity = categoryRepository.findByCategoryName(categoryName);
			if (categoryEntity == null) {
				return ConstantUtil.CATEGORY_NOT_AVAILABLE;
			}
			product.setCategory(categoryEntity);
			product = productRepository.save(product);

			Provider providerEntity = providerRepository.findByProviderName(providerName);
			if (providerEntity == null) {
				return ConstantUtil.PROVIDER_NOT_AVAILABLE;
			}

			product.getProvider().addAll(Arrays.asList(providerEntity));
			product = productRepository.save(product);
			return product != null ? ConstantUtil.ADD_SUCCESS : ConstantUtil.FAILURE;

		} catch (EmptyResultDataAccessException e) {
			log.error(
					ConstantUtil.PROVIDER_OR_PRODUCT_NOT_AVAILABLE + "for product {} and category {} and provider {} ",
					productToSave, categoryName, providerName, e);
			return ConstantUtil.FAILURE;
		}
	}



	@Override
	public String changeProductDiscount(String productName, double discount) {
		try {
			Product productSearch = productRepository.findByProductName(productName);
			if (productSearch == null) {
				return ConstantUtil.PRODUCT_NOT_AVAILABLE;
			}
			productSearch.setDiscount(discount);
			productSearch = productRepository.save(productSearch);
			return ConstantUtil.UPDATE_SUCCESS;
		} catch (EmptyResultDataAccessException e) {
			log.error(
					ConstantUtil.PRODUCT_NOT_AVAILABLE + "for product  ", productName, e);
			return ConstantUtil.FAILURE;
		}
	}



	@Override
	public String changeProductPrice(String productName, double price) {
		try {
			Product productSearch = productRepository.findByProductName(productName);
			if (productSearch == null) {
				return ConstantUtil.PRODUCT_NOT_AVAILABLE;
			}
			productSearch.setPrice(price);
			productSearch = productRepository.save(productSearch);
			return ConstantUtil.UPDATE_SUCCESS;
		} catch (EmptyResultDataAccessException e) {
			log.error(
					ConstantUtil.PRODUCT_NOT_AVAILABLE + "for product  ", productName, e);
			return ConstantUtil.FAILURE;
		}
	}


	@Override
	public String deleteProduct(String productName) {
		try {
			Product productSearch = productRepository.findByProductName(productName);
			if (productSearch == null) {
				return ConstantUtil.PRODUCT_NOT_AVAILABLE;
			}
			
			productRepository.delete(productSearch);
			return ConstantUtil.DELETE_SUCCESS;
		} catch (EmptyResultDataAccessException e) {
			log.error(
					ConstantUtil.PRODUCT_NOT_AVAILABLE + "for product  ", productName, e);
			return ConstantUtil.FAILURE;
		}
	}


	@Override
	public String updateProductCategory(String categoryName, String productName) {
		try {
			Category categoryEntity = categoryRepository.findByCategoryName(categoryName);
			if (categoryEntity == null) {
				return ConstantUtil.CATEGORY_NOT_AVAILABLE;
			}
			Product product = productRepository.findByProductName(productName);
			if (product == null) {
				return ConstantUtil.PRODUCT_NOT_AVAILABLE;
			}
			product.setProductName(productName);
			product.setCategory(categoryEntity);
			Product finalproduct = productRepository.save(product);
			return finalproduct != null ? ConstantUtil.UPDATE_SUCCESS : ConstantUtil.FAILURE;
		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.CATEGORY_NOT_AVAILABLE + "for product {} and category {}", productName, categoryName,
					e);
			return ConstantUtil.FAILURE;
		}

	}

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
	public List<ProductDto> getProductsByName(String productName) {
		List<ProductDto> returnValue = new ArrayList<>();
		try {
			Product product = productRepository.findByProductName(productName);
			
				ProductDto productDto = new ProductDto();
				BeanUtils.copyProperties(product, productDto);
				returnValue.add(productDto);
			

		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.PRODUCT_NOT_AVAILABLE, e);
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
			if (providerEntity != null) {
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

			List<Product> products = (List<Product>) productRepository.findAll();
			products.stream().filter(i -> i.getDiscount() >= discount).forEach(i -> {
				ProductDto productDto = new ProductDto();
				BeanUtils.copyProperties(i, productDto);
				returnValue.add(productDto);
			});

		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.PRODUCT_NOT_AVAILABLE, e);
		}

		return returnValue;
	}





}
