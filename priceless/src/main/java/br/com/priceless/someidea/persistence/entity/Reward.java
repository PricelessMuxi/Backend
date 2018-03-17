package br.com.priceless.someidea.persistence.entity;

public class Reward {

    private long clientId;
    private String username;
    private long points;

    protected Reward() {}
    
    public Reward(long clientId, String username, long points) {
        this.clientId = clientId;
        this.username = username;
        this.points = points;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Reward[clientId=%d, username='%s', points='%s']",
                clientId, username, points);
    }

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

}