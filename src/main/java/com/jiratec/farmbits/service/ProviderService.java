package com.jiratec.farmbits.service;

import java.util.List;

import com.jiratec.farmbits.shared.dto.ProviderDto;

public interface ProviderService {
	
	String addProvider(String providerName, String email);
	String addProviderToProduct(String providerName, String productName);
	List<ProviderDto> getAllProviders();
	List<ProviderDto> getProviderByName(String providerName);
	String updateProvider(String providerName,String email);
	String deleteProvider(String providerName);

}
