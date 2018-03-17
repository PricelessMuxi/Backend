package br.com.priceless.someidea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.priceless.someidea.persistence.entity.Merchant;
import br.com.priceless.someidea.service.MerchantService;

@RestController
public class MerchantController {

	@Autowired
	private MerchantService merchantService;
	
    @PostMapping(value = "/merchant/registration", consumes = "application/json", produces = "application/json")
    public Merchant merchantRegistration(@RequestBody Merchant merchant) {
    	
    	Merchant savedMerchant = merchantService.registrate(merchant);
    	
    	return savedMerchant;
    }
    
}