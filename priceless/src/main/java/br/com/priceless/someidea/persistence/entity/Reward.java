package br.com.priceless.someidea.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reward {

	@Id
    private long clientId;
	
	@ManyToOne(fetch=FetchType.EAGER)
    private Customer customer;
    
	@ManyToOne(fetch=FetchType.EAGER)
    private Merchant merchant;
	
    private long points;

    public Reward() {}
    
    public Reward(Customer customer, Merchant merchant, long points) {
        this.clientId = customer.getId();
        this.merchant = merchant;
        this.customer = customer;
        this.points = points;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Reward[clientId=%d, merchantName='%s', username='%s', points='%s']",
                customer.getId(), merchant.getMerchantName(), customer.getUsername(), points);
    }

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		this.clientId = customer.getId();
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}
	
	public void addPoints(long points) {
		setPoints(getPoints() + points);
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}


}