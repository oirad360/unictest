package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Studente extends Utente implements Serializable {
    private TemplatePersonalizzato templateCorrente;
    private TreeMap<Integer, TemplatePersonalizzato> mappaTemplatePersonalizzati;
    private Template templateSelezionato;
    private static final long serialVersionUID = 1;

    public Studente(String nome, String cognome, String cf) {
        super(nome,cognome,cf);
        mappaTemplatePersonalizzati = new TreeMap<Integer,TemplatePersonalizzato>();
    }

    public Map<Integer, TemplatePersonalizzato> getMappaTemplatePersonalizzati() {
        return mappaTemplatePersonalizzati;
    }

    public TemplatePersonalizzato getTemplateCorrente() {
        return templateCorrente;
    }

    public Template getTemplateSelezionato() {
        return templateSelezionato;
    }

    @Override
    public String toString() {
        return "Studente{" +
                "nome='" + super.getNome() + '\'' +
                ", cognome='" + super.getCognome() + '\'' +
                ", cf='" + super.getCf() + '\'' +
                ", mappaTemplatePersonalizzati=" + mappaTemplatePersonalizzati +
                '}';
    }

    /////////////////////////////////////////////METODI DCD///////////////////////////////////////////////
               ////////////////////UC2 CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////
    public void nuovoTemplateP(String nome){
        int id = 0;
        if(!mappaTemplatePersonalizzati.isEmpty()) id = mappaTemplatePersonalizzati.lastKey()+1;
        templateCorrente = new TemplatePersonalizzato(id, nome);
    }

    public void inserisciInfoTemplateP(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio){
        /*templateCorrente.setPuntiCorretta(puntiCorretta);
        templateCorrente.setPuntiErrata(puntiErrata);
        templateCorrente.setPuntiNonData(puntiNonData);
        templateCorrente.setNumRisposte(numRisposte);
        templateCorrente.setMinRisposteCorrette(minRisposteCorrette);
        templateCorrente.setMaxRisposteCorrette(maxRisposteCorrette);
        templateCorrente.setTempoMedio(tempoMedio);*/
        templateCorrente.setInfoTemplate(puntiCorretta,puntiErrata,puntiNonData,numRisposte,minRisposteCorrette,maxRisposteCorrette);
        templateCorrente.setTempoMedio(tempoMedio);
    }

    public void creaSezioneP(Materia m, int numQuesiti, int difficoltàMedia){
        templateCorrente.creaSezione(m,numQuesiti,difficoltàMedia);
    }

    public void confermaTemplateP(){
        mappaTemplatePersonalizzati.put(templateCorrente.getId(),templateCorrente);
        templateCorrente=null;
    }

               ////////////////////UC1 AVVIA SIMULAZIONE/////////////////////
    public ArrayList<TemplatePersonalizzato> visualizzaTemplate(){
        ArrayList<TemplatePersonalizzato> listaTemplate = new ArrayList<TemplatePersonalizzato>(mappaTemplatePersonalizzati.values());
        return listaTemplate;
    }
    public Test avviaSimulazione(int idTemplate) throws Exception{
        TemplatePersonalizzato tp=mappaTemplatePersonalizzati.get(idTemplate);
        templateSelezionato=tp;
        Test t = tp.avviaSimulazione();
        return t;
    }

    public void selezionaRisposta(String idQuesitoReale, String idRisposta){
        templateSelezionato.selezionaRisposta(idQuesitoReale, idRisposta);
    }

    public Test terminaSimulazione(){
        Test t=templateSelezionato.terminaSimulazione();
        templateSelezionato=null;
        return t;
    }


}
