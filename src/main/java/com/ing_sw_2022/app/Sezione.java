package com.ing_sw_2022.app;

import java.io.Serializable;

public class Sezione implements Serializable {
    private int numQuesiti;
    private int difficoltàMedia;
    private String id;
    private Materia materia;
    private static final long serialVersionUID = 1;

    public Sezione(String id, Materia materia, int numQuesiti, int difficoltàMedia) {
        this.numQuesiti = numQuesiti;
        this.difficoltàMedia = difficoltàMedia;
        this.id = id;
        this.materia = materia;
    }

    public int getNumQuesiti() {
        return numQuesiti;
    }

    public void setNumQuesiti(int numQuesiti) {
        this.numQuesiti = numQuesiti;
    }

    public int getDifficoltàMedia() {
        return difficoltàMedia;
    }

    public void setDifficoltàMedia(int difficoltàMedia) {
        this.difficoltàMedia = difficoltàMedia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }


}
