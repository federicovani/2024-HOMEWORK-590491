package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {
	
	private Giocatore g1;
	
	@Before
	public void setUp() {
		g1 = new Giocatore();
	}
	
	@Test
	public void testGetCfu() {
		assertEquals(Giocatore.getCfuIniziali(), new Giocatore().getCfu());
	}
	
	@Test
	public void testSetCfu() {
		g1.setCfu(30);
		assertEquals(30, g1.getCfu());
	}

}
