package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Controlla se nella stanza è presente l'attrezzo che si desidera prendere.
 * Se capienza e peso massimo della borsa ne permettono l'inserimento lo mette nella borsa
 * e lo rimuove dalla stanza, mandando un messaggio di conferma.
 * Altrimenti manda un messaggio di errore.
 */

public class ComandoPrendi extends AbstractComando{
	
	static final private String NOME = "prendi";
		
	@Override
	public void esegui(Partita partita) {
		
		if(super.getParametro()==null) {
			io.mostraMessaggio("Che attrezzo vuoi prendere?");
			return;
		}
		
		Attrezzo attrezzoCercato = null;
		attrezzoCercato = partita.getLabirinto().getStanzaCorrente().getAttrezzo(super.getParametro());
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
	public String getNome() {
		return ComandoPrendi.NOME;
	}

}
