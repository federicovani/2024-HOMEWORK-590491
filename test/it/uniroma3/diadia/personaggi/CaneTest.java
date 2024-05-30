package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.comandi.ComandoFine;
import it.uniroma3.diadia.fixture.Fixture;

public class CaneTest {
	
	List<String> comandiDaLeggere;
	
	@Before
	public void setUp() {
		comandiDaLeggere = new ArrayList<>();
	}
	
	@Test
	public void test() {
		comandiDaLeggere.add("vai nord");
		comandiDaLeggere.add("saluta");
		comandiDaLeggere.add("interagisci");
		comandiDaLeggere.add("fine");
		
		IOSimulator io = Fixture.creaSimulazionePartitaConPersonaggi(comandiDaLeggere);
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Bagno", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("bau!", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Il cane si è innervosito e ti ha morso, facendoti perdere un CFU, te ne rimangono 18", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
	}

}
