package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */

public class ComandoAiuto extends AbstractComando{
	
	static final private String NOME = "aiuto";
	
	@Override
	public void esegui(Partita partita) {
	// TODO
	}
	
	@Override
	public String getNome() {
		return ComandoAiuto.NOME;
	}
	
	
}
