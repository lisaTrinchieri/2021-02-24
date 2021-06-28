package it.polito.tdp.PremierLeague.model;

public class GiocatoreMigliore {

	private Player p; 
	private double valore;
	public GiocatoreMigliore(Player p, double valore) {
		super();
		this.p = p;
		this.valore = valore;
	}
	public Player getP() {
		return p;
	}
	public void setP(Player p) {
		this.p = p;
	}
	public double getValore() {
		return valore;
	}
	public void setValore(double valore) {
		this.valore = valore;
	}
	
	public String toString() {
		return ""+this.p.getName()+", delta: "+this.getValore();
	}
	
	
}
