package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Personaggio;

public class LabirintoBuilder {
	
	private Labirinto labirinto;
	private Stanza ultimaStanza;
	private Map<String, Stanza> stanze;
	
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.stanze = new HashMap<>();
	}
	
	public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
		Stanza stanzaIniziale = new Stanza(nomeStanzaIniziale);
		this.labirinto.setStanzaIniziale(stanzaIniziale);
		stanze.put(nomeStanzaIniziale, stanzaIniziale);
		this.setUltimaStanza(stanzaIniziale);
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		this.labirinto.setStanzaVincente(stanzaVincente);
		stanze.put(nomeStanzaVincente, stanzaVincente);
		this.setUltimaStanza(stanzaVincente);
		return this;
	}
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza stanza = new Stanza(nomeStanza);
		stanze.put(nomeStanza, stanza);
		this.setUltimaStanza(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nomeStanza, int sogliaMagica) {
		Stanza stanzaMagica = new StanzaMagica(nomeStanza, sogliaMagica);
		stanze.put(nomeStanza, stanzaMagica);
		this.setUltimaStanza(stanzaMagica);
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, Direzione direzione, String attrezzo) {
		Stanza stanzaBloccata = new StanzaBloccata(nomeStanza, direzione, attrezzo);
		stanze.put(nomeStanza, stanzaBloccata);
		this.setUltimaStanza(stanzaBloccata);
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzo) {
		Stanza stanzaBuia = new StanzaBuia(nomeStanza, attrezzo);
		stanze.put(nomeStanza, stanzaBuia);
		this.setUltimaStanza(stanzaBuia);
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo attrezzo = new Attrezzo(nome, peso);
		this.ultimaStanza.addAttrezzo(attrezzo);
		return this;
	}
	
	public LabirintoBuilder setPersonaggio(Personaggio personaggio) {
		this.ultimaStanza.setPersonaggio(personaggio);
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String s1, String s2, Direzione direzione) {
		Stanza stanza1 = getListaStanze().get(s1);
		Stanza stanza2 = getListaStanze().get(s2);
		stanza1.impostaStanzaAdiacente(direzione, stanza2);
		stanza2.impostaStanzaAdiacente(direzione.opposta(), stanza1);
		return this;
	}
	
	public void setUltimaStanza(Stanza stanza) {
		this.ultimaStanza = stanza;
	}
	
	public Map<String, Stanza> getListaStanze(){
		return stanze;
	}
	
	public List<Stanza> getListaStanze1(){
		return (List<Stanza>) stanze.values();
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
}
