package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Personaggio;

public class ComandoRegala extends AbstractComando{

	private static final String MESSAGGIO = "Cosa vorresti regalare?";
	private String messaggio;
	
	@Override
	public void esegui(Partita partita) {
		
		Personaggio personaggio;
		Attrezzo attrezzo;
		personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (personaggio==null) {
			io.mostraMessaggio("Non c'è nessun personaggio in questa stanza.");
			return;
		} 
		if(super.getParametro() == null) {
			io.mostraMessaggio(MESSAGGIO);
			return;
		}
		if(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(super.getParametro())) {
			attrezzo = partita.getLabirinto().getStanzaCorrente().getAttrezzo(super.getParametro());
			partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzo);
			this.messaggio = personaggio.riceviRegalo(attrezzo, partita);
			io.mostraMessaggio(messaggio);
		}
		else io.mostraMessaggio("Questo attrezzo non è presente nella stanza");
	}
}
