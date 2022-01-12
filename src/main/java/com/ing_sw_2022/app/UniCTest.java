package com.ing_sw_2022.app;

import java.util.HashMap;
import java.util.List;

public class UniCTest {
    private static UniCTest unictest;
    private HashMap<String,Materia> mappaMaterie;
    private HashMap<String,Visibilità> mappaVisibilità;
    private Materia materiaCorrente;
    private Tutor tutorAutenticato;
    private HashMap<String, Tutor> mappaTutor;

    private UniCTest() {
        mappaMaterie = new HashMap<>();
        mappaVisibilità = new HashMap<>();
        mappaTutor = new HashMap<>();
        loadMaterie();
        loadTutor();
        loadVisibilità();
    }

    public static UniCTest getInstance() {
        if (unictest == null) unictest = new UniCTest();
        return unictest;
    }

    public void addMateria(String codice, Materia m){
        mappaMaterie.put(codice, m);
    }

    public void addVisibilità(String codice, Visibilità v ){
        mappaVisibilità.put(codice, v);
    }

    public void addTutor(String cf, Tutor t ){
        mappaTutor.put(cf, t);
    }

    public HashMap<String, Materia> getMappaMaterie() {
        return mappaMaterie;
    }

    public HashMap<String, Visibilità> getMappaVisibilità() {
        return mappaVisibilità;
    }

    public Materia getMateriaCorrente() {
        return materiaCorrente;
    }

    public void setMateriaCorrente(Materia materiaCorrente) {
        this.materiaCorrente = materiaCorrente;
    }

    //Metodi nuovi
    public List visualizzaMaterieInsegnate(){
        return tutorAutenticato.getMaterieInsegnate();
    }

    public void nuovoQuesito(String codiceMateria){
        Materia m = mappaMaterie.get(codiceMateria);
        materiaCorrente = m; //m diventa corrente per UniCTest
        m.nuovoQuesito(tutorAutenticato);
    }

    public void inserisciFonte(String fonte){
        materiaCorrente.inserisciFonte(fonte);
    }

    public void inserisciTesto(String testo){
        materiaCorrente.inserisciTesto(testo);
    }

    public void inserisciRisposta(String testo, boolean valore){
        materiaCorrente.inserisciRisposta(testo, valore);
    }

    public void inserisciDifficoltà(int difficoltà){
        materiaCorrente.inserisciDifficoltà(difficoltà);
    }

    public void confermaQuesito(String codiceVisibilità){
        Visibilità v = mappaVisibilità.get(codiceVisibilità);
        materiaCorrente.confermaQuesito(v);
    }

    public void loadTutor(){
        Tutor t = new Tutor("Mario", "Rossi", "RSSMRA80A01C351O");
        addTutor(t.getCf(),t);
        //MOMENTANEO per il caso d'uso di avviamento
        t.addMateriaInsegnata(mappaMaterie.get("MAT01"));
        t.addMateriaInsegnata(mappaMaterie.get("ITA02"));
        setTutorAutenticato(t);
    }

    public void loadMaterie(){
        Materia m1 = new Materia("Matematica", "MAT01");
        Materia m2 = new Materia("Italiano", "ITA02");
        addMateria(m1.getCodice(), m1);
        addMateria(m2.getCodice(), m2);
    }

    public void loadVisibilità(){
        Visibilità v1 = new Visibilità("Personale", "p1");
        Visibilità v2 = new Visibilità("Privato", "p2");
        Visibilità v3 = new Visibilità("Pubblico", "p3");
        addVisibilità(v1.getCodice(),v1);
        addVisibilità(v2.getCodice(),v2);
        addVisibilità(v3.getCodice(),v3);
    }

    //MOMENTANEO per il caso d'uso di avviamento
    public void setTutorAutenticato(Tutor tutorAutenticato) {
        this.tutorAutenticato = tutorAutenticato;
    }
}
