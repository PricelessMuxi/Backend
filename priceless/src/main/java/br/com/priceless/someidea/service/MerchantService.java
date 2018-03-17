package br.com.priceless.someidea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.priceless.someidea.persistence.entity.Merchant;
import br.com.priceless.someidea.persistence.repository.MerchantRepository;

@Service
public class MerchantService {

	@Autowired
	private MerchantRepository merchantRepository;
	
	public Merchant registrate(Merchant merchant) {
		
		merchantRepository.save(merchant);
		
		return merchant;
	}
	
}
