package br.com.priceless.someidea.persistence.dto;

public class RewardPoint {
	
	private Long customerId;
	private Long merchantId;
	private Long points;
	
	public Long getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Long getMerchantId() {
		return merchantId;
	}
	
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	
	public Long getPoints() {
		return points;
	}
	
	public void setPoints(Long points) {
		this.points = points;
	}
	
	
}
