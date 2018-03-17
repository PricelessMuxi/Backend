package br.com.priceless.someidea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.priceless.someidea.persistence.entity.Customer;
import br.com.priceless.someidea.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
    @GetMapping("/customer/registration")
    public Customer customerRegistration(
    		@RequestParam(value="customerId", required = true) Long customerId,
    		@RequestParam(value="username", required = true) String username,
    		@RequestParam(value="firstName", required = true) String firstName,
    		@RequestParam(value="lastName", required = true) String lastName) {
    
    	Customer customer = customerService.registrate(customerId, username, firstName, lastName);
    	
    	return customer;
    }
    
}