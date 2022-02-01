package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.Sezione;
import com.ing_sw_2022.app.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public abstract class Template implements Serializable {
    private int id;
    private String nome;
    private int numRisposte;
    private int minRisposteCorrette;
    private int maxRisposteCorrette;
    private float puntiCorretta;
    private float puntiErrata;
    private float puntiNonData;
    private ArrayList<Sezione> listaSezioni;
    private Test testCorrente;
    private TreeMap<String,Test> mappaTest;
    private static final long serialVersionUID = 1;

    public Template(int id, String nome) {
        this.id = id;
        this.nome = nome;
        listaSezioni = new ArrayList<Sezione>();
        mappaTest = new TreeMap<String,Test>();
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

    public int getNumRisposte() {
        return numRisposte;
    }

    public int getMinRisposteCorrette() {
        return minRisposteCorrette;
    }

    public int getMaxRisposteCorrette() {
        return maxRisposteCorrette;
    }

    public float getPuntiCorretta() {
        return puntiCorretta;
    }

    public float getPuntiErrata() {
        return puntiErrata;
    }

    public float getPuntiNonData() {
        return puntiNonData;
    }

    public ArrayList<Sezione> getListaSezioni() {
        return listaSezioni;
    }

    public Test getTestCorrente() {
        return testCorrente;
    }

    public void setInfoTemplate(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette){
        this.puntiCorretta=puntiCorretta;
        this.puntiErrata=puntiErrata;
        this.puntiNonData=puntiNonData;
        this.numRisposte=numRisposte;
        this.minRisposteCorrette=minRisposteCorrette;
        this.maxRisposteCorrette=maxRisposteCorrette;
    }

                            ///////////////////////////UC1 AVVIA SIMULAZIONE//////////////////////////
    public Test avviaSimulazione() throws Exception{
        String newId;
        if(mappaTest.isEmpty()) newId = id+"-0";
        else newId = id+"-"+(Integer.parseInt(mappaTest.lastKey().split("-")[1])+1);
        Test t = new Test(newId,this);
        testCorrente=t;
        return t;
    }

    public void selezionaRisposta(String idQuesitoReale, String idRisposta){
        testCorrente.selezionaRisposta(idQuesitoReale, idRisposta);
    }

    public Test terminaSimulazione(){
        testCorrente.terminaSimulazione();
        mappaTest.put(testCorrente.getId(), testCorrente);
        return testCorrente;
    }
}
