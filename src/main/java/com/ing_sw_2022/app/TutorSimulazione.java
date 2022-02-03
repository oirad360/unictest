package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class TutorSimulazione extends Decorator implements Serializable {
    private static long serialVersionUID = 1;
    private TemplatePersonalizzato templateCorrente;
    private TreeMap<String, TemplatePersonalizzato> mappaTemplatePersonalizzati;

    public TutorSimulazione(Impiegato imp) {
        super(imp);
        mappaTemplatePersonalizzati = new TreeMap<>();
    }

    public TemplatePersonalizzato getTemplatePersonalizzatoCorrente() {
        return templateCorrente;
    }

    public TreeMap<String, TemplatePersonalizzato> getMappaTemplatePersonalizzati() {
        return mappaTemplatePersonalizzati;
    }

    @Override
    public String toString() {
        return impiegato.toString()+"\nSono un Tutor di Simulazione";
    }
    ////////////////////////////////////////////METODI DCD//////////////////////////////////////////////////
                ////////////////////UC2/T CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////
    public void nuovoTemplateP(String nome){
        String id = "0";
        if(!mappaTemplatePersonalizzati.isEmpty()) id = String.valueOf(Integer.parseInt(mappaTemplatePersonalizzati.lastKey())+1);
        templateCorrente = new TemplatePersonalizzato(id, nome);
    }

    public void inserisciInfoTemplateP(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio){
        templateCorrente.setInfoTemplate(puntiCorretta,puntiErrata,puntiNonData,numRisposte,minRisposteCorrette,maxRisposteCorrette);
        templateCorrente.setTempoMedio(tempoMedio);
    }

    public void creaSezioneP(Materia m, int numQuesiti){
        templateCorrente.creaSezione(m,numQuesiti);
    }

    public void confermaTemplateP(){
        mappaTemplatePersonalizzati.put(templateCorrente.getId(),templateCorrente);
        templateCorrente=null;
    }
    //////////////////////UC9 COMPONI TEST PER SIMULAZIONE CARTACEA/////////////////
    @Override
    public ArrayList<TemplatePersonalizzato> visualizzaTemplate() throws Exception{
        ArrayList<TemplatePersonalizzato> lista = new ArrayList<>(mappaTemplatePersonalizzati.values());
        return lista;
    }
}
