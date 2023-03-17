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
    //private final String contentDir="content";

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

    public Map<String, Materia> getMappaMaterie() {
        return mappaMaterie;
    }

    public Map<String, Visibilità> getMappaVisibilità() {
        return mappaVisibilità;
    }

    public Materia getMateriaCorrente() {
        return materiaCorrente;
    }

    public Map<String, Studente> getMappaStudenti(){
        return mappaStudenti;
    }

    public Tutor getTutorAutenticato() {
        return tutorAutenticato;
    }

    public Studente getStudenteAutenticato() {
        return studenteAutenticato;
    }

    /*public String getContentDir(){
        return contentDir;
    }*/

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

    /////////////////////////////////////////////METODI DCD///////////////////////////////////////////////
                     ////////////////////UC7 INSERISCI QUESITO/////////////////////
    public List<Materia> visualizzaMaterieInsegnate(){
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
        for(Materia m: mappaMaterie.values()){
            System.out.println(m.getMappaQuesiti());
        }
        unictest.serialize();
    }

                        ////////////////////UC2 CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////

    public void nuovoTemplate(String nome){
        studenteAutenticato.nuovoTemplate(nome);
    }

    public List<Materia> inserisciInfoTemplate(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio){
        studenteAutenticato.inserisciInfoTemplate(puntiCorretta, puntiErrata, puntiNonData, numRisposte, minRisposteCorrette, maxRisposteCorrette, tempoMedio);
        List<Materia> list = new ArrayList<Materia>(mappaMaterie.values());
        return list;
    }

    public void creaSezione(String codiceMateria, int numQuesiti, int difficoltàMedia){
        Materia m=mappaMaterie.get(codiceMateria);
        studenteAutenticato.creaSezione(m,numQuesiti,difficoltàMedia);
    }

    public void confermaTemplate(){
        studenteAutenticato.confermaTemplate();
    }

                        ////////////METODI PER CASO D'USO DI AVVIAMENTO//////////////

    private void loadTutor(){
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
        mappaTutor.put(t.getCf(),t);
        //MOMENTANEO per il caso d'uso di avviamento
        t.addMateriaInsegnata(mappaMaterie.get("MAT01"));
        t.addMateriaInsegnata(mappaMaterie.get("ITA02"));
        setTutorAutenticato(t);
    }

    private void loadMaterie(){
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
        mappaMaterie.put(m1.getCodice(),m1);
        mappaMaterie.put(m2.getCodice(),m2);
    }

    private void loadVisibilità(){
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
        mappaVisibilità.put(v1.getCodice(),v1);
        mappaVisibilità.put(v2.getCodice(),v2);
        mappaVisibilità.put(v3.getCodice(),v3);
    }

    private void loadStudenti(){
        Studente s = new Studente("Luigi","Verdi","VRDLGI99R21C351J");
        setStudenteAutenticato(s);
    }

    private void setTutorAutenticato(Tutor tutorAutenticato) {
        this.tutorAutenticato = tutorAutenticato;
    }//MOMENTANEO per il caso d'uso di avviamento

    private void setStudenteAutenticato(Studente studenteAutenticato) {
        this.studenteAutenticato = studenteAutenticato;
    }//MOMENTANEO per il caso d'uso di avviamento
}