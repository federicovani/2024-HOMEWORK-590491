package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.giocatore.Giocatore;

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		borsa = new Borsa();
	}
	
	public boolean isVivo() {
		return cfu > 0;
	}

	public int getCfu() {
		return this.cfu;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	static public int getCfuIniziali() {
		return CFU_INIZIALI;
	}
}
