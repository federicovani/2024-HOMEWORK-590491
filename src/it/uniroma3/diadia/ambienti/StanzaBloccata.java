package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	final static private String ATTREZZO_PER_SBLOCCARE = "passepartout";
	private String attrezzoPerSbloccare;
	private String direzioneBloccata;
	
	public StanzaBloccata(String nome, String direzioneBloccata) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoPerSbloccare = ATTREZZO_PER_SBLOCCARE;
	}
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoPerSbloccare) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoPerSbloccare = attrezzoPerSbloccare;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if(dir==direzioneBloccata)
			if(!super.hasAttrezzo(attrezzoPerSbloccare)) return this;
		return super.getStanzaAdiacente(dir);
	}
	
	@Override
	public String getDescrizione() {
		String messaggio = "\n Questa stanza è bloccata. \n"
    			+ "Per andare a " + direzioneBloccata + " è necessario posare un " + attrezzoPerSbloccare;
    	return super.toString() + messaggio;
	}

}
