package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */

public class ComandoVai extends AbstractComando{

    static final private String NOME = "vai";
    
    /**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
    @Override
    public void esegui(Partita partita) {
    	Stanza prossimaStanza = null;
    	
		if(super.getParametro()==null) {
			io.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		Direzione direzione = Direzione.valueOf(super.getParametro().toUpperCase());
		prossimaStanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente");
			return;
		}
		
		partita.getLabirinto().setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
    }
    
    @Override
    public String getNome() {
        return ComandoVai.NOME;
    }

}