package com.ing_sw_2022.app;

//import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Materia implements Serializable {
    private String nome;
    private String codice;
    private TreeMap<String, QuesitoDescrizione> mappaQuesiti;
    private QuesitoDescrizione quesitoDescrizioneCorrente;
    private static final long serialVersionUID = 1;

    public Materia(String nome, String codice) {
        this.nome = nome;
        this.codice = codice;
        this.mappaQuesiti = new TreeMap<>();
    }

    public void addQuesito(String id, QuesitoDescrizione q){
        mappaQuesiti.put(id, q);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Map<String, QuesitoDescrizione> getMappaQuesiti() {
        return mappaQuesiti;
    }

    public QuesitoDescrizione getQuesitoCorrente() {
        return quesitoDescrizioneCorrente;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "nome='" + nome + '\'' +
                ", codice='" + codice + '\'' +
                '}';
    }

    /////////////////////////////////////////////METODI DCD///////////////////////////////////////////////
                        ////////////////////UC7 INSERISCI QUESITO/////////////////////
    public void nuovoQuesito(Tutor t){
        //Setting id quesito
        String newId;
        if(mappaQuesiti.isEmpty()) newId = codice+"-0";
        else newId = codice+"-"+(Integer.parseInt(mappaQuesiti.lastKey().split("-")[1])+1);

        QuesitoDescrizione q = new QuesitoDescrizione(newId,t);
        quesitoDescrizioneCorrente = q; //q diventa corrente per Materia
    }

    public void inserisciFonte(String fonte){
        quesitoDescrizioneCorrente.setFonte(fonte);
    }

    public void inserisciTesto(String testo){
        quesitoDescrizioneCorrente.setTesto(testo);
    }

    public void inserisciRisposta(String testo, boolean valore){
        quesitoDescrizioneCorrente.inserisciRisposta(testo, valore);
    }

    public void inserisciDifficoltà(int difficoltà){
        quesitoDescrizioneCorrente.setDifficoltà(difficoltà);
    }

    public void confermaQuesito(Visibilità v){
        quesitoDescrizioneCorrente.setVisibilità(v);
        String idQuesitoCorrente = quesitoDescrizioneCorrente.getId();
        mappaQuesiti.put(idQuesitoCorrente, quesitoDescrizioneCorrente);
        quesitoDescrizioneCorrente = null;
    }
                 ///////////////////////////UC1 AVVIA SIMULAZIONE//////////////////////
    public List<QuesitoDescrizione> generaQuesiti(Template t, Sezione s) throws Exception{
        int n=s.getNumQuesiti();
        int dim=mappaQuesiti.size();
        if(n>dim) throw new Exception("la materia "+s.getMateria().getNome()+" contiene solo "+dim+" quesiti contro i "+n+" richiesti");

        int difficoltà=s.getDifficoltàMedia();
        int risposte=t.getNumRisposte();
        int maxCorrette=t.getMaxRisposteCorrette();
        int minCorrette=t.getMinRisposteCorrette();
        ArrayList<QuesitoDescrizione> listaQuesiti=new ArrayList<QuesitoDescrizione>();
        ArrayList<Integer> oldIndex= new ArrayList<Integer>();
        ArrayList<QuesitoDescrizione> listaQD= new ArrayList<QuesitoDescrizione>(mappaQuesiti.values());

        while(listaQuesiti.size()<n){
            if(dim-oldIndex.size()<n-listaQuesiti.size()) throw new Exception("non ci sono abbastanza quesiti validi per la materia "+s.getMateria().getNome()+",\n ne sono già stati presi in considerazione "+oldIndex.size()+" su "+dim+" ma solo "+listaQuesiti.size()+" contro i "+n+" richiesti sono validi"); //se il numero di quesiti che ho ancora a disposizione è inferiore al numero di quesiti mancanti lancio un'eccezione
            //generazione numero random
            boolean repeat;
            int randomNum;
            do{
                repeat=false;
                randomNum = ThreadLocalRandom.current().nextInt(0, dim);
                for(Integer i : oldIndex){ //oldIndex contiene gli indici dei quesiti o scartati o già aggiunti
                    if(i==randomNum) {
                        repeat=true;
                        break;
                    }
                }
            } while(repeat); //Se esco sono riuscito a generare un numero random mai ripetuto.
            oldIndex.add(randomNum);
            //pesco il quesito random e ne verifico tutti i requisiti, se li rispetta lo aggiungo alla lista
            QuesitoDescrizione qd=listaQD.get(randomNum);
            boolean error=false;
            if(!qd.getVisibilità().getCodice().equals("p3")) error = true;
            if(!error){
                if(qd.getRisposte().size()!=risposte) error = true; //Conto il numero di risposte
                if(!error){
                    if(difficoltà!=0){
                        if(qd.getDifficoltà()!=difficoltà-1 && qd.getDifficoltà()!=difficoltà && qd.getDifficoltà()!=difficoltà+1) error = true;
                    }
                    if(!error){
                        int countCorrette=0;
                        for(Risposta r: qd.getRisposte().values()) if(r.isValore()) countCorrette++; //Conto il numero di risposte vere
                        if(countCorrette>maxCorrette || countCorrette<minCorrette) error = true;
                        if(!error) listaQuesiti.add(qd);
                    }
                }
            }
        }
        return listaQuesiti;
    }
    ////////////////////////////UC9 COMPONI TEST PER SIMULAZIONE CARTACEA///////////////////
    public ArrayList<QuesitoDescrizione> visualizzaQuesiti(Template t, Sezione s) throws Exception {
        if(s.getNumQuesiti()>mappaQuesiti.size()) throw new Exception("Ci sono solo"+mappaQuesiti.size()+" quesiti per la materia "+s.getMateria().getNome()+", ne servono "+s.getNumQuesiti());
        ArrayList<QuesitoDescrizione> listaQuesiti = new ArrayList<>();
        int risposte=t.getNumRisposte();
        int maxCorrette=t.getMaxRisposteCorrette();
        int minCorrette=t.getMinRisposteCorrette();
        int count=0;
        for(QuesitoDescrizione qd: mappaQuesiti.values()){
            if(mappaQuesiti.size()-count<s.getNumQuesiti()-listaQuesiti.size()) throw new Exception("non ci sono abbastanza quesiti validi per la materia "+s.getMateria().getNome()+",\n ne sono già stati presi in considerazione "+count+" su "+mappaQuesiti.size()+" ma solo "+listaQuesiti.size()+" contro i "+s.getNumQuesiti()+" richiesti sono validi");
            boolean error=false;
            if(!qd.getVisibilità().getCodice().equals("p2")) error = true;
            if(!error){
                if(qd.getRisposte().size()!=risposte) error = true; //Conto il numero di risposte
                if(!error){
                    int countCorrette=0;
                    for(Risposta r: qd.getRisposte().values()) if(r.isValore()) countCorrette++; //Conto il numero di risposte vere
                    if(countCorrette>maxCorrette || countCorrette<minCorrette) error = true;
                    if(!error) listaQuesiti.add(qd);
                }
            }
            count++;
        }
        return listaQuesiti;
    }

    public List<QuesitoDescrizione> recuperaQuesiti(List<String> listaIdQuesiti) throws Exception {
        List<QuesitoDescrizione> listaQuesiti = new ArrayList<>();
        for(String id:listaIdQuesiti){
            QuesitoDescrizione qd=mappaQuesiti.get(id);
            if(qd==null) throw new Exception("Id quesiti errati");
            boolean error=false;

            listaQuesiti.add(qd);
        }
        return listaQuesiti;
    }
}
