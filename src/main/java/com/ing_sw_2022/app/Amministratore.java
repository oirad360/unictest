package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Amministratore extends Decorator implements Serializable{
    private static long serialVersionUID = 1;
    private TemplateUfficiale templateCorrente;

    public Amministratore(Impiegato imp) {
        super(imp);
    }

    public TemplateUfficiale getTemplateUfficialeCorrente() {
        return templateCorrente;
    }

    @Override
    public String whoAmI(){
        String chiSonoIo = "Amministratore";
        System.out.print(chiSonoIo + ", ");
        return chiSonoIo+this.impiegato.whoAmI();
    }


    @Override
    public String toString() {
        return impiegato.toString()+"\nSono un Amministratore";
    }

    //////////////////////////////////////////////METODI DCD//////////////////////////////////////////////
    /////////////////////////////UC2/A CREA TEMPLATE DI TEST UFFICIALE///////////////////////////////

    @Override
    public void nuovoTemplateU(String nome){
        String id = "U0";
        TreeMap<String,TemplateUfficiale> mappaTemplateUfficiali = UniCTest.getInstance().getMappaTemplateUfficiali();
        if(!mappaTemplateUfficiali.isEmpty()) id = "U"+ (Integer.parseInt(mappaTemplateUfficiali.lastKey().substring(1)) + 1);
        templateCorrente = new TemplateUfficiale(id, nome);
    }

    @Override
    public void inserisciInfoTemplateU(String fonte,float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoTotale){
        templateCorrente.setInfoTemplate(puntiCorretta,puntiErrata,puntiNonData,numRisposte,minRisposteCorrette,maxRisposteCorrette);
        templateCorrente.setFonte(fonte);
        templateCorrente.setTempoTotale(tempoTotale);
    }

    @Override
    public void creaSezioneU(String nomeMateria, int numQuesiti){
        templateCorrente.creaSezione(nomeMateria,numQuesiti);
    }

    @Override
    public void confermaTemplateU(){
        UniCTest.getInstance().getMappaTemplateUfficiali().put(templateCorrente.getId(),templateCorrente);
        templateCorrente=null;
    }

    @Override
    public void rendiAmministratore(Impiegato imp){
        Impiegato impDecorato = new Amministratore(imp);
        UniCTest unictest = UniCTest.getInstance();
        unictest.getMappaUtenti().replace(imp.getCf(), impDecorato, imp);
    }

    @Override
    public void rendiTutorSimulazione(Impiegato imp){
        Impiegato impDecorato = new TutorSimulazione(imp);
        UniCTest unictest = UniCTest.getInstance();
        unictest.getMappaUtenti().replace(imp.getCf(), impDecorato, imp);
    }

    @Override
    public void rimuoviAmministratore(Impiegato imp){
        int flag=0;
        Impiegato attuale = imp;
        Impiegato precedente = null;
        Impiegato successivo = imp.getImpiegato();   //Struttura:  PREC(ATTUALE(SUCC))
        do{
            if(attuale instanceof Amministratore){
                precedente.setImpiegato(successivo);
                flag = 1;
            } else { //Scaliamo tutti al successivo
                successivo = successivo.getImpiegato(); //Il SUCC diventa più SUCC
                attuale = successivo; //L'ATT diventa il SUCC
                precedente = attuale; //Il PREC diventa l'ATT
            }
        }while(flag == 0 && successivo != null); //Interrompo se lo trovo o se non ho più successivi (cioè più interni)! --> Infatti il Tutor restituisce null perché non ha interni.
    }

    @Override
    public void rimuoviTutorSimulazione(Impiegato imp){
        int flag=0;
        Impiegato attuale = imp;
        Impiegato precedente = null;
        Impiegato successivo = imp.getImpiegato();   //Struttura:  PREC(ATTUALE(SUCC))
        do{
            if(attuale instanceof TutorSimulazione){
                precedente.setImpiegato(successivo);
                flag = 1;
            } else { //Scaliamo tutti al successivo
                successivo = successivo.getImpiegato(); //Il SUCC diventa più SUCC
                attuale = successivo; //L'ATT diventa il SUCC
                precedente = attuale; //Il PREC diventa l'ATT
            }
        }while(flag == 0 && successivo != null); //Interrompo se lo trovo o se non ho più successivi (cioè più interni)! --> Infatti il Tutor restituisce null perché non ha interni.
    }

}
