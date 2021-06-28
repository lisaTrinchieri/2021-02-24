package it.polito.tdp.PremierLeague.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	PremierLeagueDAO dao;
	private Graph<Player, DefaultWeightedEdge> grafo;
	private Map<Integer, Player> idMap;
	
	private Simulatore sim;
	
	
	public Model() {
		this.dao = new PremierLeagueDAO();
		this.idMap = new HashMap<Integer, Player>();
		this.dao.listAllPlayers(idMap);
		
		
	}
	
	public void creaGrafo(Match m) {
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
	   
		List<Player> vertici = this.dao.getVertici(m, idMap);
		Graphs.addAllVertices(this.grafo, vertici);
		
	/*	for(Player p1: vertici)
		{	for(Player p2: vertici)
	    	{  if(!(p1.playerID.equals(p2.playerID)) &&  (p1.getTeam()!=p2.getTeam()))
	        	{ if(!this.grafo.containsEdge(p1, p2)  && !this.grafo.containsEdge(p2, p1))
	    	      {  double diff = p1.getEfficienza()-p2.getEfficienza();
		             if(diff > 0)
		    	            Graphs.addEdge(this.grafo, p1, p2, diff);
		              else if(diff<0)
	    	               Graphs.addEdge(this.grafo, p2, p1, (-1)*(diff));
	    	      }
	    	   }
	        }
	     }  */
		
		
		List<Adiacenza> archi = this.dao.getArchi(m, idMap);
		for(Adiacenza a : archi)
		{   if(a.getPeso()!= 0)
		    {  if(a.getPeso()>0)
		    	  Graphs.addEdge(this.grafo, a.getP1(), a.getP2(), a.getPeso());
		       else
	    	      Graphs.addEdge(this.grafo, a.getP2(), a.getP1(), (-1)*(a.getPeso()));
	    
		    }  
		}  
		   
			
		System.out.println("GRAFO FATTO\n");
		System.out.println("# vertici : "+ this.grafo.vertexSet().size()+"\n");
		System.out.println("# achi : "+ this.grafo.edgeSet().size()+"\n");
		
	
	}
	
	public int getNarchi() {
		return this.grafo.edgeSet().size();
	}
	
	public int getnVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public List<Match> getTuttimatch() {
		List<Match> matches = this.dao.listAllMatches();
		Collections.sort(matches, new Comparator<Match>() {
			@Override
			public int compare(Match m1, Match m2)
			{   return m1.getMatchID()-m2.getMatchID(); }
			
		});
		
		return matches;
	}
	
	public GiocatoreMigliore getMigliore() {
		if(this.grafo == null)
			return null;
		
		Player best = null;
		Double maxDelta = (double) Integer.MIN_VALUE;
		
		for(Player p : this.grafo.vertexSet()) {
			double pesoUscente = 0.0;
			for(DefaultWeightedEdge e : this.grafo.outgoingEdgesOf(p))
				pesoUscente += this.grafo.getEdgeWeight(e);
			
			double pesoEntrante = 0.0;
			for(DefaultWeightedEdge e : this.grafo.incomingEdgesOf(p))
				pesoEntrante += this.grafo.getEdgeWeight(e);
		
		    double delta = pesoUscente-pesoEntrante;
		    if(delta>maxDelta) {
		    	best = p;
		    	maxDelta = delta;
		    }
		}
		
		return new GiocatoreMigliore(best, maxDelta);
		
		
	}

	public Graph<Player, DefaultWeightedEdge> getGrafo() {
		return this.grafo;
	}
	
	public void simula(int N) {
		this.sim = new Simulatore();
	    
		this.sim.init(N, grafo, this.getMigliore());
		this.sim.run();
		
	}
	
	public int getGol1() {
		return this.sim.getGol1();
	}
	
	public int getGol2() {
		return this.sim.getGol2();
	}
	public int getEx1() {
		return this.sim.getEx1();
	}
	public int getEx2() {
		return this.sim.getEx2();
	}
	
}
