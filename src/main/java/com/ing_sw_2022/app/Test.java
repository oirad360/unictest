package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Test implements Serializable {

	private String id;
	private float punteggioComplessivo;
	private TemplatePersonalizzato templatePersonalizzato;
	private TreeMap<String, QuesitoReale> mappaQuesiti;
	private static final long serialVersionUID = 1;


	public Test(String id, TemplatePersonalizzato templatePersonalizzato) throws Exception { //La costruzione del Test fallisce se non posso soddisfare i requisiti del template
		this.id = id;
		this.templatePersonalizzato = templatePersonalizzato;
		this.mappaQuesiti = new TreeMap<String, QuesitoReale>();

		ArrayList<Sezione> listaSezioni = templatePersonalizzato.getListaSezioni();
		for(Sezione s : listaSezioni){
			Materia m = s.getMateria();
			List<QuesitoDescrizione> listaQuesiti= null;
			listaQuesiti = m.generaQuesiti(templatePersonalizzato,s); //Questo è il potenziale punto di lancio dell'eccezione
			for(QuesitoDescrizione qd : listaQuesiti){
				String newId;
				if(mappaQuesiti.isEmpty()) newId = id+"-0";
				else newId = id+"-"+(Integer.parseInt(mappaQuesiti.lastKey().split("-")[2])+1);
				QuesitoReale qr = new QuesitoReale(newId,qd);
				mappaQuesiti.put(qr.getId(),qr);
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getPunteggioComplessivo() {
		return punteggioComplessivo;
	}

	public TemplatePersonalizzato getTemplatePersonalizzato() {
		return templatePersonalizzato;
	}

	public void setTemplatePersonalizzato(TemplatePersonalizzato templatePersonalizzato) {
		this.templatePersonalizzato = templatePersonalizzato;
	}

	public TreeMap<String, QuesitoReale> getMappaQuesiti() {
		return mappaQuesiti;
	}
	/////////////////////////////////////////////////METODI DCD/////////////////////////////////////////////

									////////////////////UC1 AVVIA SIMULAZIONE/////////////////////
	public void selezionaRisposta(String idQuesitoReale, String idRisposta) {
		QuesitoReale qr = mappaQuesiti.get(idQuesitoReale);
		qr.selezionaRisposta(idRisposta);
	}

	public void terminaSimulazione() {
		int correttezza;
		float temp=0;
		for (QuesitoReale qr : mappaQuesiti.values()) {
			correttezza = qr.verificaCorrettezza();
			switch(correttezza){
				case 0 : temp+= templatePersonalizzato.getPuntiNonData(); break;
				case 1 : temp+= templatePersonalizzato.getPuntiCorretta(); break;
				case -1: temp+= templatePersonalizzato.getPuntiErrata(); break;
				default: System.out.println("ERRORE: Setting errato dei punti del quesito"); break;
			}
		}
		punteggioComplessivo=temp;
	}

}
