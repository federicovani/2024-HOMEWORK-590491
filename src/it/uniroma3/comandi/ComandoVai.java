package it.uniroma3.comandi;

import it.uniroma3.ambienti.Stanza;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */

public class ComandoVai implements Comando{

    private String direzione;
    static final private String NOME = "vai";
    private IO io;
    
    /**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
    @Override
    public void esegui(Partita partita) {
    	Stanza prossimaStanza = null;
    	
		if(direzione==null) {
			io.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
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
    public void setParametro(String parametro) {
    	this.direzione = parametro;
    }
    
    @Override
    public String getNome() {
        return ComandoVai.NOME;
    }
    
    @Override
    public String getParametro() {
        return this.direzione;
    }
    
    @Override
	public void setIO(IO io) {
		this.io = io;
	}

}