package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	
	static final private String NOME = "nonValido";
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Comando non valido\n"+"Per conoscere le istruzioni usa il comando 'aiuto'.");
	}
	
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	public String getNome() {
		return ComandoNonValido.NOME;
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
