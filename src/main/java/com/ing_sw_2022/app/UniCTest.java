package com.ing_sw_2022.app;

import java.util.HashMap;
import java.util.List;

public class UniCTest {
    private static UniCTest unictest;
    private HashMap<String,Materia> mappaMaterie;
    private HashMap<String,Visibilità> mappaVisibilità;
    private Materia corrente;
    private Tutor tutorAutenticato;
    private HashMap<String, Tutor> mappaTutor;

    private UniCTest() {
        this.mappaMaterie = new HashMap<>();
        this.mappaVisibilità = new HashMap<>();
        this.mappaTutor = new HashMap<>();
        //this.corrente = corrente;
    }

    public static UniCTest getInstance() {
        if (unictest == null)
            unictest = new UniCTest();
        else
            System.out.println("Istanza già creata");

        return unictest;
    }

    public void addMateria(String codice, Materia m){
        mappaMaterie.put(codice, m);
    }

    public void addVisibilità(String codice, Visibilità v ){
        mappaVisibilità.put(codice, v);
    }

    public static void setUnictest(UniCTest unictest) {
        UniCTest.unictest = unictest;
    }

    public HashMap<String, Materia> getMappaMaterie() {
        return mappaMaterie;
    }

    public HashMap<String, Visibilità> getMappaVisibilità() {
        return mappaVisibilità;
    }

    public Materia getCorrente() {
        return corrente;
    }

    public void setCorrente(Materia corrente) {
        this.corrente = corrente;
    }

    //Metodi nuovi
    public List visualizzaMaterieInsegnate(){
        return tutorAutenticato.getMaterieInsegnate();
    }

    public void nuovoQuesito(String codiceMateria){
        Materia m = mappaMaterie.get(codiceMateria);
        corrente = m; //m diventa corrente per UniCTest
        m.nuovoQuesito(tutorAutenticato);
    }

    public void inserisciFonte(String fonte){
        corrente.inserisciFonte(fonte);
    }

    public void inserisciTesto(String testo){
        corrente.inserisciTesto(testo);
    }

    public void inserisciRisposta(String testo, boolean valore){
        corrente.inserisciRisposta(testo, valore);
    }

    public void inserisciDifficoltà(int difficoltà){
        corrente.inserisciDifficoltà(difficoltà);
    }

    public void confermaQuesito(String codiceVisibilità){
        Visibilità v = mappaVisibilità.get(codiceVisibilità);
        corrente.confermaQuesito(v);
    }

    //MOMENTANEO: Caso d'uso di avviamento
    public void setTutorAutenticato(Tutor tutorAutenticato) {
        this.tutorAutenticato = tutorAutenticato;
    }
}
