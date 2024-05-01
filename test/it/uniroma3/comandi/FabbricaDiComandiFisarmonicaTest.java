package it.uniroma3.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

public class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandi factory;
	private IO io;
	@Before
	public void setUp() {
		io = new IOConsole();
		factory = new FabbricaDiComandiFisarmonica(io);
	}
	
	@Test
	public void testCostruisciComandoNonValido() {
		assertEquals("nonValido", factory.costruisciComando("-").getNome());
	}
	
	@Test
	public void testCostruisciComandoAiuto() {
		assertEquals("aiuto", factory.costruisciComando("aiuto").getNome());
	}
	
	@Test
	public void testCostruisciComandoGuarda() {
		assertEquals("guarda", factory.costruisciComando("guarda").getNome());
	}
	
	@Test
	public void testCostruisciComandoFine() {
		assertEquals("fine", factory.costruisciComando("fine").getNome());
	}
	
	@Test
	public void testCostruisciComandoVai() {
		assertEquals("vai", factory.costruisciComando("vai nord").getNome());
		assertEquals("nord", factory.costruisciComando("vai nord").getParametro());
	}
	
	@Test
	public void testCostruisciComandoPrendi() {
		assertEquals("prendi", factory.costruisciComando("prendi").getNome());
		assertEquals("osso", factory.costruisciComando("prendi osso").getParametro());
	}
	
	@Test
	public void testCostruisciComandoPosa() {
		assertEquals("posa", factory.costruisciComando("posa").getNome());
		assertEquals("osso", factory.costruisciComando("posa osso").getParametro());
	}


	

}
