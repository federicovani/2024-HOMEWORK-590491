package it.uniroma3.diadia.personaggi;

import java.util.TreeSet;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparatoreStanzePerAttrezzi;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends Personaggio{
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		Stanza stanza;
		TreeSet<Stanza> stanzeAdiacenti = new TreeSet<Stanza>(new ComparatoreStanzePerAttrezzi());
		stanzeAdiacenti.addAll(partita.getLabirinto().getStanzaCorrente().getMapStanzeAdiacenti().values());
		if(!super.haSalutato()) {
			stanza = stanzeAdiacenti.pollFirst();
			partita.getLabirinto().setStanzaCorrente(stanza);
			msg = "La strega si è offesa perchè non l'hai salutata, quindi ti ha trasportato nella stanza adiacente con meno attrezzi: " + stanza.getNome();
		} else {
			stanza = stanzeAdiacenti.pollLast();
			partita.getLabirinto().setStanzaCorrente(stanza);
			msg = "La strega è felice perchè l'hai salutata, quindi ti ha trasportato nella stanza adiacente con più attrezzi: " + stanza.getNome();
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg = "HAHAHAHAHA";
		return msg;
	}
}
