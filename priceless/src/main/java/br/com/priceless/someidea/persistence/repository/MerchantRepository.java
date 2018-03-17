package br.com.priceless.someidea.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.priceless.someidea.persistence.entity.Merchant;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {

	Merchant findByMerchantId(Long id);
	
	Merchant findByMerchantName(String username);
}