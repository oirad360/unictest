package com.ing_sw_2022.app;

public class Risposta {
    private String testo;
    private boolean valore;
    private String id;

    public Risposta(String testo, boolean valore, String id) {
        this.testo = testo;
        this.valore = valore;
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public boolean isValore() {
        return valore;
    }

    public void setValore(boolean valore) {
        this.valore = valore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    ////////////////////////TOSTRING MOMENTANEO////////////////////
    @Override
    public String toString() {
        return "Risposta{" +
                "testo='" + testo + "'\n" +
                "valore='" + valore + "'\n" +
                "id='" + id + "'\n" +
                "}\n";
    }
}
