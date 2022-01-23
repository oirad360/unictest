package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Studente extends Utente /*implements Serializable*/ {
    /*private String nome;
    private String cognome;
    private String cf;*/
    private TemplatePersonalizzato templateCorrente;
    private TreeMap<Integer, TemplatePersonalizzato> mappaTemplatePersonalizzati;
    private TemplatePersonalizzato templateSelezionato;
    private static final long serialVersionUID = 1;

    public Studente(String nome, String cognome, String cf) {
        /*this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;*/
        super(nome,cognome,cf);
        mappaTemplatePersonalizzati = new TreeMap<Integer,TemplatePersonalizzato>();
    }

    /*public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }*/

    public Map<Integer, TemplatePersonalizzato> getMappaTemplatePersonalizzati() {
        return mappaTemplatePersonalizzati;
    }

    public TemplatePersonalizzato getTemplateCorrente() {
        return templateCorrente;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "nome='" + super.getNome() + '\'' +
                ", cognome='" + super.getCognome() + '\'' +
                ", cf='" + super.getCf() + '\'' +
                ", mappaTemplatePersonalizzati=" + mappaTemplatePersonalizzati +
                '}';
    }

    /////////////////////////////////////////////METODI DCD///////////////////////////////////////////////
               ////////////////////UC2 CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////
    public void nuovoTemplate(String nome){
        int id = 0;
        if(!mappaTemplatePersonalizzati.isEmpty()) id = mappaTemplatePersonalizzati.lastKey()+1;
        templateCorrente = new TemplatePersonalizzato(id, nome);
    }

    public void inserisciInfoTemplate(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio){
        templateCorrente.setPuntiCorretta(puntiCorretta);
        templateCorrente.setPuntiErrata(puntiErrata);
        templateCorrente.setPuntiNonData(puntiNonData);
        templateCorrente.setNumRisposte(numRisposte);
        templateCorrente.setMinRisposteCorrette(minRisposteCorrette);
        templateCorrente.setMaxRisposteCorrette(maxRisposteCorrette);
        templateCorrente.setTempoMedio(tempoMedio);
    }

    public void creaSezione(Materia m, int numQuesiti, int difficoltàMedia){
        templateCorrente.creaSezione(m,numQuesiti,difficoltàMedia);
    }

    public void confermaTemplate(){
        mappaTemplatePersonalizzati.put(templateCorrente.getId(),templateCorrente);
        templateCorrente=null;
        UniCTest.getInstance().serialize();
        System.out.println(mappaTemplatePersonalizzati);
    }

               ////////////////////UC1 AVVIA SIMULAZIONE/////////////////////
    public ArrayList<TemplatePersonalizzato> visualizzaTemplate(){
        ArrayList<TemplatePersonalizzato> listaTemplate = new ArrayList<TemplatePersonalizzato>(mappaTemplatePersonalizzati.values());
        return listaTemplate;
    }
    public Test avviaSimulazione(String idTemplate){
        TemplatePersonalizzato tp=mappaTemplatePersonalizzati.get(idTemplate);
        Test t = tp.avviaSimulazione();
        templateSelezionato=tp;
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
