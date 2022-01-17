package com.ing_sw_2022.app;

import java.io.*;
import java.util.*;

public class UniCTest implements Serializable{
    private static UniCTest unictest;
    private HashMap<String,Materia> mappaMaterie;
    private HashMap<String,Visibilità> mappaVisibilità;
    private Materia materiaCorrente;
    private Tutor tutorAutenticato;
    private Studente studenteAutenticato;
    private HashMap<String, Tutor> mappaTutor;
    private HashMap<String, Studente> mappaStudenti;
    private static final long serialVersionUID = 1;
    private final String contentDir="content";

    private UniCTest() {
        mappaMaterie = new HashMap<>();
        mappaVisibilità = new HashMap<>();
        mappaTutor = new HashMap<>();
        mappaStudenti = new HashMap<>();
        loadMaterie();
        loadTutor();
        loadVisibilità();
        loadStudenti();
    }

    public static UniCTest getInstance() {
        if (unictest == null) {
            int res=deserialize();
            if(res==0){
                unictest = new UniCTest();
                serialize();
            }
        }
        return unictest;
    }

    public void addMateria(String codice, Materia m){
        mappaMaterie.put(codice, m);
    }

    public void addVisibilità(String codice, Visibilità v ){
        mappaVisibilità.put(codice, v);
    }

    public void addStudente(String cf, Studente s){
        mappaStudenti.put(cf,s);
    }

    public void addTutor(String cf, Tutor t ){
        mappaTutor.put(cf, t);
    }

    public HashMap<String, Materia> getMappaMaterie() {
        return mappaMaterie;
    }

    public HashMap<String, Visibilità> getMappaVisibilità() {
        return mappaVisibilità;
    }

    public Materia getMateriaCorrente() {
        return materiaCorrente;
    }

    public HashMap<String, Studente> getMappaStudenti(){
        return mappaStudenti;
    }

    public String getContentDir(){
        return contentDir;
    }
    //Metodi nuovi
    public List visualizzaMaterieInsegnate(){
        return tutorAutenticato.getMaterieInsegnate();
    }

    public void nuovoQuesito(String codiceMateria){
        Materia m = mappaMaterie.get(codiceMateria);
        materiaCorrente = m; //m diventa corrente per UniCTest
        m.nuovoQuesito(tutorAutenticato);
    }

    public void inserisciFonte(String fonte){
        materiaCorrente.inserisciFonte(fonte);
    }

    public void inserisciTesto(String testo){
        materiaCorrente.inserisciTesto(testo);
    }

    public void inserisciRisposta(String testo, boolean valore){
        materiaCorrente.inserisciRisposta(testo, valore);
    }

    public void inserisciDifficoltà(int difficoltà){
        materiaCorrente.inserisciDifficoltà(difficoltà);
    }

    public void confermaQuesito(String codiceVisibilità){
        Visibilità v = mappaVisibilità.get(codiceVisibilità);
        materiaCorrente.confermaQuesito(v);
        materiaCorrente = null;
        Set<String> idMaterie = (Set<String>) mappaMaterie.keySet();
        for (String key: idMaterie) System.out.println(mappaMaterie.get(key).getMappaQuesiti());
        unictest.serialize();
    }

