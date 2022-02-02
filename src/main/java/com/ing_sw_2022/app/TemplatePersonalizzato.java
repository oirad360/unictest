package com.ing_sw_2022.app;

import java.io.Serializable;

public class TemplatePersonalizzato extends Template implements Serializable{
    /*private int id;
    private String nome;
    private int numRisposte;
    private int minRisposteCorrette;
    private int maxRisposteCorrette;
    private float puntiCorretta;
    private float puntiErrata;
    private float puntiNonData;
    private ArrayList<Sezione> listaSezioni;
    private Test testCorrente;
    private TreeMap<String,Test> mappaTest;*/
    private int tempoMedio;
    private static final long serialVersionUID = 1;

    public TemplatePersonalizzato(String id, String nome) {
        super(id,nome);
    }

   /* public int getId() {
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

    public Test getTestCorrente() {
        return testCorrente;
    }*/

    public int getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(int tempoMedio) {
        this.tempoMedio = tempoMedio;
    }
/*

    /////////////////////////////////////////////METODI DCD///////////////////////////////////////////////

                        ///////////////////////UC2 CREA TEMPLATE DI TEST PERSONALIZZATO///////////////////////
    public void creaSezioneP(Materia materia, int numQuesiti, int difficoltàMedia){
        String newId;
        if(listaSezioni.isEmpty()) newId = id+"-0";
        else newId = id+"-"+(Integer.parseInt(listaSezioni.get(listaSezioni.size()-1).getId().split("-")[1])+1);
        Sezione s = new Sezione(newId, materia, numQuesiti, difficoltàMedia);
        listaSezioni.add(s);
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
*/

    @Override
    public String toString() {
        return "TemplatePersonalizzato{" +"\n"+
                "id=" + super.id +"\n"+
                ", nome=" + super.nome + "\n"+
                ", tempoMedio=" + tempoMedio +"\n"+
                ", numRisposte=" + super.numRisposte +"\n"+
                ", minRisposteCorrette=" + super.minRisposteCorrette +"\n"+
                ", maxRisposteCorrette=" + super.maxRisposteCorrette +"\n"+
                ", puntiCorretta=" + super.puntiCorretta +"\n"+
                ", puntiErrata=" + super.puntiErrata +"\n"+
                ", puntiNonData=" + super.puntiNonData +"\n"+
                ", listaSezioni=" + super.listaSezioni +"\n"+
                '}';
    }
}
