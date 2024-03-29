package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;

public class TemplatePersonalizzato implements Serializable {
    private int id;
    private String nome;
    private int tempoMedio;
    private int numRisposte;
    private int minRisposteCorrette;
    private int maxRisposteCorrette;
    private float puntiCorretta;
    private float puntiErrata;
    private float puntiNonData;
    private ArrayList<Sezione> listaSezioni;
    private static final long serialVersionUID = 1;

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

    public int getMinRisposteCorrette() {
        return minRisposteCorrette;
    }

    public void setMinRisposteCorrette(int minRisposteCorrette) {
        this.minRisposteCorrette = minRisposteCorrette;
    }

    public int getMaxRisposteCorrette() {
        return maxRisposteCorrette;
    }

    public void setMaxRisposteCorrette(int maxRisposteCorrette) {
        this.maxRisposteCorrette = maxRisposteCorrette;
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

    public void creaSezione(Materia materia, int numQuesiti, int difficoltàMedia){
        String newId;
        if(listaSezioni.isEmpty()) newId = id+"-0";
        else newId = id+"-"+(Integer.parseInt(listaSezioni.get(listaSezioni.size()-1).getId().split("-")[1])+1);
        Sezione s = new Sezione(newId, materia, numQuesiti, difficoltàMedia);
        listaSezioni.add(s);
    }

    @Override
    public String toString() {
        return "TemplatePersonalizzato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tempoMedio=" + tempoMedio +
                ", numRisposte=" + numRisposte +
                ", minRisposteCorrette=" + minRisposteCorrette +
                ", maxRisposteCorrette=" + maxRisposteCorrette +
                ", puntiCorretta=" + puntiCorretta +
                ", puntiErrata=" + puntiErrata +
                ", puntiNonData=" + puntiNonData +
                ", listaSezioni=" + listaSezioni +
                '}';
    }
}
