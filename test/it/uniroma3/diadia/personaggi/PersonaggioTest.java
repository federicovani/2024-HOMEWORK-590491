package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.fixture.Fixture;

public class PersonaggioTest {
	
	Personaggio p;
	Partita partita;
	
	@Before
	public void setUp() {
		p = new FakePersonaggio("Cartomante", "Leggo le carte");
		partita = new Partita();
	}

	@Test
	public void testGetNome() {
		assertEquals("Cartomante", p.getNome());
	}
	
	@Test
	public void testToString() {
		assertEquals("Cartomante", p.toString());
	}
	
	@Test
	public void testAgisci() {
		assertEquals("done", p.agisci(partita));
	}
	
	@Test
	public void testSaluta() {
		assertEquals("Ciao, io sono Cartomante. Leggo le carte", p.saluta());
	}
	
	@Test
	public void testGiaSalutato() {
		p.saluta();
		assertEquals("Ciao, io sono Cartomante. Ci siamo gia' presentati!", p.saluta());
	}
}
