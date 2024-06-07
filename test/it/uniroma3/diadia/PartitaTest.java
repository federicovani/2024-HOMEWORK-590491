package it.uniroma3.diadia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

public class PartitaTest {

	private Partita p;
	private Labirinto labirinto;
	
	@Before
	public void setUp() {
		labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.getLabirinto();
		p = new Partita(labirinto);
	}
	
	@Test
	public void testSetFinita() {
		assertFalse(p.isFinita());
		p.setFinita();
		assertTrue(p.isFinita());
	}
	
	@Test
	public void testVinta() {
		assertFalse(p.vinta());
	}
	
	@Test
	public void testVintaUscitaRaggiunta() {
		p.getLabirinto().setStanzaCorrente(p.getLabirinto().getStanzaVincente());
		/*In questa partita il giocatore ha raggiunto l'uscita*/
		assertTrue(p.vinta());
	}
	
	@Test
	public void testIsFinita() {
		/*Questa partita è appena cominciata*/
		assertFalse(p.isFinita());
	}
	
	@Test
	public void testIsFinita0Cfu() {
		p.getGiocatore().setCfu(0);
		/*In questa partita il giocatore non possiede più alcun CFU*/
		assertTrue(p.isFinita());
	}

}
