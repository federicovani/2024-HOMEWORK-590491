package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.fixture.Fixture;

public class ComandoPrendiTest {

	List<String> comandiDaLeggere;
	Partita p;
	Comando comando;
	IO io;
	
	@Before
	public void setUp() {
		comandiDaLeggere = new ArrayList<String>();
		p = new Partita();
		io = new IOConsole();
		comando = new ComandoPrendi();
		comando.setIO(io);
		comando.setParametro("osso");
		p.getLabirinto().getStanzaCorrente().addAttrezzo(new Attrezzo("osso", 1));
	}

	@Test
	public void testEseguiAttrezzoValido() {
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("osso")); //Verifico che l'attrezzo non sia già presente
		comando.esegui(p); //Aggiungo l'attrezzo
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("osso")); //Verifico che ora sia presente nella stanza.
	}
	
	@Test
	public void testEseguiAttrezzoValidoFixture() {
		comandiDaLeggere.add("prendi martello");
		comandiDaLeggere.add("fine");
		
		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaHard(comandiDaLeggere);
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Attrezzo preso.", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
	}
	
	@Test
	public void testEseguiAttrezzoNonPresenteFixture() {
		comandiDaLeggere.add("prendi osso");
		comandiDaLeggere.add("fine");
		
		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaHard(comandiDaLeggere);
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Attrezzo inesistente", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
	}

}
