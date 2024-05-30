package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{
	private String nome;
	private String parametro;
	protected IO io;
	
	public abstract void esegui(Partita partita);
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
	public void setIO(IO io) {
		this.io = io;
	}
}
