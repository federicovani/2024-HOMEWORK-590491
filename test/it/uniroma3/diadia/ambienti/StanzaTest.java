package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
	private Stanza s1;
	private Stanza s2;
	private Stanza s3;
	private Attrezzo a1;
	private int numAttrezziS2;
	
	@Before
	public void setUp() {
		this.s1 = new Stanza("S1");
		this.s2 = new Stanza("S2");
		this.s3 = new Stanza("S3");
		s3.impostaStanzaAdiacente(Direzione.SUD, s2);
		this.a1 = new Attrezzo("Spada", 10);
		s3.addAttrezzo(new Attrezzo("1", 1));
		s3.addAttrezzo(new Attrezzo("2", 1));
		s3.addAttrezzo(new Attrezzo("3", 1));
		s3.addAttrezzo(new Attrezzo("4", 1));
		s3.addAttrezzo(new Attrezzo("5", 1));
		s3.addAttrezzo(new Attrezzo("6", 1));
		s3.addAttrezzo(new Attrezzo("7", 1));
		s3.addAttrezzo(new Attrezzo("8", 1));
		s3.addAttrezzo(new Attrezzo("9", 1));
		s3.addAttrezzo(new Attrezzo("10", 1));
		s2.addAttrezzo(a1);
		numAttrezziS2 = s2.getNumeroAttrezzi();
		
		
	}
	@Test
	public void testImpostaStanzaAdiacenteNuovaStanza() {
		assertNull(s1.getStanzaAdiacente(Direzione.SUD));
		s1.impostaStanzaAdiacente(Direzione.SUD, s2);
		assertEquals(s2, s1.getStanzaAdiacente(Direzione.SUD));
	}
	
	@Test
	public void testImpostaStanzaAdiacenteSostituisciStanza() {
		assertEquals(s2, s3.getStanzaAdiacente(Direzione.SUD));
		s3.impostaStanzaAdiacente(Direzione.SUD, s1);
		assertEquals(s1, s3.getStanzaAdiacente(Direzione.SUD));
	}
	
	public void testImpostaStanzaAdiacenteStanzaNulla() {
		s1.impostaStanzaAdiacente(Direzione.SUD, null);
		assertNull(s1.getStanzaAdiacente(Direzione.SUD));
	}
	
	@Test
	public void testAddAttrezzoNull() {
		assertFalse(s1.addAttrezzo(null));
	}
	
	@Test
	public void testAddAttrezzo() {
		assertTrue(s1.addAttrezzo(new Attrezzo("Spada", 10)));
	}
	
	@Test
	public void testAddAttrezzoConStanzaPiena() {
		/* s3 è stata riempita fino al massimo numero di attrezzi nel SetUp()*/
		assertFalse(s3.addAttrezzo(a1));
	}
	
	@Test
	public void testRemoveAttrezzoStanzaVuota() {
		assertFalse(new Stanza("test").removeAttrezzo(a1));
	}
	
	@Test
	public void testRemoveAttrezzoStanzaSenzaAttrezzo() {
		assertFalse(s2.removeAttrezzo(new Attrezzo("Arco", 5)));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		assertTrue(s2.removeAttrezzo(a1));
		assertEquals(s2.getNumeroAttrezzi(), numAttrezziS2-1);
		
	}
	
	@Test
	public void testHasAttrezzo() {
		s1.addAttrezzo(new Attrezzo("Clava", 10));
		assertTrue(s1.hasAttrezzo("Clava"));
	}
	
}

