package com.ing_sw_2022.app;

//import sun.reflect.generics.tree.Tree;

import java.io.Serializable;
import java.util.HashMap;
import java.util.TreeMap;

public class Materia implements Serializable {
    private String nome;
    private String codice;
    private TreeMap<String,Quesito> mappaQuesiti;
    private Quesito corrente;
    private static final long serialVersionUID = 1;

    public Materia(String nome, String codice) {
        this.nome = nome;
        this.codice = codice;
        this.mappaQuesiti = new TreeMap<>();
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

    public TreeMap<String, Quesito> getMappaQuesiti() {
        return mappaQuesiti;
    }

    public Quesito getCorrente() {
        return corrente;
    }

    public void setCorrente(Quesito corrente) {
        this.corrente = corrente;
    }

    public void nuovoQuesito(Tutor tutorAutenticato){
        //Setting id quesito
        String newId;
        if(mappaQuesiti.isEmpty()) newId = codice+"-0";
        else newId = codice+"-"+(Integer.parseInt(mappaQuesiti.lastKey().split("-")[1])+1);

        Quesito q = new Quesito(newId,tutorAutenticato);
        corrente = q; //q diventa corrente per Materia
    }

    public void inserisciFonte(String fonte){
        corrente.setFonte(fonte);
    }

    public void inserisciTesto(String testo){
        corrente.setTesto(testo);
    }

    public void inserisciRisposta(String testo, boolean valore){
        corrente.inserisciRisposta(testo, valore);
    }

    public void inserisciDifficoltà(int difficoltà){
        corrente.setDifficoltà(difficoltà);
    }

    public void confermaQuesito(Visibilità v){
        corrente.setVisibilità(v);
        String idQuesitoCorrente = corrente.getId();
        mappaQuesiti.put(idQuesitoCorrente, corrente);
    }

    ///////////////////// TO STRING MOMENTANEO ////////////////////
    @Override
    public String toString() {
        return "Materia{" +
                "nome='" + nome + '\'' +
                ", codice='" + codice + '\'' +
                '}';
    }
}
