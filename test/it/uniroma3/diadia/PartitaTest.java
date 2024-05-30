package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class PartitaTest {

	private Partita p;
	private Labirinto labirinto;
	
	@Before
	public void setUp() {
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Biblioteca", "Atrio", "sud")
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
