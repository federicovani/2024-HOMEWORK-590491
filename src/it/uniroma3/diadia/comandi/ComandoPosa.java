package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Controlla se nella borsa è presente l'attrezzo che si desidera posare e lo
 * posa nella stanza rimuovendolo dalla borsa e mandando un messaggio di conferma.
 * Altrimenti manda un messaggio di errore.
 */

public class ComandoPosa extends AbstractComando{
	
	static final private String NOME = "posa";
		
	@Override
	public void esegui(Partita partita) {
		if(super.getParametro()==null) {
			io.mostraMessaggio("Che attrezzo vuoi posare?");
			return;
		}
		Attrezzo attrezzoCercato = null;
		attrezzoCercato = partita.getGiocatore().getBorsa().getAttrezzo(super.getParametro());
		if(attrezzoCercato == null) {
			io.mostraMessaggio("Non possiedi questo attrezzo.");
			return;
		}
		if(partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoCercato)) {
			partita.getGiocatore().getBorsa().removeAttrezzo(super.getParametro());
			io.mostraMessaggio("Attrezzo posato.");
		} else io.mostraMessaggio("Non è stato possibile posare l'attrezzo.");
	}
	
	@Override
	public String getNome() {
		return ComandoPosa.NOME;
	}
}
