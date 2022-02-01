package com.ing_sw_2022.app;

import java.io.Serializable;

public class TemplateUfficiale extends Template implements Serializable {
    protected String fonte;
    protected int tempoTotale;
    private static final long serialVersionUID = 1;

    public TemplateUfficiale(int id, String nome) {
        super(id, nome);
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public int getTempoTotale() {
        return tempoTotale;
    }

    public void setTempoTotale(int tempoTotale) {
        this.tempoTotale = tempoTotale;
    }

    @Override
    public String toString() {
        return "TemplatePersonalizzato{" +"\n"+
                "id=" + super.id +"\n"+
                ", nome='" + super.nome + "\n"+
                ", tempoTotale=" + tempoTotale +"\n"+
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
