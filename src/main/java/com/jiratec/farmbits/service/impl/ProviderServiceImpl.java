package com.jiratec.farmbits.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import com.jiratec.farmbits.entity.Product;
import com.jiratec.farmbits.entity.Provider;
import com.jiratec.farmbits.repositories.ProductRepository;
import com.jiratec.farmbits.repositories.ProviderRepository;
import com.jiratec.farmbits.service.ProviderService;
import com.jiratec.farmbits.shared.dto.ProviderDto;
import com.jiratec.farmbits.util.ConstantUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProviderServiceImpl  implements ProviderService{
	
	@Autowired
	ProviderRepository providerRepository;
	
	@Autowired
	ProductRepository productRepository;
	@Override
	public String addProvider(String providerName, String email) {

		try {
			Provider providerEntity = providerRepository.findByProviderName(providerName);
			if (providerEntity != null) {
				return ConstantUtil.PROVIDER_ALREADY_AVAILABLE;
			}
			Provider provider = new Provider();
			provider.setProviderName(providerName);
			provider.setEmail(email);
			providerEntity = providerRepository.save(provider);
			return providerEntity != null ? ConstantUtil.ADD_SUCCESS : ConstantUtil.FAILURE;

		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.FAILURE, e);
			return ConstantUtil.FAILURE;
		}
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
			return product != null ? ConstantUtil.ADD_SUCCESS : ConstantUtil.FAILURE;

		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.PROVIDER_NOT_AVAILABLE + "for product {} and provider {}", productName, providerName,
					e);
			return ConstantUtil.FAILURE;
		}
	}

	@Override
	public List<ProviderDto> getAllProviders() {
		
		List<ProviderDto> returnValues=new ArrayList<>();
		try {
		List<Provider> providers= (List<Provider>) providerRepository.findAll();
		providers.forEach(i->{
			ProviderDto provider=new ProviderDto();
			BeanUtils.copyProperties(i, provider);
			returnValues.add(provider);
		});
		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.PROVIDER_NOT_AVAILABLE + "for provider {}", e);
		}
		return returnValues;
	}

	@Override
	public List<ProviderDto> getProviderByName(String providerName) {
		List<ProviderDto> returnValue = new ArrayList<>();
		Provider providerEntity = null;
		
		try {
			providerEntity = providerRepository.findByProviderName(providerName);
			
			ProviderDto provider=new ProviderDto();
				BeanUtils.copyProperties(providerEntity, provider);
				returnValue.add(provider);
			
			
		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.PROVIDER_NOT_AVAILABLE + "for provider {}", providerEntity, e);
		}
		return returnValue;
	}

	@Override
	public String updateProvider(String providerName, String email) {

		try {
			Provider providerEntity = providerRepository.findByProviderName(providerName);
			if (providerEntity == null) {
				return ConstantUtil.PROVIDER_NOT_AVAILABLE;
			}
			Provider provider = new Provider();
			provider.setProviderName(providerName);
			provider.setEmail(email);
			providerEntity = providerRepository.save(provider);
			return providerEntity != null ? ConstantUtil.ADD_SUCCESS : ConstantUtil.FAILURE;

		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.FAILURE, e);
			return ConstantUtil.FAILURE;
		}
	}

	@Override
	public String deleteProvider(String providerName) {
		try {
			Provider providerEntity = providerRepository.findByProviderName(providerName);
			if (providerEntity == null) {
				return ConstantUtil.PROVIDER_NOT_AVAILABLE;
			}
			providerRepository.delete(providerEntity);
			return providerEntity != null ? ConstantUtil.DELETE_SUCCESS : ConstantUtil.FAILURE;

		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.FAILURE, e);
			return ConstantUtil.FAILURE;
		}
	}

}
