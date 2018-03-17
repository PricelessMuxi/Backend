package br.com.priceless.someidea.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Redemption {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	
	@OneToOne(fetch=FetchType.EAGER)
    private Customer customer;
    
    private long pointsToRedeem;

    public Redemption() {}
    
    public Redemption(long id, Customer customer, long pointsToRedeem) {
        this.id = customer.getId();
        this.customer = customer;
        this.pointsToRedeem = pointsToRedeem;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Reward[clientId=%d, username='%s', points='%s']",
                id, customer.getUsername(), pointsToRedeem);
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public long getPointsToRedeem() {
		return pointsToRedeem;
	}

	public void setPointsToRedeem(long pointsToRedeem) {
		this.pointsToRedeem = pointsToRedeem;
	}


}