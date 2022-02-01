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
}
