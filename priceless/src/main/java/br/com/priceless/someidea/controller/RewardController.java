package br.com.priceless.someidea.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.priceless.someidea.persistence.dto.RewardPoint;
import br.com.priceless.someidea.persistence.entity.Redemption;
import br.com.priceless.someidea.persistence.entity.Reward;
import br.com.priceless.someidea.service.MockDataService;
import br.com.priceless.someidea.service.RewardService;
import br.com.priceless.someidea.service.exception.NotEnoughPointsToBeRedeemedException;

@RestController
public class RewardController {

	@Autowired
	private MockDataService mockDataServiceService;
	
	@Autowired
	private RewardService rewardService;

	@PostConstruct
	public void init() {
		mockDataServiceService.runMockH2Database();
	}
	
	
    @GetMapping("/pointsBalance")
    public Reward pointsBalance(
    		@RequestParam(value="customerId", required = true) Long customerId,
    		@RequestParam(value="merchantId", required = true) Long merchantId) {
    
    	Reward reward = rewardService.requestBalance(customerId, merchantId);
    	
    	return reward;
    }
    
    @GetMapping("/pointsRedemptionRequest")
    public String pointsRedemptionRequest(
    		@RequestParam(value="customerId", required = true) Long customerId,
    		@RequestParam(value="merchantId", required = true) Long merchantId,
    		@RequestParam(value="pointsToRedeem", required = true) long pointsToRedeem) {
    
    	try {
			rewardService.requestRedemption(customerId, merchantId, pointsToRedeem);
		} catch (NotEnoughPointsToBeRedeemedException e) {
			return e.getMessage();
		}
    	
    	return "Requested";
    }
    
    @GetMapping("/pointsRedemptionRequest/all")
    public String pointsRedemptionRequestAll(
    		@RequestParam(value="customerId", required = true) Long customerId,
    		@RequestParam(value="merchantId", required = true) Long merchantId) {
    
    	try {
			rewardService.requestFullRedemption(customerId, merchantId);
		} catch (NotEnoughPointsToBeRedeemedException e) {
			return e.getMessage();
		}
    	
    	return "Requested";
    }
    
    @GetMapping("/pointsRedemptionBalance")
    public Redemption pointsRedemptionBalance(@RequestParam(value="id", required = true) Long id, 
    		@RequestParam(value="pointsToRedeem", required = true) long pointsToRedeem) {
    
    	Redemption redemption = rewardService.requestRedemptionBalance(id);
    	
    	return redemption;
    }
    
    @GetMapping("/pointsRedemption")
    public String pointsRedemption(
    		@RequestParam(value="customerId", required = true) Long customerId,
    		@RequestParam(value="merchantId", required = true) Long merchantId) {
    
    	try {
			rewardService.redeem(customerId, merchantId);
		} catch (NotEnoughPointsToBeRedeemedException e) {
			return e.getMessage();
		}
    	
    	return "Redeemed";
    }
    
    @PostMapping(name = "/addPoints", consumes = "application/json")
    public String addPoints(@RequestBody RewardPoint point) {    	
    	try
    	{
    		if (rewardService.addPoints(point))
    			return "Added";
    	}
		catch (Exception ex)
    	{
			return ex.getMessage();
    	}
		return "Not added";
    }
        
}