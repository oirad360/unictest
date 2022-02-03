package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public abstract class Decorator extends Impiegato implements Serializable{
    private static long serialVersionUID = 1;
    protected Impiegato impiegato;

    public Decorator(Impiegato imp){
        super(imp.getNome(), imp.getCognome(), imp.getCf());
        impiegato=imp;
    }

    @Override
    public TemplatePersonalizzato getTemplatePersonalizzatoCorrente() throws Exception {
        return impiegato.getTemplatePersonalizzatoCorrente();
    }

    @Override
    public TemplateUfficiale getTemplateUfficialeCorrente() throws Exception {
        return impiegato.getTemplateUfficialeCorrente();
    }

    @Override
    public TreeMap<String, TemplatePersonalizzato> getMappaTemplatePersonalizzati() throws Exception{
        return impiegato.getMappaTemplatePersonalizzati();
    }

    @Override
    public void addMateriaInsegnata(Materia m){
        impiegato.addMateriaInsegnata(m);
    }

    @Override
    public List<Materia> getMaterieInsegnate(){
        return impiegato.getMaterieInsegnate();
    }

    @Override
    public Materia getMateriaCorrente(){
        return impiegato.getMateriaCorrente();
    }

    /*@Override
    public String toString(){
        return impiegato.toString();
    }*/
    //////////////////////////////////////////////METODI DCD//////////////////////////////////////////////
    /*
    * ALGORITMO DI FUNZIONAMENTO: RESPONSABILITA' IN CASCATA
    * Chi non reimplementa un metodo del Decorator non lo fa perché non è in grado di soddisfare la richiesta.
    * Quindi, se non riesco a soddisfare la richiesta, rinvio la responsabilità a cascata sul Decorator che ho all'interno.
    * Se nessuno riesce a soddisfare la richiesta, essa arriverà al Tutor. Se questi non riesce a sua volta a soddisfare la richiesta, allora viene lanciata (e gestita) un'eccezione
    * L'eccezione indica che qualcuno ha chiamato un metodo pur non avendo le responsabilità adeguate per farlo.
    * */
    /////////////////////////////UC7 NUOVO QUESITO///////////////////////////////
    @Override
    public void nuovoQuesito(String codiceMateria){
        impiegato.nuovoQuesito(codiceMateria);
    }

    @Override
    public void inserisciFonte(String fonte){
        impiegato.inserisciFonte(fonte);
    }

    @Override
    public void inserisciTesto(String testo){
        impiegato.inserisciTesto(testo);
    }

    @Override
    public void inserisciRisposta(String testo, boolean valore){
        impiegato.inserisciRisposta(testo,valore);
    }

    @Override
    public void inserisciDifficoltà(int difficoltà){
        impiegato.inserisciDifficoltà(difficoltà);
    }

    @Override
    public void confermaQuesito(Visibilità v){
        impiegato.confermaQuesito(v);
    }
    /////////////////////////////UC2/A CREA TEMPLATE DI TEST UFFICIALE///////////////////////////////
    public void nuovoTemplateU(String nome) throws Exception {
        impiegato.nuovoTemplateU(nome);
    }

    public void inserisciInfoTemplateU(String fonte,float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoTotale) throws Exception {
        impiegato.inserisciInfoTemplateU(fonte, puntiCorretta, puntiErrata, puntiNonData, numRisposte, minRisposteCorrette, maxRisposteCorrette, tempoTotale);
    }

    public void creaSezioneU(String nomeMateria, int numQuesiti) throws Exception {
        impiegato.creaSezioneU(nomeMateria,numQuesiti);
    }

    public void confermaTemplateU() throws Exception {
        impiegato.confermaTemplateU();
    }
    ////////////////////UC2/T CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////
    public void nuovoTemplateP(String nome) throws Exception {
        impiegato.nuovoTemplateP(nome);
    }

    public void inserisciInfoTemplateP(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio) throws Exception {
        impiegato.inserisciInfoTemplateP(puntiCorretta, puntiErrata, puntiNonData, numRisposte, minRisposteCorrette, maxRisposteCorrette, tempoMedio);
    }

    public void creaSezioneP(Materia m, int numQuesiti) throws Exception {
        impiegato.creaSezioneP(m, numQuesiti);
    }

    public void confermaTemplateP() throws Exception {
        impiegato.confermaTemplateP();
    }
    //////////////////////UC9 COMPONI TEST PER SIMULAZIONE CARTACEA/////////////////
    @Override
    public ArrayList<TemplatePersonalizzato> visualizzaTemplate() throws Exception{
        return impiegato.visualizzaTemplate();
    }
}
