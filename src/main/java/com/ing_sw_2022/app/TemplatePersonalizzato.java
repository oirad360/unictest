package com.ing_sw_2022.app;

import java.util.ArrayList;

public class TemplatePersonalizzato{
    private int id;
    private String nome;
    private int tempoMedio;
    private int numRisposte;
    private int numRisposteCorrette;
    private float puntiCorretta;
    private float puntiErrata;
    private float puntiNonData;
    private ArrayList<Sezione> listaSezioni;

    public void creaSezione(Materia materia, int numQuesiti, int difficoltàMedia){
        String newId;
        if(listaSezioni.isEmpty()) newId = id+"-0";
        else newId = id+"-"+(Integer.parseInt(listaSezioni.get(listaSezioni.size()-1).getId().split("-")[1])+1);
        Sezione s = new Sezione(newId, materia, numQuesiti, difficoltàMedia);
        listaSezioni.add(s);
    }

    public TemplatePersonalizzato(int id, String nome) {
        this.id = id;
        this.nome = nome;
        listaSezioni = new ArrayList<Sezione>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(int tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public int getNumRisposte() {
        return numRisposte;
    }

    public void setNumRisposte(int numRisposte) {
        this.numRisposte = numRisposte;
    }

    public int getNumRisposteCorrette() {
        return numRisposteCorrette;
    }

    public void setNumRisposteCorrette(int numRisposteCorrette) {
        this.numRisposteCorrette = numRisposteCorrette;
    }

    public float getPuntiCorretta() {
        return puntiCorretta;
    }

    public void setPuntiCorretta(float puntiCorretta) {
        this.puntiCorretta = puntiCorretta;
    }

    public float getPuntiErrata() {
        return puntiErrata;
    }

    public void setPuntiErrata(float puntiErrata) {
        this.puntiErrata = puntiErrata;
    }

    public float getPuntiNonData() {
        return puntiNonData;
    }

    public void setPuntiNonData(float puntiNonData) {
        this.puntiNonData = puntiNonData;
    }

    public ArrayList<Sezione> getListaSezioni() {
        return listaSezioni;
    }
}
