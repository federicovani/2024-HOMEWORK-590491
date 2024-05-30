package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public class Cane extends Personaggio{
	
	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg = "Il cane si è innervosito e ti ha morso, facendoti perdere un CFU.";
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);;
		return msg;
	}

}
