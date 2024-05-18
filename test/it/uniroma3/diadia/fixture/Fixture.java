package it.uniroma3.diadia.fixture;

import java.util.List;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class Fixture {
	public static IOSimulator creaSimulazioneBilocaleSemplice(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("laboratorio")
				.addAdiacenza("atrio", "laboratorio", "nord")
				.addAdiacenza("laboratorio", "atrio", "sud")
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
		return io;
	}
	
	public static IOSimulator creaSimulazioneTrilocaleSempliceConAttrezzi(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio").addAttrezzo("sedia", 1)
				.addStanza("biblioteca")
				.addAdiacenza("atrio", "biblioteca", "sud")
				.addAdiacenza("biblioteca", "atrio", "nord")
				.addAttrezzo("libro antico", 5)
				.addStanzaVincente("campus")
				.addAdiacenza("biblioteca", "campus", "est")
				.addAdiacenza("campus","biblioteca" , "ovest")
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
		return io;
	}
	
	public static IOSimulator creaSimulazioneCompleta(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("biblioteca")
				.addStanza("corridoio")
				.addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1)
				.addStanzaBloccata("corridoio bloccato","nord","chiave")
				.addStanzaMagica("stanza magica", 1)
				.addStanzaBuia("stanza buia","lanterna")
				.addStanza("Aula 1")
				.addAdiacenza("atrio", "corridoio", "nord")
				.addAdiacenza("corridoio", "atrio", "sud")
				.addAdiacenza("corridoio", "corridoio bloccato", "nord")
				.addAdiacenza("corridoio bloccato", "corridoio", "sud")
				.addAdiacenza("corridoio bloccato", "Aula 1", "nord")
				.addAdiacenza("Aula 1", "corridoio bloccato", "sud")
				.addAdiacenza("Aula 1", "biblioteca","nord")
				.addAdiacenza("biblioteca", "Aula 1", "sud")
				.addAdiacenza("corridoio", "stanza magica", "est")
				.addAdiacenza("stanza magica", "corridoio", "ovest")
				.addAdiacenza("corridoio", "stanza buia", "ovest")
				.addAdiacenza("stanza buia", "corridoio", "est")
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
		return io;
	}	
}
