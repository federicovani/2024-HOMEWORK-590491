package it.uniroma3.diadia.fixture;

import java.util.List;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;


public class Fixture {
	public static IOSimulator creaSimulazionePartitaEGiocaEasy(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
		return io;
	}
	
	public static Labirinto creaLabirintoEasy() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.getLabirinto();
		return labirinto;
	}

	public static IOSimulator creaSimulazionePartitaEGiocaHard(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.addStanza("Bagno")
				.addAdiacenza("Bagno", "Atrio", Direzione.SUD)
				.addStanza("Studio")
				.addAdiacenza("Atrio", "Studio", Direzione.OVEST)
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
		return io;
	}
	
	public static Labirinto creaLabirintoHard() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.addStanza("Bagno")
				.addAdiacenza("Bagno", "Atrio", Direzione.SUD)
				.addStanza("Studio")
				.addAdiacenza("Atrio", "Studio", Direzione.OVEST)
				.getLabirinto();
		return labirinto;
	}	

	public static IOSimulator creaSimulazionePartitaEGiocaMonolocale(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto") 
				.addStanzaVincente("salotto") 
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, monolocale);
		gioco.gioca();
		return io;
	}
	
	
	public static IOSimulator creaSimulazionePartitaEGiocaBilocale(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto bilocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10) // dove? fa riferimento all’ultima stanza aggiunta
				.addAdiacenza("salotto", "camera", Direzione.NORD) // camera si trova a nord di salotto
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, bilocale);
		gioco.gioca();
		return io;
	}
	
	public static IOSimulator creaSimulazionePartitaEGiocaTrilocale(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto trilocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola",1) // dove? fa riferimento all’ultima stanza aggiunta
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "cucina", Direzione.NORD)
				.addAdiacenza("cucina", "camera", Direzione.EST)
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, trilocale);
		gioco.gioca();
		return io;
	}
	
	public static IOSimulator creaSimulazionePartitaConStrega(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto trilocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto").addAttrezzo("bastone", 1)
				.addStanza("cucina").setPersonaggio(new Strega("Strega", "Ciao")).addAttrezzo("pietra", 5)
				.addStanzaVincente("camera").addAttrezzo("osso", 1).addAttrezzo("pistola", 1)
				.addAdiacenza("salotto", "cucina", Direzione.NORD)
				.addAdiacenza("cucina", "camera", Direzione.EST)
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, trilocale);
		gioco.gioca();
		return io;
	}
	
	public static IOSimulator creaSimulazionePartitaConStregaStessoNumeroAttrezzi(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto trilocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto").addAttrezzo("bastone", 1)
				.addStanza("cucina").setPersonaggio(new Strega("Strega", "Ciao"))
				.addStanzaVincente("camera").addAttrezzo("osso", 1)
				.addAdiacenza("salotto", "cucina", Direzione.NORD)
				.addAdiacenza("cucina", "camera", Direzione.EST)
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, trilocale);
		gioco.gioca();
		return io;
	}
	
	public static IOSimulator creaSimulazionePartitaConPersonaggi(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.addStanza("Bagno").setPersonaggio(new Cane("Fuffi", "bau!", new Attrezzo("bastone", 2))).addAttrezzo("croccantini", 1).addAttrezzo("pistola", 1)
				.addAdiacenza("Bagno", "Atrio", Direzione.SUD)
				.addStanza("Studio").setPersonaggio(new Mago("Merlino", "Sono in grado di leggere i cuori e capire i veri desideri delle persone.", new Attrezzo("bacchetta", 1))).addAttrezzo("libro", 4)
				.addAdiacenza("Atrio", "Studio", Direzione.OVEST)
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
		return io;
	}

	public static Attrezzo creaAttrezzoEAggiugniAStanza(Stanza stanzaDaRiempire, String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		stanzaDaRiempire.addAttrezzo(attrezzo);
		return attrezzo;
	}

}
