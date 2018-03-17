package br.com.priceless.someidea.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.priceless.someidea.persistence.entity.Customer;
import br.com.priceless.someidea.persistence.entity.Reward;
import br.com.priceless.someidea.persistence.repository.CustomerRepository;
import br.com.priceless.someidea.service.RewardService;

@RestController
public class RewardController {

	private CustomerRepository customerRepository;
	
	@Autowired
	private RewardService rewardService;
	
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/queryPoints")
    public Reward queryPoints(
    		@RequestParam(value="username") String username, 
    		@RequestParam(value="firstName") String firstName, 
    		@RequestParam(value="lastName") String lastName) {
    
    	Customer customer;
    	
    	if (username != null) {
    		customer = customerRepository.findByUsername(username);
    	}
    	
//    	rewardService
    	
//    	RewardPoints rewardPoints = new RewardPoints(counter.incrementAndGet(), String.format(template, username), "1000");
    	
//        return rewardPoints;
    	return null;
    }
    
//    @GetMapping("/points")
//    public RewardPoints greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new RewardPoints(counter.incrementAndGet(), String.format(template, name));
//    }
}