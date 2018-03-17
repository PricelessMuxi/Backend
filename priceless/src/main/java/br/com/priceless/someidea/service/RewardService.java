package br.com.priceless.someidea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.priceless.someidea.persistence.dto.RewardPoint;
import br.com.priceless.someidea.persistence.entity.Customer;
import br.com.priceless.someidea.persistence.entity.Merchant;
import br.com.priceless.someidea.persistence.entity.Redemption;
import br.com.priceless.someidea.persistence.entity.Reward;
import br.com.priceless.someidea.persistence.repository.CustomerRepository;
import br.com.priceless.someidea.persistence.repository.MerchantRepository;
import br.com.priceless.someidea.persistence.repository.RedemptionRepository;
import br.com.priceless.someidea.persistence.repository.RewardRepository;
import br.com.priceless.someidea.service.exception.NotEnoughPointsToBeRedeemedException;

@Service
public class RewardService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private RewardRepository rewardRepository;
	
	@Autowired
	private RedemptionRepository redemptionRepository;
	
	public boolean addPoints(RewardPoint point) {
		
    	Customer customer;
    	Reward reward;
    	Merchant merchant;
    	
    	merchant = merchantRepository.findByMerchantId(point.getMerchantId());
    	
		customer = customerRepository.findByCustomerId(point.getCustomerId());
		
		if ((merchant != null) && (customer != null))
		{
    		reward = rewardRepository.findByCustomerAndMerchant(customer, merchant);
    		if (reward != null)
    		{
        		long rewardPoints = reward.getPoints();  		
        		long newBalance = rewardPoints + (long)  (merchant.getPointsToMoneyUnit() * point.getValue());
        		reward.setPoints(newBalance);
    		}
    		else
    			reward = new Reward(customer, merchant, (long)  (merchant.getPointsToMoneyUnit() * point.getValue()));
    		
    		rewardRepository.save(reward);
        	
    		return true;
		}
    	return false;
	}
 
	
	public Reward requestBalance(Long customerId, Long merchantId) {
		
    	Customer customer;
    	Merchant merchant;
    	
    	Reward reward;
    	
    	if (customerId != null && merchantId != null) {
    		customer = customerRepository.findByCustomerId(customerId);
    		merchant = merchantRepository.findByMerchantId(merchantId);
    		
    		reward = rewardRepository.findByCustomerAndMerchant(customer, merchant);
    	
    		return reward;
    	}
    	
    	return null;
	}
	
	public void requestRedemption(Long customerId, Long merchantId, long pointsToRedeem) throws NotEnoughPointsToBeRedeemedException {
		
    	Customer customer;
    	Merchant merchant;
    	
    	Reward reward;
    	
    	Redemption redemption;
    	
    	if (customerId != null && merchantId != null && pointsToRedeem > 0) {
    		customer = customerRepository.findByCustomerId(customerId);
    		merchant = merchantRepository.findByMerchantId(merchantId);

    		redemption = new Redemption();
    		
    		reward = rewardRepository.findByCustomerAndMerchant(customer, merchant);
    		
    		if (reward.getPoints() < pointsToRedeem) {
    			throw new NotEnoughPointsToBeRedeemedException(reward.getPoints());
    		}
    		
    		redemption.setCustomer(customer);
    		redemption.setPointsToRedeem(pointsToRedeem);
    		
    		redemptionRepository.save(redemption);
    	}
	}
	
	public Redemption requestRedemptionBalance(Long id) {
		
    	Customer customer;
    	
    	Redemption redemption;
    	
    	if (id != null) {
    		customer = customerRepository.findByCustomerId(id);

    		redemption = redemptionRepository.findByCustomer(customer);

    		return redemption;
    	}
    	
    	return null;
	}
	
	public void requestFullRedemption(Long customerId, Long merchantId) throws NotEnoughPointsToBeRedeemedException {
		
    	Customer customer;
    	Merchant merchant;
    	
    	Reward reward;
    	
    	Redemption redemption;
    	
    	if (customerId != null && merchantId != null) {
    		customer = customerRepository.findByCustomerId(customerId);
    		merchant = merchantRepository.findByMerchantId(merchantId);
    		
    		redemption = new Redemption();
    		
    		reward = rewardRepository.findByCustomerAndMerchant(customer, merchant);
    		
    		redemption.setCustomer(customer);
    		redemption.setPointsToRedeem(reward.getPoints());
    		
    		redemptionRepository.save(redemption);
    	}
	}
	
	public void redeem(Long customerId, Long merchantId) throws NotEnoughPointsToBeRedeemedException {
		
    	Customer customer;
    	Merchant merchant;
    	
    	Reward reward;
    	
    	Redemption redemption;
    	
    	if (customerId != null && merchantId != null) {
    		customer = customerRepository.findByCustomerId(customerId);
    		merchant = merchantRepository.findByMerchantId(merchantId);
    				
    		redemption = redemptionRepository.findByCustomer(customer);

    		reward = rewardRepository.findByCustomerAndMerchant(customer, merchant);
    		
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
