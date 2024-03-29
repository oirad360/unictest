package com.ing_sw_2022.app;

import com.ing_sw_2022.app.eccezioni.NotEnoughQuestionsException;
import com.ing_sw_2022.app.eccezioni.QuestionNotFoundException;
import com.ing_sw_2022.app.eccezioni.TemplateSectionException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public Sezione(String id, String nomeMateria, int numQuesiti, int difficoltàMedia) {
        //metodo per creare la sezione in cui la materia è considerata un flyweight
        this.numQuesiti = numQuesiti;
        this.difficoltàMedia = difficoltàMedia;
        this.id = id;
        this.materia = UniCTest.getInstance().getMateriaFlyweight(nomeMateria);
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

    @Override
    public String toString() {
        return "Sezione{" +
                "numQuesiti=" + numQuesiti +
                ", difficoltàMedia=" + difficoltàMedia +
                ", id='" + id + '\'' +
                ", materia=" + materia +
                '}';
    }
    ////////////////////////////////////METODI DCD//////////////////////////////////////
    /////////////////////////////UC1 AVVIA SIMULAZIONE//////////////////////////////
    public List<QuesitoDescrizione> generaQuesiti(Template t) throws NotEnoughQuestionsException {
        return materia.generaQuesiti(t,this);//potrebbe lanciare eccezione
    }

    ////////////////////////////UC9 COMPONI TEST PER SIMULAZIONE CARTACEA///////////////////
    public ArrayList<QuesitoDescrizione> visualizzaQuesiti(Template t) throws NotEnoughQuestionsException {
        ArrayList<QuesitoDescrizione> lista = materia.visualizzaQuesiti(t,this);
        return lista;
    }

    public List<QuesitoDescrizione> recuperaQuesiti(List<String> listaIdQuesiti)throws TemplateSectionException, QuestionNotFoundException {
        if(listaIdQuesiti.size()!=numQuesiti) throw new TemplateSectionException("Sono stati inseriti "+listaIdQuesiti.size()+" quesiti ma ne sono richiesti "+numQuesiti);
        return materia.recuperaQuesiti(listaIdQuesiti);
    }
}
