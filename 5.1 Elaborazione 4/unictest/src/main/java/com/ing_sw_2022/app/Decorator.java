package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.List;

public abstract class Decorator extends Impiegato implements Serializable{
    private static long serialVersionUID = 1;
    protected Impiegato impiegato;

    public Decorator(Impiegato imp){
        super(imp.getNome(), imp.getCognome(), imp.getCf());
        impiegato=imp;
    }

    @Override
    public void addMateriaInsegnata(Materia m){
        impiegato.addMateriaInsegnata(m);
    }

    @Override
    public List<Materia> getMaterieInsegnate(){
        return impiegato.getMaterieInsegnate();
    }

    @Override
    public Materia getMateriaCorrente(){
        return impiegato.getMateriaCorrente();
    }

    @Override
    public String toString(){
        return impiegato.toString();
    }
    //////////////////////////////////////////////METODI DCD//////////////////////////////////////////////
    /////////////////////////////UC7 NUOVO QUESITO///////////////////////////////
    @Override
    public void nuovoQuesito(String codiceMateria){
        impiegato.nuovoQuesito(codiceMateria);
    }

    @Override
    public void inserisciFonte(String fonte){
        impiegato.inserisciFonte(fonte);
    }

    @Override
    public void inserisciTesto(String testo){
        impiegato.inserisciTesto(testo);
    }

    @Override
    public void inserisciRisposta(String testo, boolean valore){
        impiegato.inserisciRisposta(testo,valore);
    }

    @Override
    public void inserisciDifficoltà(int difficoltà){
        impiegato.inserisciDifficoltà(difficoltà);
    }

    @Override
    public void confermaQuesito(Visibilità v){
        impiegato.confermaQuesito(v);
    }
}
