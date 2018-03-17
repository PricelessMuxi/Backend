package br.com.priceless.someidea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.priceless.someidea.persistence.entity.Merchant;
import br.com.priceless.someidea.service.MerchantService;

@RestController
public class MerchantController {

	@Autowired
	private MerchantService merchantService;
	
    @GetMapping("/merchant/registration")
    public Merchant customerRegistration(
    		@RequestParam(value="merchantId", required = true) Long merchantId,
    		@RequestParam(value="merchantName", required = true) String merchantName,
    		@RequestParam(value="pointsToMoneyUnit", required = true) Long pointsToMoneyUnit) {   
    	
    	Merchant merchant = merchantService.registrate(merchantId, merchantName, pointsToMoneyUnit);
    	
    	return merchant;
    }
    
}