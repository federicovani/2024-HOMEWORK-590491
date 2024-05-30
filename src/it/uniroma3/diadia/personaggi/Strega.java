package it.uniroma3.diadia.personaggi;

import java.util.TreeSet;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparatoreStanzePerAttrezzi;
import it.uniroma3.diadia.ambienti.Stanza;

public class Strega extends Personaggio{
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		TreeSet<Stanza> stanzeAdiacenti = new TreeSet<Stanza>(new ComparatoreStanzePerAttrezzi());
		stanzeAdiacenti.addAll(partita.getLabirinto().getStanzaCorrente().getMapStanzeAdiacenti().values());
		if(!super.haSalutato()) {
			partita.getLabirinto().setStanzaCorrente(stanzeAdiacenti.pollFirst());
			msg = "La strega si è offesa perchè non l'hai salutata. \n" + "Sei stato trasportato nella stanza adiacente con meno attrezzi.";
		} else {
			partita.getLabirinto().setStanzaCorrente(stanzeAdiacenti.pollLast());
			msg = "La strega è felice perchè l'hai salutata. \n" + "Sei stato trasportato nella stanza adiacente con più attrezzi.";
		}
		return msg;
	}
}
