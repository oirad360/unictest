package com.ing_sw_2022.app;

import org.graalvm.compiler.lir.LIRInstruction;

import java.util.TreeMap;

public class Studente {
    private String nome;
    private String cognome;
    private String cf;
    private TemplatePersonalizzato templateCorrente;
    private TreeMap<Integer, TemplatePersonalizzato> mappaTemplatePersonalizzati;

    public Studente(String nome, String cognome, String cf) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        mappaTemplatePersonalizzati = new TreeMap<Integer,TemplatePersonalizzato>();
    }

    public String getNome() {
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
    }

    public TreeMap<Integer, TemplatePersonalizzato> getMappaTemplatePersonalizzati() {
        return mappaTemplatePersonalizzati;
    }
    
    public void nuovoTemplate(String nome){
        int id = 0;
        if(!mappaTemplatePersonalizzati.isEmpty()) id = mappaTemplatePersonalizzati.lastKey()+1;
        templateCorrente = new TemplatePersonalizzato(id, nome);
    }

    public void inserisciInfoTemplate(int puntiCorretta, int puntiErrata, int puntiNonData, int numRisposte, int numRisposteCorrette, int tempoMedio){
        templateCorrente.setPuntiCorretta(puntiCorretta);
        templateCorrente.setPuntiErrata(puntiErrata);
        templateCorrente.setPuntiNonData(puntiNonData);
        templateCorrente.setNumRisposte(numRisposte);
        templateCorrente.setNumRisposteCorrette(numRisposteCorrette);
        templateCorrente.setTempoMedio(tempoMedio);
    }

    public void creaSezione(Materia m, int numQuesiti, int difficoltàMedia){
        templateCorrente.creaSezione(m,numQuesiti,difficoltàMedia);
    }

    public void confermaTemplate(){
        mappaTemplatePersonalizzati.put(templateCorrente.getId(),templateCorrente);
        templateCorrente=null;
        UniCTest.getInstance().serialize();
    }
}
