package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtectedTest {

	private Stanza s1;
	private Stanza s2;
	private Attrezzo a1;
	
	@Before
	public void setUp() {
		s1 = new StanzaMagica("s1");
		s2 = new StanzaMagica("s2", 0);
		a1 = new Attrezzo("Spada", 3);
	}

	@Test
	public void testAddAttrezzoNull() {
		assertFalse(s1.addAttrezzo(null));
	}
	
	@Test
	public void testAddAttrezzoComportamentoNormale() {
		assertTrue(s1.addAttrezzo(a1));
	}
	
	@Test
	public void testAddAttrezzoComportamentoMagico() {
		assertTrue(s2.addAttrezzo(a1));
		assertTrue(s2.hasAttrezzo("adapS"));
		assertEquals(a1.getPeso()*2, s2.getAttrezzo("adapS").getPeso());
	}

}
