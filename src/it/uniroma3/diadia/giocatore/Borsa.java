package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null || this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return attrezzi.get(nomeAttrezzo);
	}
	
	public int getPeso() {
		int peso = 0;
		for (String a : this.attrezzi.keySet())
			peso += attrezzi.get(a).getPeso();
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public boolean removeAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoRimosso = attrezzi.remove(nomeAttrezzo);
		if(attrezzoRimosso == null) return false;
		return true;
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> ordinata = new ArrayList<>(attrezzi.values());
		Comparator<Attrezzo> cmp = new ComparatoreAttrezziPerPeso();
		Collections.sort(ordinata, cmp);
		return ordinata;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> ordinata = new TreeSet<Attrezzo>(attrezzi.values());
		return ordinata;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Set<Attrezzo> attrezziOrdinati;
		Map<Integer, Set<Attrezzo>> raggruppata = new HashMap<>();
		for(Attrezzo a : this.attrezzi.values()) {
			if(raggruppata.containsKey(a.getPeso()))
				attrezziOrdinati = raggruppata.get(a.getPeso());
			else
				attrezziOrdinati = new HashSet<>();
			attrezziOrdinati.add(a);
			raggruppata.put(a.getPeso(), attrezziOrdinati);
		}
		return raggruppata;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> ordinata = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerPeso());
		ordinata.addAll(this.attrezzi.values());
		return ordinata;
	}
	
	public String getDescrizione() {
        return this.toString();
    }
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (String a : this.attrezzi.keySet())
				s.append(a.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
}
