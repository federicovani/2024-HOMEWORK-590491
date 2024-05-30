package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Direzione;

public class StanzaBloccata extends Stanza{
	
	final static private String ATTREZZO_PER_SBLOCCARE = "passepartout";
	private String attrezzoPerSbloccare;
	private Direzione direzioneBloccata;
	
	public StanzaBloccata(String nome, Direzione direzioneBloccata) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoPerSbloccare = ATTREZZO_PER_SBLOCCARE;
	}
	
	public StanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoPerSbloccare) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoPerSbloccare = attrezzoPerSbloccare;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione dir) {
		if(dir==direzioneBloccata)
			if(!super.hasAttrezzo(this.attrezzoPerSbloccare)) return this;
		return super.getStanzaAdiacente(dir);
	}
	
	@Override
	public String getDescrizione() {
		String messaggio = "\n\n Questa stanza è bloccata. \n"
    			+ " Per andare a " + direzioneBloccata + " è necessario posare l'attrezzo: " + attrezzoPerSbloccare + "\n";
    	return super.toString() + messaggio;
	}

}
