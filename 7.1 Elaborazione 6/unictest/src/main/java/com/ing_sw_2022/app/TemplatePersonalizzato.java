package com.ing_sw_2022.app;

import java.io.Serializable;

public class TemplatePersonalizzato extends Template implements Serializable{
    /*private int id;
    private String nome;
    private int numRisposte;
    private int minRisposteCorrette;
    private int maxRisposteCorrette;
    private float puntiCorretta;
    private float puntiErrata;
    private float puntiNonData;
    private ArrayList<Sezione> listaSezioni;
    private Test testCorrente;
    private TreeMap<String,Test> mappaTest;*/
    private int tempoMedio;
    private static final long serialVersionUID = 1;

    public TemplatePersonalizzato(String id, String nome) {
        super(id,nome);
    }


    public int getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(int tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    /////////////////////////////////////////////METODI DCD///////////////////////////////////////////////


    @Override
    public String toString() {
        return "TemplatePersonalizzato{" +"\n"+
                "id=" + super.id +"\n"+
                ", nome=" + super.nome + "\n"+
                ", tempoMedio=" + tempoMedio +"\n"+
                ", numRisposte=" + super.numRisposte +"\n"+
                ", minRisposteCorrette=" + super.minRisposteCorrette +"\n"+
                ", maxRisposteCorrette=" + super.maxRisposteCorrette +"\n"+
                ", puntiCorretta=" + super.puntiCorretta +"\n"+
                ", puntiErrata=" + super.puntiErrata +"\n"+
                ", puntiNonData=" + super.puntiNonData +"\n"+
                ", listaSezioni=" + super.listaSezioni +"\n"+
                '}';
    }
}
