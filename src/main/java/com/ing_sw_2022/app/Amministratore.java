package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.List;

public class Amministratore extends Decorator implements Serializable{
    private static long serialVersionUID = 1;

    public Amministratore(Impiegato imp) {
        super(imp);
    }

    @Override
    public String toString() {
        return impiegato.toString()+"\nSono un Amministratore";
    }
}
