package com.ing_sw_2022.app;

import java.io.Serializable;
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
    public boolean isAmministratore(){
        return true;
    }
    @Override
    public boolean isTutorSimulazione(){
        return this.impiegato.isTutorSimulazione();
    }


    @Override
    public String toString() {
        return "Amministratore + "+impiegato.toString();
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
    public Impiegato rendiAmministratore(Impiegato imp){
        if(!imp.isAmministratore()) {
            Impiegato impDecorato = new Amministratore(imp);
            UniCTest.getInstance().getMappaUtenti().replace(imp.getCf(), impDecorato);
            return impDecorato;
        }
        return imp; //Exception
    }

    @Override
    public Impiegato rendiTutorSimulazione(Impiegato imp){
        if(!imp.isTutorSimulazione()){
            Impiegato impDecorato = new TutorSimulazione(imp);
            UniCTest.getInstance().getMappaUtenti().replace(imp.getCf(), impDecorato);
            return impDecorato;
        }
        return imp; //Exception
    }

    @Override
    public Impiegato rimuoviAmministratore(Impiegato imp){
        //int flag=0;
        Impiegato attuale = imp;
        Impiegato precedente = null;
        Impiegato successivo = imp.getImpiegato();   //Struttura:  PREC(ATTUALE(SUCC))
        while(/*flag == 0 &&*/ successivo != null){
            if(attuale instanceof Amministratore){
                if(precedente!=null){
                precedente.setImpiegato(successivo);
                //flag = 1;
                return imp;
                } else {
                    return successivo;
                }
            } else { //Scaliamo tutti al successivo
                precedente = attuale; //Il PREC diventa l'ATT
                attuale = successivo; //L'ATT diventa il SUCC
                successivo = successivo.getImpiegato(); //Il SUCC diventa più SUCC
            }
        } //Interrompo se lo trovo o se non ho più successivi (cioè più interni)! --> Infatti il Tutor restituisce null perché non ha interni.
        System.out.println("Non sei Amministratore, quindi non rimuovo nulla");
        return imp;
    }

    @Override
    public Impiegato rimuoviTutorSimulazione(Impiegato imp){
        //int flag=0;
        Impiegato attuale = imp;
        Impiegato precedente = null;
        Impiegato successivo = imp.getImpiegato();   //Struttura:  PREC(ATTUALE(SUCC))
        while(/*flag == 0 &&*/ successivo != null){
            if(attuale instanceof TutorSimulazione){
                if(precedente!=null){
                    precedente.setImpiegato(successivo);
                    //flag = 1;
                    return imp;
                } else {
                    return successivo;
                }
            } else { //Scaliamo tutti al successivo
                precedente = attuale; //Il PREC diventa l'ATT
                attuale = successivo; //L'ATT diventa il SUCC
                successivo = successivo.getImpiegato(); //Il SUCC diventa più SUCC
            }
        } //Interrompo se lo trovo o se non ho più successivi (cioè più interni)! --> Infatti il Tutor restituisce null perché non ha interni.
        System.out.println("Non sei TutorSimulazione, quindi non rimuovo nulla");
        return imp;
    }
    ///////////////////////UC6 NUOVO TUTOR//////////////////////7
    @Override
    public Impiegato nuovoTutor(String cf, String nome, String cognome) {
        return new Tutor(nome,cognome,cf);
    }
    ///////////////////////UC6 NUOVO STUDENTE//////////////////////7
    @Override
    public Studente nuovoStudente(String cf, String nome, String cognome) {
        return new Studente(nome,cognome,cf);
    }
    ///////////////////////UC AGGIUNGI MATERIA INSEGNATA//////////////////////
    @Override
    public void aggiungiMateriaInsegnata(Impiegato tutor, String nomeMateria) {
        tutor.addMateriaInsegnata(UniCTest.getInstance().getMateriaFlyweight(nomeMateria));
    }
}
