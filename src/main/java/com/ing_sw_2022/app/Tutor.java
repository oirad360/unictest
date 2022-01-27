package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tutor extends Utente implements Serializable{
    private ArrayList<Materia> materieInsegnate;
    private static final long serialVersionUID = 1;

    public Tutor(String nome, String cognome, String cf) {
        super(nome,cognome,cf);
        this.materieInsegnate = new ArrayList<>();
    }


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
