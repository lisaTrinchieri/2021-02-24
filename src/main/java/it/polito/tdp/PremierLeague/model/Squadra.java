package it.polito.tdp.PremierLeague.model;

public class Squadra {
	
	private int id;
	private int numGiocatori;
	private int espulsi;
	private int gol;
	
	public Squadra(int id) {
		this.id = id;
		
		this.numGiocatori = 11;
		this.espulsi =0;
		this.gol =0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumGiocatori() {
		return numGiocatori;
	}

	public void setNumGiocatori(int numGiocatori) {
		this.numGiocatori = numGiocatori;
	}

	public int getEspulsi() {
		return espulsi;
	}

	public void setEspulsi(int espulsi) {
		this.espulsi = espulsi;
	}

	public int getGol() {
		return gol;
	}

	public void setGol(int gol) {
		this.gol = gol;
	}
	
	public void segnaGol() {
		this.gol++;
	}
	
	public void espulsione() {
		this.espulsi ++;
		this.numGiocatori--;
	}

}
