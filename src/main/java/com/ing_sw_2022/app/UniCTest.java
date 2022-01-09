package com.ing_sw_2022.app;

import java.util.HashMap;

public class UniCTest {
    private static UniCTest unictest;
    private HashMap<String,Materia> mappaMaterie;
    private HashMap<String,Visibilità> mappaVisibilità;
    private Materia corrente;

    private UniCTest() {
        this.mappaMaterie = new HashMap<>();
        this.mappaVisibilità = new HashMap<>();
        this.corrente = corrente;
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

    public void setMappaMaterie(HashMap<String, Materia> mappaMaterie) {
        this.mappaMaterie = mappaMaterie;
    }

    public HashMap<String, Visibilità> getMappaVisibilità() {
        return mappaVisibilità;
    }

    public void setMappaVisibilità(HashMap<String, Visibilità> mappaVisibilità) {
        this.mappaVisibilità = mappaVisibilità;
    }

    public Materia getCorrente() {
        return corrente;
    }

    public void setCorrente(Materia corrente) {
        this.corrente = corrente;
    }

    public static UniCTest getInstance() {
        if (unictest == null)
            unictest = new UniCTest();
        else
            System.out.println("Istanza già creata");

        return unictest;
    }


}
