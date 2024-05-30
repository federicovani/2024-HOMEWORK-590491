package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends Personaggio{
	
	private final static String CIBO_PREFERITO_DEFAULT = "croccantini";
	private String ciboPreferito;
	private Attrezzo attrezzoDaLasciare;
	
	public Cane(String nome, String presentazione, String ciboPreferito, Attrezzo attrezzoDaLasciare) {
		super(nome, presentazione);
		this.ciboPreferito = ciboPreferito;
		this.attrezzoDaLasciare = attrezzoDaLasciare;
	}
	
	public Cane(String nome, String presentazione, Attrezzo attrezzoDaLasciare) {
		this(nome, presentazione, CIBO_PREFERITO_DEFAULT, attrezzoDaLasciare);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		String msg = "Il cane si è innervosito e ti ha morso, facendoti perdere un CFU, te ne rimangono " + partita.getGiocatore().getCfu();
		return msg;
	}
	
	@Override
	public String saluta() {
		String msg = "bau!";
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		if(attrezzo.getNome() == this.ciboPreferito) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoDaLasciare);
			msg = "Il cane accetta volentieri questo cibo e in cambio lascia a terra un attrezzo: " + attrezzoDaLasciare.getNome();
		}
		else {
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
			msg = "Il cane non ha gradito questo alimento e ti ha morso, facendoti perdere un CFU. CFU rimasti: " + partita.getGiocatore().getCfu();
		}
		return msg;
	}

}
