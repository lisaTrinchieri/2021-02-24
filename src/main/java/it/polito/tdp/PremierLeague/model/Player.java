package it.polito.tdp.PremierLeague.model;

public class Player {
	Integer playerID;
	String name;
	int team;
	
	double efficienza;
	
	public Player(Integer playerID, String name, int team) {
		super();
		this.playerID = playerID;
		this.name = name;
		this.team = team;
		
		this.efficienza =0.0;
	}
	
	public Integer getPlayerID() {
		return playerID;
	}
	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getTeam() {
		return this.team;
	}
	
	public double getEfficienza() {
		return this.efficienza;
	}
	
	public void setEfficienza(double eff) {
		this.efficienza = eff;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playerID == null) ? 0 : playerID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (playerID == null) {
			if (other.playerID != null)
				return false;
		} else if (!playerID.equals(other.playerID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return playerID + " - " + name;
	}
	
	
	
}
