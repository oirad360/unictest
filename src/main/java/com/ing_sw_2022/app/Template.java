package com.ing_sw_2022.app;

import com.ing_sw_2022.app.Materia;
import com.ing_sw_2022.app.Sezione;
import com.ing_sw_2022.app.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public abstract class Template implements Serializable, Cloneable {
    protected String id;
    protected String nome;
    protected int numRisposte;
    protected int minRisposteCorrette;
    protected int maxRisposteCorrette;
    protected float puntiCorretta;
    protected float puntiErrata;
    protected float puntiNonData;
    protected ArrayList<Sezione> listaSezioni;
    protected Test testCorrente;
    protected TreeMap<String,Test> mappaTest;
    private static final long serialVersionUID = 1;
    private Sezione sezioneCorrente;

    public Template(String id, String nome) {
        this.id = id;
        this.nome = nome;
        listaSezioni = new ArrayList<Sezione>();
        mappaTest = new TreeMap<String,Test>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumRisposte() {
        return numRisposte;
    }

    public int getMinRisposteCorrette() {
        return minRisposteCorrette;
    }

    public int getMaxRisposteCorrette() {
        return maxRisposteCorrette;
    }

    public float getPuntiCorretta() {
        return puntiCorretta;
    }

    public float getPuntiErrata() {
        return puntiErrata;
    }

    public float getPuntiNonData() {
        return puntiNonData;
    }

    public ArrayList<Sezione> getListaSezioni() {
        return listaSezioni;
    }

    public Test getTestCorrente() {
        return testCorrente;
    }

    public void setInfoTemplate(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette){
        this.puntiCorretta=puntiCorretta;
        this.puntiErrata=puntiErrata;
        this.puntiNonData=puntiNonData;
        this.numRisposte=numRisposte;
        this.minRisposteCorrette=minRisposteCorrette;
        this.maxRisposteCorrette=maxRisposteCorrette;
    }

    public TreeMap<String, Test> getMappaTest() {
        return mappaTest;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    /////////////////////////UC2 CREA TEMPLATE DI TEST PERSONALIZZATO/////////////
    public void creaSezione(Materia materia, int numQuesiti, int difficoltàMedia){
        String newId;
        if(listaSezioni.isEmpty()) newId = id+"-0";
        else newId = id+"-"+(Integer.parseInt(listaSezioni.get(listaSezioni.size()-1).getId().split("-")[1])+1);
        Sezione s = new Sezione(newId, materia, numQuesiti, difficoltàMedia);
        listaSezioni.add(s);
    }
                    /////////////////////////UC2/A CREA TEMPLATE DI TEST UFFICALE////////////////////
    public void creaSezione(String nomeMateria, int numQuesiti){
        String newId;
        if(listaSezioni.isEmpty()) newId = id+"-0";
        else newId = id+"-"+(Integer.parseInt(listaSezioni.get(listaSezioni.size()-1).getId().split("-")[1])+1);
        Sezione s = new Sezione(newId, nomeMateria, numQuesiti, 0);
        listaSezioni.add(s);
    }

                    /////////////////////////UC2/T CREA TEMPLATE DI TEST PERSONALIZZATO////////////////////
    public void creaSezione(Materia m, int numQuesiti){
        String newId;
        if(listaSezioni.isEmpty()) newId = id+"-0";
        else newId = id+"-"+(Integer.parseInt(listaSezioni.get(listaSezioni.size()-1).getId().split("-")[1])+1);
        Sezione s = new Sezione(newId, m, numQuesiti, 0);
        listaSezioni.add(s);
    }

                            ///////////////////////////UC1 AVVIA SIMULAZIONE//////////////////////////
    public Test avviaSimulazione() throws NotEnoughQuestionsException {
        String newId;
        if(mappaTest.isEmpty()) newId = id+"-0";
        else newId = id+"-"+(Integer.parseInt(mappaTest.lastKey().split("-")[1])+1);
        Test t = new Test(newId,this);
        testCorrente=t;
        for(Sezione s:listaSezioni){
            List<QuesitoDescrizione> listaQuesiti=s.generaQuesiti(this); //potrebbe lanciare eccezione
            t.inserisciQuesiti(listaQuesiti);
        }
        return t;
    }

    public void selezionaRisposta(String idQuesitoReale, String idRisposta){
        testCorrente.selezionaRisposta(idQuesitoReale, idRisposta);
    }

    public Test terminaSimulazione(){
        testCorrente.terminaSimulazione();
        mappaTest.put(testCorrente.getId(), testCorrente);
        return testCorrente;
    }
    ////////////////////////////UC9 COMPONI TEST PER SIMULAZIONE CARTACEA///////////////////
    public List<Sezione> creaTestCartaceo() {
        String newId;
        if(mappaTest.isEmpty()) newId = id+"-0";
        else newId = id+"-"+(Integer.parseInt(mappaTest.lastKey().split("-")[1])+1);
        Test t = new Test(newId,this);
        testCorrente = t;
        List<Sezione> lista=new ArrayList<>();
        for(Sezione s : listaSezioni){
            lista.add(s);
        }
        return lista;
    }

    public ArrayList<QuesitoDescrizione> visualizzaQuesiti(String idSezione) throws NotEnoughQuestionsException {
        //Ricerca sezione
        sezioneCorrente=null;
        for (Sezione s: listaSezioni) {
            if(s.getId().equals(idSezione)) {
                sezioneCorrente = s; //Poi la sezioneCorrente va svuotata
                break;
            }
        }
        if(sezioneCorrente!=null){
            ArrayList<QuesitoDescrizione> lista = sezioneCorrente.visualizzaQuesiti(this);
            return lista;
        }
        return null;
    }

    public void inserisciQuesiti(List<String> listaIdQuesiti) throws QuestionNotFoundException, TemplateSectionException {
        List<QuesitoDescrizione> listaQuesiti=sezioneCorrente.recuperaQuesiti(listaIdQuesiti);
        testCorrente.inserisciQuesiti(listaQuesiti);
    }

    public void stampaTest(String nomeFile){
        testCorrente.stampaTest(nomeFile);
        mappaTest.put(testCorrente.getId(), testCorrente);
        testCorrente=null;
    }
}
