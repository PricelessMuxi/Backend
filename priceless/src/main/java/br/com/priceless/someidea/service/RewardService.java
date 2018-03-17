package br.com.priceless.someidea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.priceless.someidea.persistence.entity.Customer;
import br.com.priceless.someidea.persistence.entity.Reward;
import br.com.priceless.someidea.persistence.repository.CustomerRepository;
import br.com.priceless.someidea.persistence.repository.RewardRepository;

@Service
public class RewardService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RewardRepository rewardRepository;
	
	public Reward query(String username) {
		
    	Customer customer;
    	Reward reward;
    	
    	if (username != null) {
    		customer = customerRepository.findByUsername(username);

    		reward = rewardRepository.findByCustomer(customer);
    	
    		return reward;
    	}
    	
    	return null;
	}
	
	public boolean save(Reward reward)
	{
		if (reward != null)
		{
			rewardRepository.save(reward);
			return true;
		}
		return false;	
	}
}
