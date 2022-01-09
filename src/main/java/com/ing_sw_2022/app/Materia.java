package com.ing_sw_2022.app;

import java.util.HashMap;

public class Materia {
    private String nome;
    private String codice;
    private HashMap<String,Quesito> mappaQuesiti;
    private Quesito corrente;

    public Materia(String nome, String codice, Quesito corrente) {
        this.nome = nome;
        this.codice = codice;
        this.mappaQuesiti = new HashMap<>();
        this.corrente = corrente;
    }

    public void addQuesito(String id, Quesito q){
        mappaQuesiti.put(id, q);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public HashMap<String, Quesito> getMappaQuesiti() {
        return mappaQuesiti;
    }

    public Quesito getCorrente() {
        return corrente;
    }

    public void setCorrente(Quesito corrente) {
        this.corrente = corrente;
    }

    public void nuovoQuesito(){

    }

    public void inserisciFonte(String fonte){

    }

    public void inserisciDifficoltà(int difficoltà){

    }

    public void inserisciRisposta(String testo, boolean valore){

    }

    public void confermaQuesito(Visibilità v){

    }
}
