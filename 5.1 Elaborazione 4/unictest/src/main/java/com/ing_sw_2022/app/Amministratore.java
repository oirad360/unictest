package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Amministratore extends Decorator implements Serializable{
    private static long serialVersionUID = 1;
    private TemplateUfficiale templateCorrente;

    public Amministratore(Impiegato imp) {
        super(imp);
    }

    public TemplateUfficiale getTemplateCorrente() {
        return templateCorrente;
    }

    @Override
    public String toString() {
        return impiegato.toString()+"\nSono un Amministratore";
    }
    //////////////////////////////////////////////METODI DCD//////////////////////////////////////////////
    /////////////////////////////UC2/A CREA TEMPLATE DI TEST UFFICIALE///////////////////////////////
    public void nuovoTemplateU(String nome){
        String id = "U0";
        TreeMap<String,TemplateUfficiale> mappaTemplateUfficiali = UniCTest.getInstance().getMappaTemplateUfficiali();
        if(!mappaTemplateUfficiali.isEmpty()) id = "U"+String.valueOf(Integer.parseInt(mappaTemplateUfficiali.lastKey().substring(1))+1);
        templateCorrente = new TemplateUfficiale(id, nome);
    }

    public void inserisciInfoTemplateU(String fonte,float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoTotale){
        templateCorrente.setInfoTemplate(puntiCorretta,puntiErrata,puntiNonData,numRisposte,minRisposteCorrette,maxRisposteCorrette);
        templateCorrente.setFonte(fonte);
        templateCorrente.setTempoTotale(tempoTotale);
    }

    public void creaSezioneU(String nomeMateria, int numQuesiti){
        templateCorrente.creaSezione(nomeMateria,numQuesiti);
    }

    public void confermaTemplateU(){
        UniCTest.getInstance().getMappaTemplateUfficiali().put(templateCorrente.getId(),templateCorrente);
        templateCorrente=null;
    }
}
