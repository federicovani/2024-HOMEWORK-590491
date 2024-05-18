package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/


public class Stanza {
	
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
    private Map<String, Attrezzo> attrezzi;
    private Map<String, Stanza> stanzeAdiacenti;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashMap<>();
    }
    
    @Override
    public int hashCode() {
    	return this.getNome().hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
    	Stanza that = (Stanza) o;
    	return this.getNome().equals(that.getNome());
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
        if(direzione != null && stanza != null && stanzeAdiacenti.size() < 4) stanzeAdiacenti.put(direzione, stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
		return stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
    	List<Attrezzo> attrezzi = new ArrayList<>(this.attrezzi.values());
        return attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (attrezzo == null || this.attrezzi.size() == NUMERO_MASSIMO_ATTREZZI)
        	return false;
        this.attrezzi.put(attrezzo.getNome(), attrezzo);
        return true;
    }
    

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return attrezzi.get(nomeAttrezzo);	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo.getNome(), attrezzo);
	}


	public List<String> getDirezioni() {
		List<String> direzioni = new ArrayList<>(this.stanzeAdiacenti.keySet());
		return direzioni;
    }
	
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}
	
	public Map<String, Stanza> getMapStanzeAdiacenti(){
		return stanzeAdiacenti;
	}
	
	static public int getMassimoAttrezzi() {
		return NUMERO_MASSIMO_ATTREZZI;
	}
	
   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (String direzione : stanzeAdiacenti.keySet())
    		if(direzione != null) risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (String attrezzo : this.attrezzi.keySet()) {
    		if(attrezzo != null) risultato.append(attrezzo.toString()+" ");
    	}
    	return risultato.toString();
    }

}