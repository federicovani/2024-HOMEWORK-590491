package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.exceptions.FormatoFileNonValidoException;

public class CaricatoreLabirintoTest {
	
	Labirinto l1;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		l1 = Labirinto.newBuilder("labirinto.txt").getLabirinto();
	}

	@Test
	public void testLabirintoEasy() {
		assertEquals(l1.getStanzaIniziale(), new Stanza("N10"));
		assertEquals(l1.getStanzaVincente(), new Stanza("N11"));
		assertEquals(l1.getStanzaIniziale().getStanzaAdiacente(Direzione.SUD), new Stanza("biblioteca"));
		assertEquals(l1.getStanzaVincente().getStanzaAdiacente(Direzione.NORD), new Stanza("biblioteca"));
		assertTrue(l1.getStanzaIniziale().hasAttrezzo("pinza"));
		assertTrue(l1.getStanzaIniziale().getStanzaAdiacente(Direzione.SUD).hasAttrezzo("martello"));
	}

}
