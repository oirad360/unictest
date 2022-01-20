package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tutor extends Utente /*implements Serializable */{
    /*private String nome;
    private String cognome;
    private String cf;*/
    private ArrayList<Materia> materieInsegnate;
    //private static final long serialVersionUID = 1;

    public Tutor(String nome, String cognome, String cf) {
        /*this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;*/
        super(nome,cognome,cf);
        this.materieInsegnate = new ArrayList<>();
    }

    /*public String getNome() {
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
    }*/

    public void addMateriaInsegnata(Materia m){
        materieInsegnate.add(m);
    }

    public List<Materia> getMaterieInsegnate() {
        return materieInsegnate;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "nome='" + super.getNome() + '\'' +
                ", cognome='" + super.getCognome() + '\'' +
                ", cf='" + super.getCf() + '\'' +
                ", materieInsegnate=" + materieInsegnate +
                '}';
    }
}
