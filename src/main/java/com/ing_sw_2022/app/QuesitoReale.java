package com.ing_sw_2022.app;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.TreeMap;

public class QuesitoReale implements Serializable {

	private String id;
	private TreeMap<String,Risposta> risposteDate;
	private QuesitoDescrizione quesitoDescrizione;
	private static final long serialVersionUID = 1;

	public QuesitoReale(String id, QuesitoDescrizione quesitoDescrizione) {
		this.id = id;
		this.quesitoDescrizione = quesitoDescrizione;
		this.risposteDate = new TreeMap<String, Risposta>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TreeMap<String, Risposta> getRisposteDate() {
		return risposteDate;
	}

	public QuesitoDescrizione getQuesitoDescrizione() {
		return quesitoDescrizione;
	}

	public void setQuesitoDescrizione(QuesitoDescrizione quesitoDescrizione) {
		this.quesitoDescrizione = quesitoDescrizione;
	}

	@Override
	public String toString() {
		return "QuesitoReale{" +
				"id='" + id + '\'' +
				", risposteDate=" + risposteDate +
				", quesitoDescrizione=" + quesitoDescrizione +
				'}';
	}

	/////////////////////////////////////////////METODI DCD///////////////////////////////////////////////
	        ////////////////////UC1 AVVIA SIMULAZIONE/////////////////////
	public void selezionaRisposta(String idRisposta) {
		TreeMap<String, Risposta> risposte;
		Risposta newR;
		Risposta r = risposteDate.get(idRisposta);
		if(r==null){
			risposte =  quesitoDescrizione.getRisposte();
			newR = risposte.get(idRisposta);
			risposteDate.put(newR.getId(), newR);
		} else {
			risposteDate.remove(r.getId());
		}
	}

	public int verificaCorrettezza() {
		if(risposteDate.size()==0) return 0;
		ArrayList<Risposta> listaAlternative = new ArrayList<>(quesitoDescrizione.getRisposte().values());
		ArrayList<Risposta> listaRisposteDate = new ArrayList<>(risposteDate.values());
		for(Risposta r: listaAlternative){ //Prendo tutte le alternative di un quesito
			if(r.isValore()) { //La risposta era corretta, quindi vedo se l'ho segnata
				if (!listaRisposteDate.contains(r)) return -1; //Se è corretta ma non l'ho segnata allora ho sbagliato.
			} else { //La risposta era errata, quindi vedo se NON l'ho segnata
				if (listaRisposteDate.contains(r)) return -1; ///Se è errata ma l'ho segnata allora ho sbagliato
			}
		}
		return 1; //Se sono arrivato fin qui allora ho segnato tutte quelle corrette e non ho segnato alcuna risposta sbagliata, quindi la risposta è giusta!
	}

}
