package br.com.priceless.someidea.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.priceless.someidea.persistence.entity.Customer;
import br.com.priceless.someidea.persistence.entity.Redemption;

public interface RedemptionRepository extends CrudRepository<Redemption, Long> {

	Redemption findByCustomer(Customer customer);

}