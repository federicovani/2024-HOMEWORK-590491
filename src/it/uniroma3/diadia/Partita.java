package it.uniroma3.diadia;

import it.uniroma3.diadia.Partita;
import it.uniroma3.ambienti.Stanza;
import it.uniroma3.ambienti.Labirinto;
import it.uniroma3.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private boolean finita;
	
	public Labirinto labirinto;
	public Giocatore giocatore;
	
	public Partita(){
		labirinto = new Labirinto();
		giocatore = new Giocatore();
		this.finita = false;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return labirinto.getStanzaCorrente() == labirinto.getUscita();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || giocatore.getCfu() == 0;
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
}
