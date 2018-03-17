package br.com.priceless.someidea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.priceless.someidea.persistence.entity.Customer;
import br.com.priceless.someidea.persistence.entity.Reward;
import br.com.priceless.someidea.persistence.repository.CustomerRepository;
import br.com.priceless.someidea.persistence.repository.RewardRepository;

@Service
public class MockDataService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RewardRepository rewardRepository;
	
	public void runMockH2Database() {
		
    	Customer customer = new Customer();
    	
    	customer.setId(1L);
    	customer.setUsername("blopa");
    	customer.setFirstName("Pablo");
    	customer.setLastName("Montenegro");
    	
    	customerRepository.save(customer);
    	
    	Reward reward = new Reward();
    	
    	reward.setCustomer(customer);
    	reward.setPoints(1000L);
    	
    	rewardRepository.save(reward);
	}
	
}
