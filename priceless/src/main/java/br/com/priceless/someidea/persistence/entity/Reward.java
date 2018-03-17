package br.com.priceless.someidea.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Reward {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long clientId;
	
	@OneToOne(fetch=FetchType.EAGER)
    private Customer customer;
    
    private long points;

    protected Reward() {}
    
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

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}


}