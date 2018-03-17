package br.com.priceless.someidea.persistence.dto;

public class RewardPoint {
	
	private String username;
	private long points;
	
	public String getUserName()
	{
		return username;
	}
	
	public void setUserName(String username)
	{
		this.username = username;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}
	
}
