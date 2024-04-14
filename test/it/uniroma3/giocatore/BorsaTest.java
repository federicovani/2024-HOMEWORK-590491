package it.uniroma3.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.attrezzi.Attrezzo;

public class BorsaTest {
	
	private Borsa borsa;
	private Borsa borsa2;
	private Attrezzo a1;
	
	@Before
	public void setUp() {
		borsa = new Borsa(100);
		a1 = new Attrezzo("Spada", 5);
		for(int i = 0; i < 10; i++)
			borsa.addAttrezzo(a1);
		borsa2 = new Borsa();
		borsa2.addAttrezzo(a1);
	}

	@Test
	public void testAddAttrezzo() {
		assertTrue(new Borsa().addAttrezzo(new Attrezzo("Spada", 5)));
	}
	
	@Test
	public void testAddAttrezzoPesoMassimo() {
		assertFalse(new Borsa(10).addAttrezzo(new Attrezzo("Clava", 11)));
	}
	
	@Test
	public void testAddAttrezzoBorsaPiena() {
		/*borsa è stata riempita fino al massimo numero di attrezzi nel setUp()*/
		assertFalse(borsa.addAttrezzo(a1));
	}
	
	@Test
	public void testGetAttrezzoBorsaVuota() {
		assertNull(new Borsa().getAttrezzo("Clava"));
	}
	
	@Test
	public void testGetAttrezzo() {
		/*L'attrezzo "Spada" è stato inserito in borsa nel setUp()*/
		assertEquals(a1, borsa.getAttrezzo("Spada"));
	}
	
	@Test
	public void testHasAttrezzo() {
		/*L'attrezzo "Spada" è stato inserito in borsa nel setUp()*/
		assertTrue(borsa.hasAttrezzo("Spada"));
	}
	
	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		assertNull(new Borsa().removeAttrezzo("Stivale"));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		assertTrue(borsa2.hasAttrezzo(a1.getNome()));
		assertEquals(a1, borsa2.removeAttrezzo(a1.getNome()));
	}

}
