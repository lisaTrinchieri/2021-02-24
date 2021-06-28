package it.polito.tdp.PremierLeague.model;

import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.PremierLeague.model.Evento.EventType;

public class Simulatore {
	
	private PriorityQueue<Evento> queue;
	
	private Graph<Player, DefaultWeightedEdge> grafo;
	private int num;
	private GiocatoreMigliore bestPlayer;
	
	private int N;
	
	private Squadra t1;
	private Squadra t2;
	
	
	public void init(int N, Graph<Player, DefaultWeightedEdge> grafo, GiocatoreMigliore best) {
		this.N = N;
		this.grafo = grafo;
		this.queue = new PriorityQueue<>();
		this.bestPlayer = best;
		
		this.t1 = new Squadra(this.bestPlayer.getP().getTeam());
		this.t2 = new Squadra(1);
		
		int i=1;
		while(i<=N)
		{   double prob = Math.random();
		   
		    if(prob < 0.50)
		    {   Evento e = new Evento(i, 0, EventType.GOAL);
		        this.queue.add(e); }
		    else if(prob<0.80)
		    {   Evento e = new Evento(i, 0, EventType.ESPULSIONE);
		        this.queue.add(e); }
		    else
		    {   Evento e = new Evento(i, 0, EventType.INFORTUNIO);
	        this.queue.add(e); }
		    
		    i++;  
		}
	 }
	
	
	public void aggiungiEventi(int n) {
		
		int i=1+this.N;
		while(i<=(n+this.N))
		{   double prob = Math.random();
		   
		    if(prob < 0.50)
		    {   Evento e = new Evento(i+this.N, 0, EventType.GOAL);
		        this.queue.add(e); }
		    else if(prob<0.80)
		    {   Evento e = new Evento(i+this.N, 0, EventType.ESPULSIONE);
		        this.queue.add(e); }
		    else
		    {   Evento e = new Evento(i+this.N, 0, EventType.INFORTUNIO);
	        this.queue.add(e); }
		    
		    i++;  
		}
	}  
		
	  public void run() {
		while(!this.queue.isEmpty()) {
			Evento e = this.queue.poll();
			processEvent(e);
			System.out.println(e);
		}
		
	}
	
	private void processEvent(Evento e) {
		switch(e.getType()) {
		    case GOAL:
		    	if(this.t1.getNumGiocatori() > this.t2.getNumGiocatori())
		    	{	this.t1.segnaGol();
		    	    e.setTeam(t1.getId());  }
		    	else if(this.t2.getNumGiocatori() > this.t1.getNumGiocatori())
		    	{	this.t2.segnaGol();
		    	    e.setTeam(t2.getId());  }
		    	else
		    	{   int s = this.bestPlayer.getP().getTeam();
		    	    if(t1.getId() == s)
		    	    {	this.t1.segnaGol();
		    	        e.setTeam(t1.getId());  }
		    	    else
		    	    {	this.t2.segnaGol();
		    	        e.setTeam(t2.getId());  }
		    	}
		    	break;
		    case ESPULSIONE :
		    	double prob = Math.random();
		    	if(prob < 0.6)
		    	{   int s = this.bestPlayer.getP().getTeam();
	    	        if(t1.getId() == s)
	    	        {    this.t1.espulsione();
	    	             e.setTeam(t1.getId());  }
	    	        else
	    	        {     this.t2.espulsione();
	    	              e.setTeam(t2.getId());  }
		    	}
		    	else
		    	{  int s = this.bestPlayer.getP().getTeam();
    	           if(t1.getId() != s)
    	           {   this.t1.espulsione();
    	               e.setTeam(t1.getId());  }
   	               else
   	               {   this.t2.espulsione();
   	                   e.setTeam(t2.getId());  }
		    	}
		    	break;
		    case INFORTUNIO:
		    	double prob2 = Math.random();
		    	if(prob2 < 0.50)
		    	    this.aggiungiEventi(2);
		    	else 
		    		this.aggiungiEventi(3);
		    	break;
		}
		
		
	}

	public int getGol1() {
		return this.t1.getGol();
	}
	
	public int getGol2() {
		return this.t2.getGol();
	}
	
	public int getEx1() {
		return this.t1.getEspulsi();
	}
	
	public int getEx2() {
		return this.t2.getEspulsi();
	}

}
