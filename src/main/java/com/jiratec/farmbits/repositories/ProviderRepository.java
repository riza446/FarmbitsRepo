package com.jiratec.farmbits.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jiratec.farmbits.entity.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {

	List<Provider> findByProviderNameContaining(String providerName);

	Provider findByProviderName(String providerName);

}
