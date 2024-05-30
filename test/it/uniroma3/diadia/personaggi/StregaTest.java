package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class StregaTest {
	
	Labirinto labirinto;
	Partita p;
	Personaggio strega;
	Stanza biblioteca;
	
	@Before
	public void setUp() {
		strega = new Strega("Anna", "So troia");
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio").addAttrezzo("Osso", 1).addAttrezzo("Spada", 1)
				.addStanza("biblioteca").setPersonaggio(strega)
				.addAdiacenza("atrio", "biblioteca", "sud")
				.addAdiacenza("biblioteca", "atrio", "nord")
				.addStanzaVincente("campus").addAttrezzo("Pistola", 3)
				.addAdiacenza("biblioteca", "campus", "est")
				.addAdiacenza("campus","biblioteca" , "ovest")
				.getLabirinto();
		p = new Partita(labirinto);
		p.getLabirinto().setStanzaCorrente(new Stanza("biblioteca"));
	}
	
	@Test
	public void testAgisciNonSalutato() {
		assertEquals("La strega si è offesa perchè non l'hai salutata. \n" + "Sei stato trasportato nella stanza adiacente con meno attrezzi.", strega.agisci(p));
	}
	
	@Test
	public void testAgisciSalutato() {
		strega.saluta();
		assertEquals("La strega è felice perchè l'hai salutata. \n" + "Sei stato trasportato nella stanza adiacente con più attrezzi.", strega.agisci(p));
	}

}
