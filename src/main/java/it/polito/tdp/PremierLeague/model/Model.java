package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	Graph<Match, DefaultWeightedEdge> grafo;
	private Map<Integer,Match> idMap;
	private PremierLeagueDAO dao;
	List<Arco> migliori;
	List<Match> migliore;
	List<Match> prova;
	
	
	
	public Model() {
		dao= new PremierLeagueDAO();
		idMap= new LinkedHashMap<>();
		migliori= new LinkedList<>();
	}
	
	public void listaMigliore(Match m1, Match m2) {
		migliore= new LinkedList<>();
		prova= new LinkedList<>();
		Match finale= m2;
		prova.add(m1);
		
		
	}
	
	
	public List<Integer> getMesi() {
		List<Integer> mesi= new LinkedList<Integer>();
		for(int i=1; i<13;i++) {
			mesi.add(i);
		}
		return mesi;
	}
	
	public void creaGrafo(int mese, int minuti) {
		grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//aggiungo vertici
		Graphs.addAllVertices(grafo, dao.getVertici(mese));
		for(Match m: dao.getVertici(mese)) {
			if(!idMap.containsKey(m.getMatchID())) {
				idMap.put(m.matchID, m);
			}
		}
		
		for(Arco a: dao.getArchi(mese, minuti, idMap)) {
			if(!grafo.containsEdge(grafo.getEdge(a.getM1(), a.getM2())) && !grafo.containsEdge(grafo.getEdge(a.getM2(), a.getM1()))) {
				Graphs.addEdge(grafo, a.getM1(), a.getM2(), a.getCount());
				if(migliori.isEmpty())
					migliori.add(a);
				else if(migliori.get(0).getCount()==a.getCount())
					migliori.add(a);
				else if(migliori.get(0).getCount()<a.getCount()) {
					//migliori.clear();
					migliori.add(a);
				}
			}
		}
	}

	public int getNVertici() {
		// TODO Auto-generated method stub
		return grafo.vertexSet().size();
	}
	
	public int getArchi() {
		return grafo.edgeSet().size();
	}
	
	public String matchMigliori() {
		String s="";
		for(Arco a: this.migliori) {
			s+= s+ a.getM1()+"-"+a.getM2()+" "+ a.getCount()+"\n";
		}
		return s;
	}
}
