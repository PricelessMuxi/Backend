package br.com.priceless.someidea.persistence.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;    
    private Merchant merchant;
    private Date expiryDate;
    private Long pointsToMoneyUnit;

    public Campaign() {}

    public Campaign(Merchant merchant, Date expiryDate, Long pointsToMoneyUnit) {
    	this.merchant = merchant;
    	this.expiryDate = expiryDate;
        this.pointsToMoneyUnit = pointsToMoneyUnit;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, merchantName='%s', expiryDate='%s', pointsToMoneyUnit='%s']",
                id, merchant.getMerchantName(), expiryDate.toString(), pointsToMoneyUnit);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Long getPointsToMoneyUnit() {
		return pointsToMoneyUnit;
	}

	public void setPointsToMoneyUnit(Long pointsToMoneyUnit) {
		this.pointsToMoneyUnit = pointsToMoneyUnit;
	}


}