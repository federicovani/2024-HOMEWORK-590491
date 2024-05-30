package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	Stanza s1;
	Stanza s2;
	
	@Before
	public void setUp() {
		s1 = new StanzaBloccata("s1", "nord");
		s2 = new Stanza("s2");
		s1.impostaStanzaAdiacente("nord", s2);
		s2.impostaStanzaAdiacente("sud", s1);
	}

	@Test
	public void testGetStanzaAdiacenteSenzaAttrezzo() {
		assertEquals(s1, s1.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacenteConAttrezzo() {
		s1.addAttrezzo(new Attrezzo("passepartout", 1));
		assertEquals(s2, s1.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetDescrizione() {
		String messaggio = "\n\n Questa stanza è bloccata. \n"
    			+ " Per andare a nord è necessario posare l'attrezzo: passepartout\n";
		assertEquals(s1.toString() + messaggio, s1.getDescrizione());
	}

}
