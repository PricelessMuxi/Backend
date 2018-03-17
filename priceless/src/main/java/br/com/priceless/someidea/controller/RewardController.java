package br.com.priceless.someidea.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.priceless.someidea.persistence.entity.Reward;
import br.com.priceless.someidea.service.MockDataService;
import br.com.priceless.someidea.service.RewardService;

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
	
	
    @GetMapping("/queryPoints")
    public Reward queryPoints(@RequestParam(value="username") String username) {
    
    	Reward reward = rewardService.query(username);
    	
    	return reward;
    }
    
//    @GetMapping("/points")
//    public RewardPoints greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new RewardPoints(counter.incrementAndGet(), String.format(template, name));
//    }
    
}