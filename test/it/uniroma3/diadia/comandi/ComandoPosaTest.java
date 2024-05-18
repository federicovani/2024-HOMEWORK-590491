package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPosa;

public class ComandoPosaTest {
	
	Partita p1;
	Attrezzo a1;
	Comando comando;
	IO io;
	
	@Before
	public void setUp(){
		p1 = new Partita();
		a1 = new Attrezzo("lancia", 1);
		io = new IOConsole();
		comando = new ComandoPosa();
		comando.setIO(io);
		comando.setParametro("lancia");
		p1.getGiocatore().getBorsa().addAttrezzo(a1);
	}
	
	@Test
	public void testEseguiAttrezzoValido() {
		assertFalse(p1.getLabirinto().getStanzaCorrente().hasAttrezzo(a1.getNome())); //Verifico che l'attrezzo non sia già presente
		comando.esegui(p1); //Aggiungo l'attrezzo
		assertTrue(p1.getLabirinto().getStanzaCorrente().hasAttrezzo(a1.getNome())); //Verifico che ora sia presente nella stanza.
	}

}
