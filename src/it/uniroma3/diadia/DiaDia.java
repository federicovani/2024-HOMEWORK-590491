package it.uniroma3.diadia;

import it.uniroma3.ambienti.Stanza;
import it.uniroma3.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

	private Partita partita;
	private IOConsole console;

	public DiaDia(IOConsole console) {
		this.partita = new Partita();
		this.console = console;
	}

	public void gioca() {
		String istruzione; 

		console.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else
			console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]);
		console.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			console.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.labirinto.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.labirinto.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.giocatore.getCfu();
			this.partita.giocatore.setCfu(cfu--);
		}
		console.mostraMessaggio(partita.labirinto.getStanzaCorrente().getDescrizione());
		console.mostraMessaggio(partita.giocatore.borsa.getDescrizione());
	}
	
	/**
	 * Controlla se nella stanza è presente l'attrezzo che si desidera prendere.
	 * Se capienza e peso massimo della borsa ne permettono l'inserimento lo mette nella borsa
	 * e lo rimuove dalla stanza, mandando un messaggio di conferma.
	 * Altrimenti manda un messaggio di errore.
	 */
	private void prendi(String attrezzo) {
		if(attrezzo==null)
			console.mostraMessaggio("Che attrezzo vuoi prendere?");
		Attrezzo attrezzoCercato = null;
		attrezzoCercato = this.partita.labirinto.getStanzaCorrente().getAttrezzo(attrezzo);
		if(attrezzoCercato == null)
			console.mostraMessaggio("Attrezzo inesistente");
		else {
			if(this.partita.giocatore.borsa.addAttrezzo(attrezzoCercato)) {
				this.partita.labirinto.getStanzaCorrente().removeAttrezzo(attrezzoCercato);
				console.mostraMessaggio("Attrezzo preso");
			} else console.mostraMessaggio("Non è stato possibile prendere l'attrezzo.");
		}	
		console.mostraMessaggio(partita.labirinto.getStanzaCorrente().getDescrizione());
		console.mostraMessaggio(partita.giocatore.borsa.getDescrizione());
	}
	
	/**
	 * Controlla se nella borsa è presente l'attrezzo che si desidera posare e lo
	 * posa nella stanza rimuovendolo dalla borsa e mandando un messaggio di conferma.
	 * Altrimenti manda un messaggio di errore.
	 */
	private void posa(String attrezzo) {
		if(attrezzo==null)
			console.mostraMessaggio("Che attrezzo vuoi posare?");
		Attrezzo attrezzoCercato = null;
		attrezzoCercato = this.partita.giocatore.borsa.getAttrezzo(attrezzo);
		if(attrezzoCercato == null)
			console.mostraMessaggio("Non possiedi questo attrezzo.");
		else {
			if(this.partita.labirinto.getStanzaCorrente().addAttrezzo(attrezzoCercato)) {
				this.partita.giocatore.borsa.removeAttrezzo(attrezzo);
				console.mostraMessaggio("Attrezzo posato.");
			}
		}
		console.mostraMessaggio(partita.labirinto.getStanzaCorrente().getDescrizione());
		console.mostraMessaggio(partita.giocatore.borsa.getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}