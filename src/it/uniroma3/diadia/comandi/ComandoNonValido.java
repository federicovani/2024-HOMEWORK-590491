package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{
	
	static final private String NOME = "nonValido";
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Comando non valido\n"+"Per conoscere le istruzioni usa il comando 'aiuto'.");
	}
	
	@Override
	public String getNome() {
		return ComandoNonValido.NOME;
	}
}
