package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoFine;
import it.uniroma3.diadia.fixture.Fixture;

public class MagoTest {

	List<String> comandiDaLeggere;
	Partita p;
	
	@Before
	public void setUp() {
		comandiDaLeggere = new ArrayList<>();
	}
	
	@Test
	public void testInterazioneConDono() {
		comandiDaLeggere.add("vai ovest");
		comandiDaLeggere.add("saluta");
		comandiDaLeggere.add("interagisci");
		comandiDaLeggere.add("guarda");
		comandiDaLeggere.add("fine");
		
		IOSimulator io = Fixture.creaSimulazionePartitaConPersonaggi(comandiDaLeggere);
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Studio", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Ciao, io sono Merlino. Sono in grado di leggere i cuori e capire i veri desideri delle persone.", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Sei un vero simpaticone, con una mia magica azione, troverai un nuovo oggetto per il tuo borsone!", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Studio\nUscite:  EST\nAttrezzi nella stanza: libro bacchetta \nPersonaggio nella stanza: Merlino", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Borsa vuota", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
	}
	
	@Test
	public void testInterazioneSenzaDono() {
		comandiDaLeggere.add("vai ovest");
		comandiDaLeggere.add("saluta");
		comandiDaLeggere.add("interagisci");
		comandiDaLeggere.add("interagisci");
		comandiDaLeggere.add("fine");
		
		IOSimulator io = Fixture.creaSimulazionePartitaConPersonaggi(comandiDaLeggere);
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Studio", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Ciao, io sono Merlino. Sono in grado di leggere i cuori e capire i veri desideri delle persone.", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Sei un vero simpaticone, con una mia magica azione, troverai un nuovo oggetto per il tuo borsone!", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Mi spiace, ma non ho piu' nulla...", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
	}
	
	@Test
	public void testRegalaSimulazione() {
		comandiDaLeggere.add("vai ovest");
		comandiDaLeggere.add("regala libro");
		comandiDaLeggere.add("prendi libro");
		comandiDaLeggere.add("guarda");
		comandiDaLeggere.add("fine");
		
		IOSimulator io = Fixture.creaSimulazionePartitaConPersonaggi(comandiDaLeggere);
		
		io.debug();
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Studio", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Ti ringrazio per questo dono, ma non ne ho bisogno! Per ringraziarti ne ho dimezzato il peso.", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Attrezzo preso.", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Studio\nUscite:  EST\nAttrezzi nella stanza: \nPersonaggio nella stanza: Merlino", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Contenuto borsa (2kg/10kg): libro ", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
	}

}
