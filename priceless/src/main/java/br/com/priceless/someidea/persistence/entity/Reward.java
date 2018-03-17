package br.com.priceless.someidea.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Reward {

	@Id
    private long clientId;
	
	@OneToOne(fetch=FetchType.EAGER)
    private Customer customer;
    
    private long points;

    public Reward() {}
    
    public Reward(Customer customer, long points) {
        this.clientId = customer.getId();
        this.customer = customer;
        this.points = points;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Reward[clientId=%d, username='%s', points='%s']",
                customer.getId(), customer.getUsername(), points);
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


}