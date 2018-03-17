package br.com.priceless.someidea.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Merchant {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long merchantId;
    private String merchantName;
    private Long pointsToMoneyUnit;

    public Merchant() {}

    public Merchant(Long merchantId, String merchantName, Long pointsToMoneyUnit) {
        this.merchantId = merchantId;
    	this.merchantName = merchantName;
    	this.pointsToMoneyUnit = pointsToMoneyUnit;
    }

    @Override
    public String toString() {
        return String.format(
                "Merchant[id=%d, merchantId=%d, merchantName='%s']",
                id, merchantId, merchantName);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Long getPointsToMoneyUnit() {
		return pointsToMoneyUnit;
	}

	public void setPointsToMoneyUnit(Long pointsToMoneyUnit) {
		this.pointsToMoneyUnit = pointsToMoneyUnit;
	}

}