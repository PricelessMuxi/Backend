package br.com.priceless.someidea.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.priceless.someidea.persistence.entity.Redemption;
import br.com.priceless.someidea.persistence.dto.RewardPoint;
import br.com.priceless.someidea.persistence.entity.Customer;
import br.com.priceless.someidea.persistence.entity.Reward;
import br.com.priceless.someidea.service.CustomerService;
import br.com.priceless.someidea.service.MockDataService;
import br.com.priceless.someidea.service.RewardService;
import br.com.priceless.someidea.service.exception.NotEnoughPointsToBeRedeemedException;

@RestController
public class RewardController {

	@Autowired
	private MockDataService mockDataServiceService;
	
	@Autowired
	private RewardService rewardService;

	@Autowired
	private CustomerService customerService;
	
	@PostConstruct
	public void init() {
		mockDataServiceService.runMockH2Database();
	}
	
	
    @GetMapping("/pointsBalance")
    public Reward pointsBalance(@RequestParam(value="username", required = true) String username) {
    
    	Reward reward = rewardService.requestBalance(username);
    	
    	return reward;
    }
    
    @GetMapping("/pointsRedemptionRequest")
    public String pointsRedemptionRequest(@RequestParam(value="username", required = true) String username, 
    		@RequestParam(value="pointsToRedeem", required = true) long pointsToRedeem) {
    
    	try {
			rewardService.requestRedemption(username, pointsToRedeem);
		} catch (NotEnoughPointsToBeRedeemedException e) {
			return e.getMessage();
		}
    	
    	return "Requested";
    }
    
    @GetMapping("/pointsRedemptionRequest/all")
    public String pointsRedemptionRequestAll(@RequestParam(value="username", required = true) String username) {
    
    	try {
			rewardService.requestFullRedemption(username);
		} catch (NotEnoughPointsToBeRedeemedException e) {
			return e.getMessage();
		}
    	
    	return "Requested";
    }
    
    @GetMapping("/pointsRedemptionBalance")
    public Redemption pointsRedemptionBalance(@RequestParam(value="username", required = true) String username, 
    		@RequestParam(value="pointsToRedeem", required = true) long pointsToRedeem) {
    
    	Redemption redemption = rewardService.requestRedemptionBalance(username);
    	
    	return redemption;
    }
    
    @GetMapping("/pointsRedemption")
    public String pointsRedemption(@RequestParam(value="username", required = true) String username) {
    
    	try {
			rewardService.redeem(username);
		} catch (NotEnoughPointsToBeRedeemedException e) {
			return e.getMessage();
		}
    	
    	return "Redeemed";
    }
    
    @PostMapping(name = "/addPoints", consumes = "application/json")
    public String addPoints(@RequestBody RewardPoint point) {
    	Customer customer = null;
    	String username;
    	long points;
    	
    	try
    	{
        	username = point.getUserName();
        	points = point.getPoints();
        	Reward reward = rewardService.query(username);
        	if (reward == null)
        	{
        		customer = customerService.findByUserName(username);
        		if (customer != null)
        		{
            		reward = new Reward();
                	reward.setCustomer(customer);
        		}
        		else
        			return "Customer not found";
        	}
        	reward.addPoints(points);
        	
    		if (rewardService.save(reward))
    			return "Added";
    	}
		catch (Exception ex)
    	{
			return ex.getMessage();
    	}
		return "Not added";
    }
        
}