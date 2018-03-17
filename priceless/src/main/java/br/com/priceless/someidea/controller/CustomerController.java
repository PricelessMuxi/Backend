package br.com.priceless.someidea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.priceless.someidea.persistence.entity.Customer;
import br.com.priceless.someidea.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
    @PostMapping(value = "/customer/registration", consumes = "application/json", produces = "application/json")
    public Customer customerRegistration(@RequestBody Customer customer) {
    	
    	Customer savedCustomer = customerService.registrate(customer);
    	
    	return savedCustomer;
    }
    
}