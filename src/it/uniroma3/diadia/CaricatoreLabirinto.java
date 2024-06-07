package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.exceptions.FormatoFileNonValidoException;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze magiche*/
	private static final String STANZE_MAGICHE_MARKER = "Stanze Magiche: ";  
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze bloccate*/
	private static final String STANZE_BLOCCATE_MARKER = "Stanze Bloccate: ";  
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze buie*/
	private static final String STANZE_BUIE_MARKER = "Stanze Buie: ";  

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";
	
	/* prefisso della riga contenente le specifiche dei maghi da collocare nel formato <nomeStanza> <nomeMago> <attrezzoMago> <pesoAttrezzoMago> <presentazione> */
	private static final String MAGHI_MARKER = "Maghi: ";  

	/* prefisso della riga contenente le specifiche dei streghe da collocare nel formato <nomeStanza> <nomeStrega> <presentazione> */
	private static final String STREGHE_MARKER = "Streghe: ";  
	
	/* prefisso della riga contenente le specifiche dei cani da collocare nel formato <nomeStanza> <nomeCane> <attrezzoInBocca> <pesoAttrezzo> <ciboPreferito> <presentazione> */
	private static final String CANI_MARKER = "Cani: ";  
	
	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeMagiche();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECollocaMaghi();
			this.leggiECollocaStreghe();
			this.leggiECollocaCani();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}
	
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {	
			Stanza stanza;
			String nomeStanza = null;
			String attrezzoLuminoso = null;
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza buia."));
				nomeStanza = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					attrezzoLuminoso = scannerLinea.next();
					stanza = new StanzaBuia(nomeStanza, attrezzoLuminoso);
				} else stanza = new StanzaBuia(nomeStanza);
			}
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}
	
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {	
			Stanza stanza;
			String nomeStanza = null;
			String direzioneBloccata = null;
			String attrezzoPerSbloccare = null;
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza bloccata."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata della stanza."));
				direzioneBloccata = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					attrezzoPerSbloccare = scannerLinea.next();
					stanza = new StanzaBloccata(nomeStanza, Direzione.valueOf(direzioneBloccata.toUpperCase()), attrezzoPerSbloccare);
				} else stanza = new StanzaBloccata(nomeStanza, Direzione.valueOf(direzioneBloccata.toUpperCase()));
			}
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}
	
	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {	
			Stanza stanza = null;
			String nomeStanza = null;
			int sogliaMagica;
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza magica."));
				nomeStanza = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					try {
						sogliaMagica = Integer.valueOf(scannerLinea.next());
						stanza = new StanzaMagica(nomeStanza, sogliaMagica);
					} catch (NumberFormatException e) {
						check(false, "la soglia magica di " + nomeStanza + " deve essere un numero");
					}
				} else stanza = new StanzaMagica(nomeStanza);
			}
			this.nome2stanza.put(nomeStanza, stanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while(scanner.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		String nomeStanzaVincente = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale + " non definita");
		nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}
	
	private void leggiECollocaMaghi() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);

		for(String specificaPersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String nomeStanza = null;
			String nomePersonaggio = null;
			String nomeAttrezzo = null;
			int pesoAttrezzo = 0;
			String presentazionePersonaggio = "";
			try (Scanner scannerLinea = new Scanner(specificaPersonaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove mettere un mago."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un mago."));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo di un mago."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo di un mago."));
				try {
					pesoAttrezzo = Integer.parseInt(scannerLinea.next());
				} catch (NumberFormatException e) {
					check(false, "Il peso dell'attrezzo del Mago " + nomePersonaggio + " deve essere un numero");
				}
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione di un mago."));
				while(scannerLinea.hasNext())
					presentazionePersonaggio = presentazionePersonaggio + " " + scannerLinea.next();
			}
			check(isStanzaValida(nomeStanza),"Mago "+ nomePersonaggio +" non collocabile: stanza " + nomeStanza +" inesistente.");
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
			Mago mago = new Mago(nomePersonaggio, presentazionePersonaggio, attrezzo);
			this.nome2stanza.get(nomeStanza).setPersonaggio(mago);
		}
	}
	
	private void leggiECollocaStreghe() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(STREGHE_MARKER);

		for(String specificaPersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String nomeStanza = null;
			String nomePersonaggio = null;
			String presentazionePersonaggio = "";
			try (Scanner scannerLinea = new Scanner(specificaPersonaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove mettere una strega."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una strega."));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione di una strega."));
				while(scannerLinea.hasNext())
					presentazionePersonaggio = presentazionePersonaggio + " " + scannerLinea.next();
			}
			check(isStanzaValida(nomeStanza),"Strefa "+ nomePersonaggio +" non collocabile: stanza " + nomeStanza +" inesistente.");
			Strega strega = new Strega(nomePersonaggio, presentazionePersonaggio);
			this.nome2stanza.get(nomeStanza).setPersonaggio(strega);
		}
	}
	
	private void leggiECollocaCani() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(CANI_MARKER);

		for(String specificaPersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String nomeStanza = null;
			String nomePersonaggio = null;
			String attrezzoInBocca = null;
			int pesoAttrezzo = 0;
			String ciboPreferito = null;
			String presentazionePersonaggio = "";
			try (Scanner scannerLinea = new Scanner(specificaPersonaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove mettere un cane."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un cane."));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo che un cane tiene in bocca."));
				attrezzoInBocca = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo di un cane."));
				try {
					pesoAttrezzo = Integer.parseInt(scannerLinea.next());
				} catch (NumberFormatException e) {
					check(false, "Il peso dell'attrezzo del Cane " + nomePersonaggio + " deve essere un numero");
				}
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il cibo preferito di un cane."));
				ciboPreferito = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione di un cane."));
				while(scannerLinea.hasNext())
					presentazionePersonaggio = presentazionePersonaggio + " " + scannerLinea.next();
			}
			check(isStanzaValida(nomeStanza),"Cane "+ nomePersonaggio +" non collocabile: stanza " + nomeStanza +" inesistente.");
			Attrezzo attrezzo = new Attrezzo(attrezzoInBocca, pesoAttrezzo);
			Cane cane = new Cane(nomePersonaggio, presentazionePersonaggio, ciboPreferito, attrezzo);
			this.nome2stanza.get(nomeStanza).setPersonaggio(cane);
		}
	}

	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String uscita : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(uscita)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();
					
					impostaUscita(stanzaPartenza, Direzione.valueOf(dir.toUpperCase()), stanzaDestinazione);
				}
			}
		} 
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}