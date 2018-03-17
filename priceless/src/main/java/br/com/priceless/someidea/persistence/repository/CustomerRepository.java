package br.com.priceless.someidea.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.priceless.someidea.persistence.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByCustomerId(Long id);
	
	Customer findByUsername(String username);
	
	List<Customer> findByFirstName(String firstName);
	
    List<Customer> findByLastName(String lastName);
}