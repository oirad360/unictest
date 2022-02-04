package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Test implements Serializable {

	private String id;
	private float punteggioComplessivo;
	private Template template;
	private TreeMap<String, QuesitoReale> mappaQuesiti;
	private static final long serialVersionUID = 1;


	public Test(String id, Template template, boolean riempimento) throws Exception { //La costruzione del Test fallisce se non posso soddisfare i requisiti del template
		this.id = id;
		this.template = template;
		this.mappaQuesiti = new TreeMap<>();
		if(riempimento) riempimento();
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

	public Template getTemplate() {
		return template;
	}

	public TreeMap<String, QuesitoReale> getMappaQuesiti() {
		return mappaQuesiti;
	}

	private void riempimento() throws Exception {
		ArrayList<Sezione> listaSezioni = template.getListaSezioni();
		for(Sezione s : listaSezioni){
			Materia m = s.getMateria();
			List<QuesitoDescrizione> listaQuesiti = m.generaQuesiti(template,s); //Questo è il potenziale punto di lancio dell'eccezione
			for(QuesitoDescrizione qd : listaQuesiti){
				String newId;
				if(mappaQuesiti.isEmpty()) newId = id+"-0";
				else newId = id+"-"+(Integer.parseInt(mappaQuesiti.lastKey().split("-")[2])+1);
				QuesitoReale qr = new QuesitoReale(newId,qd);
				mappaQuesiti.put(qr.getId(),qr);
			}
		}
	}

	@Override
	public String toString() {
		return "Test{" +"\n"+
				"id='" + id + "\n"+
				", punteggioComplessivo=" + punteggioComplessivo +"\n"+
				", templatePersonalizzato=" + template.getNome() +"\n"+
				", mappaQuesiti=" + mappaQuesiti +"\n"+
				'}';
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
				case 0 : temp+= template.getPuntiNonData(); break;
				case 1 : temp+= template.getPuntiCorretta(); break;
				case -1: temp+= template.getPuntiErrata(); break;
				default: System.out.println("ERRORE: Setting errato dei punti del quesito"); break;
			}
		}
		punteggioComplessivo=temp;
	}

}
