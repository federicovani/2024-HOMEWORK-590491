package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	final static private String ATTREZZO_LUMINOSO_DEFAULT = "lanterna";
	final static private String MESSAGGIO_BUIO = "Qui c'è un buio pesto";
	private String attrezzoLuminoso;
	
	public StanzaBuia(String nome) {
		super(nome);
		this.attrezzoLuminoso = ATTREZZO_LUMINOSO_DEFAULT;
	}
	
	public StanzaBuia(String nome, String attrezzoLuminoso) {
		super(nome);
		this.attrezzoLuminoso = attrezzoLuminoso;
	}
	
	@Override
	public String getDescrizione() {
		if(!super.hasAttrezzo(attrezzoLuminoso)) return MESSAGGIO_BUIO;
		return super.getDescrizione();
	}
}
