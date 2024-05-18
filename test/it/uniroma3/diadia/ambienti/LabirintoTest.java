package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class LabirintoTest {
	
	private Labirinto l1;
	private Stanza s1;
	
	@Before
	public void setUp() {
		l1 = new Labirinto();
		s1 = new Stanza("s1");
	}
	
	@Test
	public void testSetStanzaCorrenteNull() {
		l1.setStanzaCorrente(s1);
		l1.setStanzaCorrente(null);
		assertEquals(s1, l1.getStanzaCorrente());
	}
	
	@Test
	public void testSetStanzaCorrente() {
		l1.setStanzaCorrente(s1);
		assertEquals(s1, l1.getStanzaCorrente());
	}
	
	@Test
	public void testSetStanzaCorrenteUguale() {
		l1.setStanzaCorrente(s1);
		l1.setStanzaCorrente(s1);
		assertEquals(s1, l1.getStanzaCorrente());
	}

}
