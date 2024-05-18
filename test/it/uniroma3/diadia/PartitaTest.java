package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PartitaTest {

	private Partita p1, p2, p3;
	
	@Before
	public void setUp() {
		p1 = new Partita();
		p2 = new Partita();
		p3 = new Partita();
		p2.getLabirinto().setStanzaCorrente(p2.getLabirinto().getStanzaVincente());
		p3.getGiocatore().setCfu(0);
	}
	
	@Test
	public void testSetFinita() {
		assertFalse(p1.isFinita());
		p1.setFinita();
		assertTrue(p1.isFinita());
	}
	
	@Test
	public void testVinta() {
		assertFalse(p1.vinta());
	}
	
	@Test
	public void testVintaUscitaRaggiunta() {
		/*P2 è una partita in cui il giocatore ha raggiunto l'uscita*/
		assertTrue(p2.vinta());
	}
	
	@Test
	public void testIsFinita() {
		/*P1 è una partita appena cominciata*/
		assertFalse(p1.isFinita());
	}
	
	@Test
	public void testIsFinita0Cfu() {
		/*In p3 il giocatore non possiede più alcun CFU*/
		assertTrue(p3.isFinita());
	}

}
