package br.com.priceless.someidea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.priceless.someidea.persistence.entity.Customer;
import br.com.priceless.someidea.persistence.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer registrate(Long customerId, String username, String firstName, String lastName) {
		
		Customer customer = new Customer(customerId, username, firstName, lastName);
		
		customerRepository.save(customer);
		
		return customer;
	}
	
}
