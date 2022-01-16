package com.ing_sw_2022.app;

//import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Materia implements Serializable {
    private String nome;
    private String codice;
    private TreeMap<String,Quesito> mappaQuesiti;
    private Quesito quesitoCorrente;
    private static final long serialVersionUID = 1;

    public Materia(String nome, String codice) {
        this.nome = nome;
        this.codice = codice;
        this.mappaQuesiti = new TreeMap<>();
    }

    public void addQuesito(String id, Quesito q){
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

    public TreeMap<String, Quesito> getMappaQuesiti() {
        return mappaQuesiti;
    }

    public Quesito getQuesitoCorrente() {
        return quesitoCorrente;
    }

    public void nuovoQuesito(Tutor tutorAutenticato){
        //Setting id quesito
        String newId;
        if(mappaQuesiti.isEmpty()) newId = codice+"-0";
        else newId = codice+"-"+(Integer.parseInt(mappaQuesiti.lastKey().split("-")[1])+1);

        Quesito q = new Quesito(newId,tutorAutenticato);
        quesitoCorrente = q; //q diventa corrente per Materia
    }

    public void inserisciFonte(String fonte){
        quesitoCorrente.setFonte(fonte);
    }

    public void inserisciTesto(String testo){
        quesitoCorrente.setTesto(testo);
    }

    public void inserisciRisposta(String testo, boolean valore){
        quesitoCorrente.inserisciRisposta(testo, valore);
    }

    public void inserisciDifficoltà(int difficoltà){
        quesitoCorrente.setDifficoltà(difficoltà);
    }

    public void confermaQuesito(Visibilità v){
        quesitoCorrente.setVisibilità(v);
        String idQuesitoCorrente = quesitoCorrente.getId();
        mappaQuesiti.put(idQuesitoCorrente, quesitoCorrente);
        String[] nomiAttributi= new String[1];
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
        }
        quesitoCorrente = null;
    }

    ///////////////////// TO STRING MOMENTANEO ////////////////////
    @Override
    public String toString() {
        return "Materia{" +
                "nome='" + nome + '\'' +
                ", codice='" + codice + '\'' +
                '}';
    }
}
