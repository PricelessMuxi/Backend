package br.com.priceless.someidea.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Reward pointsBalance(@RequestParam(value="username", required = true) String username) {
    
    	Reward reward = rewardService.requestBalance(username);
    	
    	return reward;
    }
    
    @GetMapping("/pointsConsumptionRequest")
    public String pointsConsumptionRequest(@RequestParam(value="username", required = true) String username, 
    		@RequestParam(value="pointsToRedeem", required = true) long pointsToRedeem) {
    
    	try {
			rewardService.requestRedemption(username, pointsToRedeem);
		} catch (NotEnoughPointsToBeRedeemedException e) {
			return e.getMessage();
		}
    	
    	return "Requested";
    }
    
    @GetMapping("/pointsConsumption")
    public String pointsConsumptionRequest(@RequestParam(value="username", required = true) String username) {
    
    	try {
			rewardService.redeem(username);
		} catch (NotEnoughPointsToBeRedeemedException e) {
			return e.getMessage();
		}
    	
    	return "Redeemed";
    }
    
    
//    @GetMapping("/points")
//    public RewardPoints greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new RewardPoints(counter.incrementAndGet(), String.format(template, name));
//    }
    
}