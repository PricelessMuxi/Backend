package br.com.priceless.someidea.persistence.dto;

public class RewardPoint {
	
	private Long customerId;
	private Long merchantId;
	private Float value;
	
	public Long getMerchantId()
	{
		return merchantId;
	}
	
	public void setMerchantId(Long merchantId)
	{
		this.merchantId = merchantId;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}
