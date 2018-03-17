package br.com.priceless.someidea.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.priceless.someidea.persistence.entity.Customer;
import br.com.priceless.someidea.persistence.entity.Reward;

public interface RewardRepository extends CrudRepository<Reward, Long> {
	Reward findByCustomer(Customer username);
}