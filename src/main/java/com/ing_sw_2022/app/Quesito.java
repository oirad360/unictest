package com.ing_sw_2022.app;

import java.util.ArrayList;

public class Quesito {
    private String testo;
    private int difficoltà;
    private String fonte;
    private String id;
    private Tutor tutor;
    private Visibilità visibilità;
    private ArrayList<Risposta> risposte;

    public Quesito(String id, Tutor tutor) {
        this.id = id;
        this.tutor = tutor;
        this.risposte = new ArrayList<>();
    }

    public void addRisposta(Risposta r){
        risposte.add(r);
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getDifficoltà() {
        return difficoltà;
    }

    public void setDifficoltà(int difficoltà) {
        this.difficoltà = difficoltà;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Visibilità getVisibilità() {
        return visibilità;
    }

    public void setVisibilità(Visibilità visibilità) {
        this.visibilità = visibilità;
    }

    public ArrayList<Risposta> getRisposte() {
        return risposte;
    }



    public void inserisciRisposta(String testo, boolean valore){
        //Setting id risposta
        String newId;
        if(risposte.isEmpty()) newId = id+"-0";
        else newId = id+"-"+(Integer.parseInt(risposte.get(risposte.size()-1).getId().split("-")[2])+1);
        Risposta r = new Risposta(testo, valore, newId);
        risposte.add(r);
    }

    ////////////////////////TOSTRING MOMENTANEO////////////////////
    @Override
    public String toString() {
        return "Quesito{" +
                "testo='" + testo + "'\n" +
                "difficoltà='" + difficoltà + "'\n" +
                "fonte='" + fonte + "'\n" +
                "id='" + id + "'\n" +
                "tutor='" + tutor + "'\n" +
                "visibilità='" + visibilità + "'\n" +
                "risposte='" + risposte + "'\n" +
                "}'\n";
    }
}
