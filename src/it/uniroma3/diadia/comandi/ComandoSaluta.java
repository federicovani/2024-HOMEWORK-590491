package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{
	
	static final private String NOME = "saluta";

	 @Override
	    public void esegui(Partita partita) {
	    	if(partita.getLabirinto().getStanzaCorrente().hasPersonaggio()) {
	    		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getPersonaggio().saluta());
	    		return;
	    	} else {
	    		io.mostraMessaggio("Non c'è nessuno in questa stanza.");
	    		return;
	    	}
	    }
	    
	    @Override
	    public String getNome() {
	        return ComandoSaluta.NOME;
	    }

}
