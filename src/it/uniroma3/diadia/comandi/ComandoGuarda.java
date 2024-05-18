package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{
	
	static final private String NOME = "guarda";
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		io.mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
	}
	
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	public String getNome() {
		return ComandoGuarda.NOME;
	}
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}

}
