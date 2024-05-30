package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{
	
	static final private String NOME = "fine";
	static final String MESSAGGIO_FINE = "Grazie di aver giocato!";
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(MESSAGGIO_FINE);
		partita.setFinita();
	}
	
	@Override
	public String getNome() {
		return ComandoFine.NOME;
	}
}
