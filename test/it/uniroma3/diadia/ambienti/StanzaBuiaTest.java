package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	
	Stanza s1;
	
	@Before
	public void setUp() {
		s1 = new StanzaBuia("s1");
	}

	@Test
	public void testGetDescrizioneSenzaAttrezzoLuminoso() {
		assertEquals("Qui c'è un buio pesto", s1.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneConAttrezzoLuminoso() {
		s1.addAttrezzo(new Attrezzo("lanterna", 1));
		assertEquals(s1.toString(), s1.getDescrizione());
	}
	

}
