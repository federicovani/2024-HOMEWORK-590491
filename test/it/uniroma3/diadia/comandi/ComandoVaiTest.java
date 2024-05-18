package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.fixture.Fixture;

public class ComandoVaiTest {
	
	List<String> comandiDaLeggere;
	
	@Before
	public void setUp() {
		comandiDaLeggere = new ArrayList<String>();
	}

	@Test
	public void testVaiMonolocale() {
		comandiDaLeggere.add("vai nord");
		IOSimulator io = Fixture.creaSimulazioneBilocaleSemplice(comandiDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(io.nextMessaggio(), "laboratorio");
		assertTrue(io.hasNextMessaggio());
		assertEquals(io.nextMessaggio(), "Hai vinto!");
	}
	
	@Test
	public void testVaiTrilocale() {
		comandiDaLeggere.add("vai ovest");
		IOSimulator io = Fixture.creaSimulazioneTrilocaleSempliceConAttrezzi(comandiDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(io.nextMessaggio(), "biblioteca");
		//assertTrue(io.hasNextMessaggio());
		//assertEquals(io.nextMessaggio(), "Hai vinto!");
	}

}
