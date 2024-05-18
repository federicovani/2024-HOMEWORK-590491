package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Controlla se nella borsa è presente l'attrezzo che si desidera posare e lo
 * posa nella stanza rimuovendolo dalla borsa e mandando un messaggio di conferma.
 * Altrimenti manda un messaggio di errore.
 */

public class ComandoPosa implements Comando{
	
	static final private String NOME = "posa";
	private String attrezzo;
	private IO io;
		
	@Override
	public void esegui(Partita partita) {
		if(attrezzo==null) {
			io.mostraMessaggio("Che attrezzo vuoi posare?");
			return;
		}
		Attrezzo attrezzoCercato = null;
		attrezzoCercato = partita.getGiocatore().getBorsa().getAttrezzo(attrezzo);
		if(attrezzoCercato == null) {
			io.mostraMessaggio("Non possiedi questo attrezzo.");
			return;
		}
		if(partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoCercato)) {
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
			io.mostraMessaggio("Attrezzo posato.");
		} else io.mostraMessaggio("Non è stato possibile posare l'attrezzo.");
	}
	
	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;
	}
	
	@Override
	public String getNome() {
		return ComandoPosa.NOME;
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
