package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	Stanza s1;
	Stanza s2;
	
	@Before
	public void setUp() {
		s1 = new StanzaBloccata("s1", Direzione.NORD);
		s2 = new Stanza("s2");
		s1.impostaStanzaAdiacente(Direzione.NORD, s2);
	}

	@Test
	public void testGetStanzaAdiacenteSenzaAttrezzo() {
		assertEquals(s1, s1.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void testGetStanzaAdiacenteConAttrezzo() {
		s1.addAttrezzo(new Attrezzo("passepartout", 1));
		assertEquals(s2, s1.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void testGetDescrizione() {
		String messaggio = "\n\n Questa stanza è bloccata. \n"
    			+ " Per andare a NORD è necessario posare l'attrezzo: passepartout\n";
		assertEquals(s1.toString() + messaggio, s1.getDescrizione());
	}

}
