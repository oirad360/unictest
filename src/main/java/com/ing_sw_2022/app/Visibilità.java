package com.ing_sw_2022.app;

import java.io.Serializable;

public class Visibilità implements Serializable {
    private String nome;
    private String codice;
    private static final long serialVersionUID = 1;

    public Visibilità(String nome, String codice) {
        this.nome = nome;
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    ////////////////////////TOSTRING MOMENTANEO////////////////////
    @Override
    public String toString() {
        return "Visibilità{" +
                "nome='" + nome + '\'' +
                ", codice='" + codice + '\'' +
                '}';
    }
}
