package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	
	private Borsa borsa;
	private Borsa borsa2;
	private Borsa borsaOrdinata;
	private Attrezzo a1;
	
	@Before
	public void setUp() {
		borsa = new Borsa(100);
		borsaOrdinata = new Borsa(200);
		a1 = new Attrezzo("Spada", 5);
		borsa.addAttrezzo(a1);
		borsa2 = new Borsa();
		borsa2.addAttrezzo(a1);
		
		borsaOrdinata.addAttrezzo(new Attrezzo("piombo", 10));
		borsaOrdinata.addAttrezzo(new Attrezzo("ps", 5));
		borsaOrdinata.addAttrezzo(new Attrezzo("piuma", 1));
		borsaOrdinata.addAttrezzo(new Attrezzo("libro", 5));
		
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
		assertFalse(new Borsa().removeAttrezzo("Stivale"));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		assertTrue(borsa2.hasAttrezzo(a1.getNome()));
		assertTrue(borsa2.removeAttrezzo(a1.getNome()));
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		List<Attrezzo> ordinata = borsaOrdinata.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> attrezzi = ordinata.iterator();
		assertEquals(attrezzi.next(), new Attrezzo("piuma", 1));
		assertEquals(attrezzi.next(), new Attrezzo("libro", 5));
		assertEquals(attrezzi.next(), new Attrezzo("ps", 5));
		assertEquals(attrezzi.next(), new Attrezzo("piombo", 10));
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> ordinata = borsaOrdinata.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> attrezzi = ordinata.iterator();
		assertEquals(attrezzi.next(), new Attrezzo("libro", 5));
		assertEquals(attrezzi.next(), new Attrezzo("piombo", 10));
		assertEquals(attrezzi.next(), new Attrezzo("piuma", 1));
		assertEquals(attrezzi.next(), new Attrezzo("ps", 5));
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> raggruppata = borsaOrdinata.getContenutoRaggruppatoPerPeso();
		Set<Attrezzo> attrezzi = raggruppata.get(1);
		Iterator<Attrezzo> it = attrezzi.iterator();
		assertEquals(it.next(), new Attrezzo("piuma", 1));
		
		attrezzi = raggruppata.get(5);
		it = attrezzi.iterator();
		assertEquals(it.next(), new Attrezzo("libro", 5));
		assertEquals(it.next(), new Attrezzo("ps", 5));
		
		attrezzi = raggruppata.get(10);
		it = attrezzi.iterator();
		assertEquals(it.next(), new Attrezzo("piombo", 10));
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> ordinata = borsaOrdinata.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> attrezzi = ordinata.iterator();
		assertEquals(attrezzi.next(), new Attrezzo("piuma", 1));
		assertEquals(attrezzi.next(), new Attrezzo("libro", 5));
		assertEquals(attrezzi.next(), new Attrezzo("ps", 5));
		assertEquals(attrezzi.next(), new Attrezzo("piombo", 10));
	}

}
