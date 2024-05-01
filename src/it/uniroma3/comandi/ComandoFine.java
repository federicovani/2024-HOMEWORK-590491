package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{
	
	static final private String NOME = "fine";
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita();
	}
	
	
	@Override
	public void setParametro(String parametro) {
		
	}
	
	@Override
	public String getNome() {
		return ComandoFine.NOME;
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
