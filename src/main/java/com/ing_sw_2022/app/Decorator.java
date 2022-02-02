package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.List;

public abstract class Decorator implements Impiegato, Serializable {

    protected Impiegato impiegato;

    public Decorator(Impiegato imp){
        impiegato=imp;
    }
    public void addMateriaInsegnata(Materia m){
        impiegato.addMateriaInsegnata(m);
    }

    public List<Materia> getMaterieInsegnate(){
        return impiegato.getMaterieInsegnate();
    }

    public Materia getMateriaCorrente(){
        return impiegato.getMateriaCorrente();
    }

    @Override
    public String toString(){
        return impiegato.toString();
    }
    //////////////////////////////////////////////METODI DCD//////////////////////////////////////////////
    /////////////////////////////UC7 NUOVO QUESITO///////////////////////////////
    public void nuovoQuesito(String codiceMateria){
        impiegato.nuovoQuesito(codiceMateria);
    }

    public void inserisciFonte(String fonte){
        impiegato.inserisciFonte(fonte);
    }

    public void inserisciTesto(String testo){
        impiegato.inserisciTesto(testo);
    }

    public void inserisciRisposta(String testo, boolean valore){
        impiegato.inserisciRisposta(testo,valore);
    }

    public void inserisciDifficoltà(int difficoltà){
        impiegato.inserisciDifficoltà(difficoltà);
    }

    public void confermaQuesito(Visibilità v){
        impiegato.confermaQuesito(v);
    }
}
