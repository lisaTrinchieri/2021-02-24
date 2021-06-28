package it.polito.tdp.PremierLeague.model;


public class Evento implements Comparable<Evento>{
	
	enum EventType {
		GOAL,
		ESPULSIONE,
		INFORTUNIO,
		
	}; 
	private int numAzione;
	private int squadra;
	private EventType type;
	
	public Evento(int n, int s, EventType type) {
		super();
		this.numAzione =n;
		this.squadra = s;
		this.type = type;
	}
	public int getTeam() {
		return this.squadra;
	}
	public void setTeam(int p) {
		this.squadra = p;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public int getNumAzione() {
		return numAzione;
	}
	public void setNumAzione(int numAzione) {
		this.numAzione = numAzione;
	}
	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.numAzione-o.numAzione;
	}
	
	
	
	
	
	
	

}
