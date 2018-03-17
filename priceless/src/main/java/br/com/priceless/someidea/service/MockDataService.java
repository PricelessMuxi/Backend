package br.com.priceless.someidea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.priceless.someidea.persistence.entity.Customer;
import br.com.priceless.someidea.persistence.entity.Merchant;
import br.com.priceless.someidea.persistence.entity.Reward;
import br.com.priceless.someidea.persistence.repository.CustomerRepository;
import br.com.priceless.someidea.persistence.repository.MerchantRepository;
import br.com.priceless.someidea.persistence.repository.RewardRepository;

@Service
public class MockDataService {

	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RewardRepository rewardRepository;
	
	public void runMockH2Database() {
		
		Merchant merchant = new Merchant();
		
		merchant.setMerchantId(42995832000135L);
		merchant.setMerchantName("FingerStore");
		merchant.setPointsToMoneyUnit(100L);
		
		merchantRepository.save(merchant);
		
    	Customer customer = new Customer();
    	
    	customer.setCustomerId(38893307707L);
    	customer.setUsername("blopa");
    	customer.setFirstName("Pablo");
    	customer.setLastName("Montenegro");
    	customer.setCustomerId(38893307707L);
    	
    	customerRepository.save(customer);
    	
    	Reward reward = new Reward();
    	
    	reward.setCustomer(customer);
    	reward.setMerchant(merchant);
    	reward.setPoints(1000L);
    	
    	rewardRepository.save(reward);
	}
	
}
