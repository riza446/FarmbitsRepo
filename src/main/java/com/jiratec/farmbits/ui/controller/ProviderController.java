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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jiratec.farmbits.service.ProductService;
import com.jiratec.farmbits.service.ProviderService;
import com.jiratec.farmbits.shared.dto.ProviderDto;
import com.jiratec.farmbits.ui.model.response.ProviderDetails;
import com.jiratec.farmbits.ui.model.response.ProviderResponse;
import com.jiratec.farmbits.util.ApiNameUntil;
import com.jiratec.farmbits.util.ConstantUtil;

/**
 * @author Rizauddin Mohmmad
 * This is the controller class which has all providers related Restapi  calls
 * like adding  provider, getting all providers, get provider by name , adding provider to product 
 * 
 * 
 *
 */
@RestController
@RequestMapping("/riza-farmbits")
public class ProviderController {

	@Autowired
	ProviderService providerService;
	
	@Autowired
	ProductService productService;

	
	@PostMapping("/addProvider")
	public ProviderResponse addProvider(@RequestParam(value = "providerName") String providerName,
			@RequestParam(value = "email") String email) {

		if (!(StringUtils.hasLength(providerName) && StringUtils.hasLength(email))) {
			return new ProviderResponse(ApiNameUntil.API_ADD_PROVIDER, ConstantUtil.INPUTS_EMPTY);
		}
		return new ProviderResponse(ApiNameUntil.API_ADD_PROVIDER, providerService.addProvider(providerName, email));
	}

	

	@PostMapping("/addProviderForProduct")
	public ProviderResponse addProviderForProducts(@RequestParam(value = "providerName") String providerName,
			@RequestParam(value = "productName") String productName) {
		if (!(StringUtils.hasLength(providerName) && StringUtils.hasLength(productName))) {
			return new ProviderResponse(ApiNameUntil.API_ADD_PROVIDER_TO_PRODUCT, ConstantUtil.INPUTS_EMPTY);
		}
		return new ProviderResponse(ApiNameUntil.API_ADD_PROVIDER_TO_PRODUCT,
				providerService.addProviderToProduct(providerName, productName));
	}

	

	@GetMapping("/getAllProviders")
	public ProviderResponse getAllProviders() {
		ProviderResponse keyVal;
		List<ProviderDetails> providerDetails = new ArrayList<>();

		List<ProviderDto> providers = providerService.getAllProviders();
		if (!CollectionUtils.isEmpty(providers)) {
			providers.forEach(i -> {
				ProviderDetails provider = new ProviderDetails();
				BeanUtils.copyProperties(i, provider);
				providerDetails.add(provider);

			});

			keyVal = new ProviderResponse(ApiNameUntil.API_GET_PROVIDERS, ConstantUtil.SUCCESS, providerDetails);
		} else
			keyVal = new ProviderResponse(ApiNameUntil.API_GET_PROVIDERS, ConstantUtil.PRODUCT_NOT_AVAILABLE,
					providerDetails);
		return keyVal;

	}

	@GetMapping("/getProvider/{Id}")
	public ProviderResponse getProvider(@PathVariable("Id") String id) {
		ProviderResponse keyVal = null;
		if (!StringUtils.hasLength(id) )
			return new ProviderResponse(ApiNameUntil.API_GET_PROVIDER, ConstantUtil.INPUTS_EMPTY);
		List<ProviderDetails> providerDetails = new ArrayList<>();

		List<ProviderDto> providers = providerService.getProviderByName(id);
		if (!CollectionUtils.isEmpty(providers)) {
			providers.forEach(i -> {
				ProviderDetails provider = new ProviderDetails();
				BeanUtils.copyProperties(i, provider);
				providerDetails.add(provider);

			});

			keyVal = new ProviderResponse(ApiNameUntil.API_GET_PROVIDER, ConstantUtil.SUCCESS,
					providerDetails);
			return keyVal;
		} else {
			keyVal = new ProviderResponse(ApiNameUntil.API_GET_PROVIDER, ConstantUtil.PRODUCT_NOT_AVAILABLE,
					providerDetails);
			return keyVal;
		}

	}

	@PutMapping("/updateProviderEmail")
	public ProviderResponse updateProvider(@RequestParam(value = "providerName") String providerName,
			@RequestParam(value = "email") String email) {
		
		
		if (!(StringUtils.hasLength(providerName)&& StringUtils.hasLength(email)) )
			return new ProviderResponse(ApiNameUntil.API_UPDATE_PROVIDER, ConstantUtil.INPUTS_EMPTY);
		
		return new ProviderResponse(ApiNameUntil.API_UPDATE_PROVIDER, providerService.updateProvider(providerName, email)); 

	}

	@DeleteMapping("/deleteProvider")
	public ProviderResponse deleteProvider(@RequestParam(value = "providerName") String providerName) {
		if (!StringUtils.hasLength(providerName) )
			return new ProviderResponse(ApiNameUntil.API_UPDATE_PROVIDER, ConstantUtil.INPUTS_EMPTY);
		
		return new ProviderResponse(ApiNameUntil.API_UPDATE_PROVIDER, providerService.deleteProvider(providerName)); 
	}

}
