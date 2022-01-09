package com.ing_sw_2022.app;

import java.util.ArrayList;

public class Tutor {
    private String nome;
    private String cognome;
    private String cf;
    private ArrayList<Materia> materieInsegnate;

    public Tutor(String nome, String cognome, String cf) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.materieInsegnate = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public void addMateriaInsegnata(Materia m){
        materieInsegnate.add(m);
    }

    public ArrayList<Materia> getMaterieInsegnate() {
        return materieInsegnate;
    }

}