    public void loadTutor(){
        /*try(BufferedReader bufferedReader = new BufferedReader(new FileReader(contentDir+File.separator+"Tutor.txt"))) {
            String[] nomiAttributi = bufferedReader.readLine().split(" ");
            String line= bufferedReader.readLine();
            String nome = "";
            String cognome = "";
            String cf = "";
            String materieInsegnate="";
            Boolean flag = true;
            while(line != null) {
                String[] val = line.split("~");
                for(int i = 0 ; i<4; i++){
                    switch(nomiAttributi[i]){
                        case "nome": nome = val[i];
                            break;
                        case "cognome": cognome = val[i];
                            break;
                        case "cf": cf = val[i];
                            break;
                        case "materieInsegnate": materieInsegnate = val[i];
                        break;
                    }
                }
                Tutor t = new Tutor(nome,cognome,cf);
                addTutor(t.getCf(),t);
                val = materieInsegnate.split(",");
                for(int i = 0; i<val.length; i++){
                    t.addMateriaInsegnata(mappaMaterie.get(val[i]));
                }
                if(flag) setTutorAutenticato(t);//MOMENTANEO
                flag = false;
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Tutor t = new Tutor("Mario", "Rossi", "RSSMRA80A01C351O");
        addTutor(t.getCf(),t);
        //MOMENTANEO per il caso d'uso di avviamento
        t.addMateriaInsegnata(mappaMaterie.get("MAT01"));
        t.addMateriaInsegnata(mappaMaterie.get("ITA02"));
        setTutorAutenticato(t);
    }

    public void loadMaterie(){
        /*try(BufferedReader bufferedReader = new BufferedReader(new FileReader(contentDir+File.separator+"Materie.txt"))) {
            String[] nomiAttributi = bufferedReader.readLine().split(" ");
            String line= bufferedReader.readLine();
            String nome = "";
            String codice = "";
            while(line != null) {
                String[] val = line.split("~");
                for(int i = 0 ; i<2; i++){
                    switch(nomiAttributi[i]){
                        case "nome": nome = val[i];
                            break;
                        case "codice": codice = val[i];
                            break;
                    }
                }
                Materia m = new Materia(nome,codice);
                addMateria(m.getCodice(),m);
                String nomeFile=contentDir+File.separator+"Quesiti-"+m.getCodice()+".txt";
                File file = new File(nomeFile);
                if(file.createNewFile()) {
                    //se il file è stato creato adesso per la prima volta allora scrivo i nomi degli attributi
                    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(nomeFile))) {
                        bufferedWriter.write("id testo difficoltà fonte tutor visibilità\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Materia m1 = new Materia("Matematica", "MAT01");
        Materia m2 = new Materia("Italiano", "ITA02");
        addMateria(m1.getCodice(), m1);
        addMateria(m2.getCodice(), m2);
    }

    public void loadVisibilità(){
        /*try(BufferedReader bufferedReader = new BufferedReader(new FileReader(contentDir+File.separator+"Visibilità.txt"))) {
            String[] nomiAttributi = bufferedReader.readLine().split(" ");
            String line= bufferedReader.readLine();
            String nome = "";
            String codice = "";
            while(line != null) {
                String[] val = line.split("~");
                for(int i = 0 ; i<2; i++){
                    switch(nomiAttributi[i]){
                        case "nome": nome = val[i];
                            break;
                        case "codice": codice = val[i];
                            break;
                    }
                }
                Visibilità v = new Visibilità(nome,codice);
                addVisibilità(v.getCodice(),v);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Visibilità v1 = new Visibilità("Personale", "p1");
        Visibilità v2 = new Visibilità("Privato", "p2");
        Visibilità v3 = new Visibilità("Pubblico", "p3");
        addVisibilità(v1.getCodice(),v1);
        addVisibilità(v2.getCodice(),v2);
        addVisibilità(v3.getCodice(),v3);
    }

    public void loadStudenti(){
        Studente s = new Studente("Luigi","Verdi","VRDLGI99R21C351J");
    }

    public static void serialize(){
         try {
             OutputStream fout = new FileOutputStream("ser.txt");
             ObjectOutput oout = new ObjectOutputStream(fout);
             System.out.println("Serialization process has started, serializing objects...");
             oout.writeObject(unictest);
             fout.close();
             oout.close();
             System.out.println("Object Serialization completed.");
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    private static Integer deserialize(){
         try {
             //DeSerialization process >
             InputStream fin=new FileInputStream("ser.txt");
             ObjectInput oin=new ObjectInputStream(fin);
             System.out.println("\nDeSerialization process has started, displaying objects...");
             unictest=(UniCTest) oin.readObject();
             System.out.println(unictest);
             fin.close();
             oin.close();
             System.out.println("Object DeSerialization completed.");
             return 1;
         } catch (FileNotFoundException e) {
             //e.printStackTrace();
             return 0;
         } catch (IOException e) {
             e.printStackTrace();
             System.out.println("errore IO nella deserializzazione");
             return -1;
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
             System.out.println("errore ClassNotFound nella deserializzazione");
             return -1;
         }
    }
    //MOMENTANEO per il caso d'uso di avviamento
    public void setTutorAutenticato(Tutor tutorAutenticato) {
        this.tutorAutenticato = tutorAutenticato;
    }

    public void setStudenteAutenticato(Studente studenteAutenticato) {
        this.studenteAutenticato = studenteAutenticato;
    }

    public HashMap<String,Materia> nuovoTemplate(String nome){
        studenteAutenticato.nuovoTemplate(nome);
        return mappaMaterie;
    }

    public void inserisciInfoTemplate(int puntiCorretta, int puntiErrata, int puntiNonData, int numRisposte, int numRisposteCorrette, int tempoMedio){
        studenteAutenticato.inserisciInfoTemplate(puntiCorretta, puntiErrata, puntiNonData, numRisposte, numRisposteCorrette, tempoMedio);
    }

    public void creaSezione(String codiceMateria, int numQuesiti, int difficoltàMedia){
        Materia m=mappaMaterie.get(codiceMateria);
        studenteAutenticato.creaSezione(m,numQuesiti,difficoltàMedia);
    }

    public void confermaTemplate(){
        studenteAutenticato.confermaTemplate();
    }
}
