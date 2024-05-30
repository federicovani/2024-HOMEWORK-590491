package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */

public class ComandoAiuto extends AbstractComando{
	
	static final private String NOME = "aiuto";
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "guarda", "aiuto", "fine"};
	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i < elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]);
		io.mostraMessaggio("");
	}
	
	@Override
	public String getNome() {
		return ComandoAiuto.NOME;
	}
}
