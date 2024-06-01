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

public class StregaTest {
	
	List<String> comandiDaLeggere;
	
	@Before
	public void setUp() {
		comandiDaLeggere = new ArrayList<>();
	}
	
	@Test
	public void testAgisciSalutata() {
		comandiDaLeggere.add("vai nord");
		comandiDaLeggere.add("saluta");
		comandiDaLeggere.add("interagisci");
		
		IOSimulator io = Fixture.creaSimulazionePartitaConStrega(comandiDaLeggere);
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("cucina", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Ciao, io sono Strega. Ciao", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("La strega è felice perchè l'hai salutata, quindi ti ha trasportato nella stanza adiacente con più attrezzi: camera", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Hai vinto!", io.nextMessaggio());
	}
	
	@Test
	public void testAgisciNonSalutata() {
		comandiDaLeggere.add("vai nord");
		comandiDaLeggere.add("interagisci");
		comandiDaLeggere.add("fine");
		
		IOSimulator io = Fixture.creaSimulazionePartitaConStrega(comandiDaLeggere);
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("cucina", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("La strega si è offesa perchè non l'hai salutata, quindi ti ha trasportato nella stanza adiacente con meno attrezzi: salotto", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
	}
	
	@Test
	public void testRegala() {
		comandiDaLeggere.add("vai nord");
		comandiDaLeggere.add("regala pietra");
		comandiDaLeggere.add("fine");
		
		IOSimulator io = Fixture.creaSimulazionePartitaConStrega(comandiDaLeggere);
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("cucina", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("HAHAHAHAHA", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
	}
	

}
