package it.uniroma3.comandi;

import it.uniroma3.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Controlla se nella stanza è presente l'attrezzo che si desidera prendere.
 * Se capienza e peso massimo della borsa ne permettono l'inserimento lo mette nella borsa
 * e lo rimuove dalla stanza, mandando un messaggio di conferma.
 * Altrimenti manda un messaggio di errore.
 */

public class ComandoPrendi implements Comando{
	
	static final private String NOME = "prendi";
	private String attrezzo;
	private IO io;
		
	@Override
	public void esegui(Partita partita) {
		
		if(attrezzo==null) {
			io.mostraMessaggio("Che attrezzo vuoi prendere?");
			return;
		}
		
		Attrezzo attrezzoCercato = null;
		attrezzoCercato = partita.getLabirinto().getStanzaCorrente().getAttrezzo(attrezzo);
		if(attrezzoCercato == null) {
			io.mostraMessaggio("Attrezzo inesistente");
			return;
		}
		
		if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzoCercato)) {
			partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzoCercato);
			io.mostraMessaggio("Attrezzo preso");
		} else io.mostraMessaggio("Non è stato possibile prendere l'attrezzo.");
		
	}
	
	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;
	}
	
	@Override
	public String getNome() {
		return ComandoPrendi.NOME;
	}
	
	@Override
	public String getParametro() {
		return attrezzo;
	}
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}

}
