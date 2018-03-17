package br.com.priceless.someidea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.priceless.someidea.persistence.entity.Merchant;
import br.com.priceless.someidea.persistence.repository.MerchantRepository;

@Service
public class MerchantService {

	@Autowired
	private MerchantRepository merchantRepository;
	
	public Merchant registrate(Long merchantId, String merchantName, Long pointsToMoneyUnit) {
		
		Merchant merchant = new Merchant(merchantId, merchantName, pointsToMoneyUnit);
		
		merchantRepository.save(merchant);
		
		return merchant;
	}
	
}
