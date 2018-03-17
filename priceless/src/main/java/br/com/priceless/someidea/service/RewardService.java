package br.com.priceless.someidea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.priceless.someidea.persistence.entity.Customer;
import br.com.priceless.someidea.persistence.entity.Redemption;
import br.com.priceless.someidea.persistence.entity.Reward;
import br.com.priceless.someidea.persistence.repository.CustomerRepository;
import br.com.priceless.someidea.persistence.repository.RedemptionRepository;
import br.com.priceless.someidea.persistence.repository.RewardRepository;
import br.com.priceless.someidea.service.exception.NotEnoughPointsToBeRedeemedException;

@Service
public class RewardService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RewardRepository rewardRepository;
	
	@Autowired
	private RedemptionRepository redemptionRepository;
	
	public Reward requestBalance(String username) {
		
    	Customer customer;
    	Reward reward;
    	
    	if (username != null) {
    		customer = customerRepository.findByUsername(username);

    		reward = rewardRepository.findByCustomer(customer);
    	
    		return reward;
    	}
    	
    	return null;
	}
	
	public void requestRedemption(String username, long pointsToRedeem) throws NotEnoughPointsToBeRedeemedException {
		
    	Customer customer;
    	
    	Reward reward;
    	
    	Redemption redemption;
    	
    	if (username != null && pointsToRedeem > 0) {
    		customer = customerRepository.findByUsername(username);

    		redemption = new Redemption();
    		
    		reward = rewardRepository.findByCustomer(customer);
    		
    		if (reward.getPoints() < pointsToRedeem) {
    			throw new NotEnoughPointsToBeRedeemedException(reward.getPoints());
    		}
    		
    		redemption.setCustomer(customer);
    		redemption.setPointsToRedeem(pointsToRedeem);
    		
    		redemptionRepository.save(redemption);
    	}
	}
	
	public void redeem(String username) throws NotEnoughPointsToBeRedeemedException {
		
    	Customer customer;
    	
    	Reward reward;
    	
    	Redemption redemption;
    	
    	if (username != null) {
    		customer = customerRepository.findByUsername(username); 
    				
    		redemption = redemptionRepository.findByCustomer(customer);

    		reward = rewardRepository.findByCustomer(customer);
    		
    		long rewardPoints = reward.getPoints();
    		long redemptionPoints = redemption.getPointsToRedeem();
    		
    		if (rewardPoints < redemptionPoints) {
    			throw new NotEnoughPointsToBeRedeemedException(reward.getPoints());
    		}
    		
    		long newBalance = rewardPoints - redemptionPoints;
    		
    		reward.setPoints(newBalance);
    		
    		rewardRepository.save(reward);
    		
    		redemptionRepository.delete(redemption);
    	}
	}
}
