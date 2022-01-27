package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tutor extends Utente implements Serializable{
    private HashMap<String,Materia> materieInsegnate;
    private Materia materiaCorrente;
    private static final long serialVersionUID = 1;

    public Tutor(String nome, String cognome, String cf) {
        super(nome,cognome,cf);
        this.materieInsegnate = new HashMap<String,Materia>();
    }

    public void addMateriaInsegnata(Materia m){
        materieInsegnate.put(m.getCodice(),m);
    }

    public List<Materia> getMaterieInsegnate() {
        return new ArrayList<>(materieInsegnate.values());
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
    public void nuovoQuesito(String codiceMateria){
        materiaCorrente=materieInsegnate.get(codiceMateria);
        materiaCorrente.nuovoQuesito(this);
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

    public void confermaQuesito(Visibilità v){
        materiaCorrente.confermaQuesito(v);
        materiaCorrente = null;
    }
}
