package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.comandi.ComandoFine;

public class IOSimulator implements IO{
	
	private List<String> comandiDaLeggere;
	private List<String> comandiLetti;
	private String rigaAttuale;
	private Map<String, List<String>> comando2messaggi;
	private int indiceComandi;
	private int indiceMessaggi;
	
	public IOSimulator (List<String> comandiDaLeggere) {
		this.comandiDaLeggere = comandiDaLeggere;
		this.comandiLetti = new LinkedList<String>();
		this.rigaAttuale = "introduzione";
		this.comandiLetti.add(rigaAttuale);
		this.comando2messaggi = new LinkedHashMap<String, List<String>>();
		this.indiceComandi = 0;
		this.indiceMessaggi = 0;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		String comando = this.getRigaAttuale();
		List<String> messaggi;
		if(comando2messaggi.containsKey(comando))
			messaggi = comando2messaggi.get(comando);
		else
			messaggi = new ArrayList<String>();
		messaggi.add(messaggio);
		
		comando2messaggi.put(comando, messaggi);
	}

	@Override
	public String leggiRiga() {
		try {
			if(this.rigaAttuale == this.comandiDaLeggere.get(0)) {
				this.comandiDaLeggere.remove(0);
				return this.getRigaAttuale();
			}
			this.rigaAttuale = this.comandiDaLeggere.remove(0);
			comandiLetti.add(rigaAttuale);
			return this.getRigaAttuale();
		} catch(Exception e) { //Se non si lancia manualmente il comando fine il metodo lanciava un OutOfBoundException che faceva fallire i test.
			this.rigaAttuale = "fine";
			this.mostraMessaggio(ComandoFine.MESSAGGIO_FINE);
			return this.getRigaAttuale();
		}
	}
	
	public String getRigaAttuale() {
		return this.rigaAttuale;
	}
	
	public String nextMessaggio() {
		List<String> messaggi = this.comando2messaggi.get(this.comandiLetti.get(this.indiceComandi));
		String messaggio = messaggi.get(this.indiceMessaggi);
		this.indiceMessaggi++;
		if(this.indiceMessaggi >= messaggi.size()) {
			indiceComandi++;
			indiceMessaggi = 0;
		}
		return messaggio;
	}
	
	public boolean hasNextMessaggio() {
		return indiceComandi < comandiLetti.size();
	}
	
	public void debug() {
		System.out.println("\n------------\n");
		for (Iterator<String> iterator = comando2messaggi.keySet().iterator(); iterator.hasNext();) {
			String comando = iterator.next();
			List<String> list = comando2messaggi.get(comando);
			for (int i = 0; i < list.size(); i++) {
				String messaggio = list.get(i);
				System.out.println(comando + ": " + messaggio);
			}
		}
		System.out.println("\n------------\n");
	}
}
