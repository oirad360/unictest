package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface Impiegato extends Serializable {
    long serialVersionUID = 1;

    void addMateriaInsegnata(Materia m);

    List<Materia> getMaterieInsegnate();

    Materia getMateriaCorrente();

    //////////////////////////////////////////////METODI DCD//////////////////////////////////////////////
    /////////////////////////////UC7 NUOVO QUESITO///////////////////////////////
    void nuovoQuesito(String codiceMateria);

    void inserisciFonte(String fonte);

    void inserisciTesto(String testo);

    void inserisciRisposta(String testo, boolean valore);

    void inserisciDifficoltà(int difficoltà);

    void confermaQuesito(Visibilità v);
}
