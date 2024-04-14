package it.uniroma3.giocatore;

import it.uniroma3.giocatore.Giocatore;

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private int cfu;
	
	public Borsa borsa;
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		borsa = new Borsa();
	}

	public int getCfu() {
		return this.cfu;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	static public int getCfuIniziali() {
		return CFU_INIZIALI;
	}
}
