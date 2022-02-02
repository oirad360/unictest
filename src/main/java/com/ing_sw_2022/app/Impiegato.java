package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Impiegato extends Utente implements Serializable {
    private static long serialVersionUID = 1;

    public Impiegato(String nome, String cognome, String cf) {
        super(nome, cognome, cf);
    }

    public abstract void addMateriaInsegnata(Materia m);

    public abstract List<Materia> getMaterieInsegnate();

    public abstract Materia getMateriaCorrente();

    //////////////////////////////////////////////METODI DCD//////////////////////////////////////////////
    /////////////////////////////UC7 NUOVO QUESITO///////////////////////////////
    public abstract void nuovoQuesito(String codiceMateria);

    public abstract void inserisciFonte(String fonte);

    public abstract void inserisciTesto(String testo);

    public abstract void inserisciRisposta(String testo, boolean valore);

    public abstract void inserisciDifficoltà(int difficoltà);

    public abstract void confermaQuesito(Visibilità v);

}
