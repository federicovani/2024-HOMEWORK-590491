package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOSimulator implements IO{
	
	private List<String> comandiDaLeggere;
	private List<String> comandiLetti;
	private String rigaAttuale;
	private Map<String, List<String>> comando2messaggi;
	//private List<String> messaggi;
	private int indiceComandi;
	private int indiceMessaggi;
	
	public IOSimulator (List<String> comandiDaLeggere) {
		this.comandiDaLeggere = comandiDaLeggere;
		this.comandiLetti = new ArrayList<String>();
		this.rigaAttuale = "introduzione";
		this.comando2messaggi = new HashMap<String, List<String>>();
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
		this.rigaAttuale = this.comandiDaLeggere.remove(0);
		comandiLetti.add(rigaAttuale);
		return this.getRigaAttuale();
	}
	
	public String getRigaAttuale() {
		return this.rigaAttuale;
	}
	
	public String nextMessaggio() {
		List<String> messaggi = this.comando2messaggi.get(this.comandiLetti.get(this.indiceComandi));
		String messaggio = messaggi.get(this.indiceMessaggi);
		this.indiceMessaggi++;
		if(this.indiceMessaggi > messaggi.size()) {
			indiceComandi++;
			indiceMessaggi = 0;
		}
		return messaggio;
	}
	
	public boolean hasNextMessaggio() {
		return indiceComandi <= comandiLetti.size();
	}
}
