package com.jiratec.farmbits.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jiratec.farmbits.entity.Provider;

public interface ProviderRepository extends CrudRepository<Provider, Long> {

	List<Provider> findByProviderNameContaining(String providerName);

	Provider findByProviderName(String providerName);

}
