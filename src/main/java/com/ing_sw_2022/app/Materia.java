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
    public void nuovoQuesito(Tutor tutorAutenticato){
        //Setting id quesito
        String newId;
        if(mappaQuesiti.isEmpty()) newId = codice+"-0";
        else newId = codice+"-"+(Integer.parseInt(mappaQuesiti.lastKey().split("-")[1])+1);

        QuesitoDescrizione q = new QuesitoDescrizione(newId,tutorAutenticato);
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
        /*String[] nomiAttributi= new String[1];
        String fileName=UniCTest.getInstance().getContentDir()+File.separator+"Quesiti-"+codice+".txt";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            nomiAttributi = bufferedReader.readLine().split(" ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName,true))) {
            String fileContent = "";
            for(int i=0;i<nomiAttributi.length;i++){
                String end = "~";
                if(i==nomiAttributi.length-1) end = "\n";
                switch (nomiAttributi[i]){
                    case "id":
                        fileContent=fileContent+quesitoCorrente.getId()+end;
                        break;
                    case "difficoltà":
                        fileContent=fileContent+quesitoCorrente.getDifficoltà()+end;
                        break;
                    case "testo":
                        fileContent=fileContent+quesitoCorrente.getTesto()+end;
                        break;
                    case "fonte":
                        fileContent=fileContent+quesitoCorrente.getFonte()+end;
                        break;
                    case "tutor":
                        fileContent=fileContent+quesitoCorrente.getTutor().getCf()+end;
                        break;
                    case "visibilità":
                        fileContent=fileContent+quesitoCorrente.getVisibilità().getCodice()+end;
                        break;
                }
            }
            bufferedWriter.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        quesitoDescrizioneCorrente = null;
    }
                 ///////////////////////////UC1 AVVIA SIMULAZIONE//////////////////////
    public List<QuesitoDescrizione> generaQuesiti(TemplatePersonalizzato tp, Sezione s) throws Exception{
        int n=s.getNumQuesiti();
        int difficoltà=s.getDifficoltàMedia();
        int risposte=tp.getNumRisposte();
        int maxCorrette=tp.getMaxRisposteCorrette();
        int minCorrette=tp.getMinRisposteCorrette();
        int dim=mappaQuesiti.size();
        ArrayList<QuesitoDescrizione> listaQuesiti=new ArrayList<QuesitoDescrizione>();
        ArrayList<Integer> oldIndex= new ArrayList<Integer>();
        ArrayList<QuesitoDescrizione> listaQD= new ArrayList<QuesitoDescrizione>(mappaQuesiti.values());
        if(n>dim) { throw new Exception(); }
        while(listaQuesiti.size()<n){
            //generazione numero random
            int lastIndex;
            boolean repeat=false;
            int randomNum;
            do{
                randomNum = ThreadLocalRandom.current().nextInt(0, dim);
                for(Integer i : oldIndex){
                    if(i==randomNum) repeat=true;
                }
            } while(repeat); //Se esco sono riuscito a generare un numero random mai ripetuto.
            oldIndex.add(randomNum);
            //pesco il quesito random e ne verifico tutti i requisiti, se li rispetta lo aggiungo alla lista
            QuesitoDescrizione qd=listaQD.get(randomNum);
            boolean error=false;
            if(qd.getRisposte().size()!=risposte) error=true; //Conto il numero di risposte
            int countCorrette=0;
            for(Risposta r: qd.getRisposte().values()) if(r.isValore()) countCorrette++; //Conto il numero di risposte vere
            if(countCorrette>maxCorrette || countCorrette<minCorrette) error=true;
            if(qd.getDifficoltà()!=difficoltà-1 && qd.getDifficoltà()!=difficoltà && qd.getDifficoltà()!=difficoltà+1) error=true;
            if(!error) listaQuesiti.add(qd);
        }
        return listaQuesiti;
    }
}
