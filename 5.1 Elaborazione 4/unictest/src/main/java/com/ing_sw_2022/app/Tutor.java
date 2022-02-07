package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tutor extends Impiegato implements Serializable{
    private HashMap<String,Materia> materieInsegnate;
    private Materia materiaCorrente;
    private static long serialVersionUID = 1;

    public Tutor(String nome, String cognome, String cf) {
        super(nome,cognome,cf);
        this.materieInsegnate = new HashMap<String,Materia>();
    }

    @Override
    public void addMateriaInsegnata(Materia m){
        materieInsegnate.put(m.getCodice(),m);
    }

    @Override
    public List<Materia> getMaterieInsegnate() {
        return new ArrayList<>(materieInsegnate.values());
    }

    @Override
    public Materia getMateriaCorrente() {
        return materiaCorrente;
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
    //////////////////////////////////////////////METODI DCD//////////////////////////////////////////////
                /////////////////////////////UC7 NUOVO QUESITO///////////////////////////////
    @Override
    public void nuovoQuesito(String codiceMateria){
        materiaCorrente=materieInsegnate.get(codiceMateria);
        materiaCorrente.nuovoQuesito(this);
    }

    @Override
    public void inserisciFonte(String fonte){
        materiaCorrente.inserisciFonte(fonte);
    }

    @Override
    public void inserisciTesto(String testo){
        materiaCorrente.inserisciTesto(testo);
    }

    @Override
    public void inserisciRisposta(String testo, boolean valore){
        materiaCorrente.inserisciRisposta(testo, valore);
    }

    @Override
    public void inserisciDifficoltà(int difficoltà){
        materiaCorrente.inserisciDifficoltà(difficoltà);
    }

    @Override
    public void confermaQuesito(Visibilità v){
        materiaCorrente.confermaQuesito(v);
        materiaCorrente = null;
    }
}
