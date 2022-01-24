package com.ing_sw_2022.app;

import java.io.*;
import java.util.*;

public class UniCTest implements Serializable{
    private static UniCTest unictest;
    private HashMap<String,Materia> mappaMaterie;
    private HashMap<String,Visibilità> mappaVisibilità;
    private Materia materiaCorrente;
    static private Utente utenteAutenticato;
    private HashMap<String, Utente> mappaUtenti;
    private static final long serialVersionUID = 1;
    //private final String contentDir="content";

    private UniCTest() {
        mappaMaterie = new HashMap<>();
        mappaVisibilità = new HashMap<>();
        mappaUtenti = new HashMap<>();
        loadMaterie();
        loadVisibilità();
        loadUtenti();
    }

    public static UniCTest getInstance() {
        if (unictest == null) {
            int res=deserialize();
            if(res==0){
                unictest = new UniCTest();
                serialize();
            }
            utenteAutenticato = unictest.getMappaUtenti().get("VRDLGI99R21C351J");
            //RSSMRA80A01C351O --> Tutor
            //VRDLGI99R21C351J --> Studente
        }
        return unictest;
    }

    public Map<String, Materia> getMappaMaterie() {
        return mappaMaterie;
    }

    public Map<String, Visibilità> getMappaVisibilità() {
        return mappaVisibilità;
    }

    public Map<String, Utente> getMappaUtenti() {
        return mappaUtenti;
    }

    public Materia getMateriaCorrente() {
        return materiaCorrente;
    }

    public Utente getUtenteAutenticato(){
        return utenteAutenticato;
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
        return ((Tutor) utenteAutenticato).getMaterieInsegnate();
    }

    public void nuovoQuesito(String codiceMateria){
        Materia m = mappaMaterie.get(codiceMateria);
        materiaCorrente = m; //m diventa corrente per UniCTest
        m.nuovoQuesito((Tutor)utenteAutenticato);
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
        ((Studente)utenteAutenticato).nuovoTemplate(nome);
    }

    public List<Materia> inserisciInfoTemplate(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio){
        ((Studente)utenteAutenticato).inserisciInfoTemplate(puntiCorretta, puntiErrata, puntiNonData, numRisposte, minRisposteCorrette, maxRisposteCorrette, tempoMedio);
        List<Materia> list = new ArrayList<Materia>(mappaMaterie.values());
        return list;
    }

    public void creaSezione(String codiceMateria, int numQuesiti, int difficoltàMedia){
        Materia m=mappaMaterie.get(codiceMateria);
        ((Studente)utenteAutenticato).creaSezione(m,numQuesiti,difficoltàMedia);
    }

    public void confermaTemplate(){
        ((Studente)utenteAutenticato).confermaTemplate();
    }
                        ////////////////////UC1 AVVIA SIMULAZIONE/////////////////////
    public ArrayList<TemplatePersonalizzato> visualizzaTemplate(){
        ArrayList<TemplatePersonalizzato> lista = ((Studente)utenteAutenticato).visualizzaTemplate();
        return lista;
    }

    public Test avviaSimulazione(String idTemplate){
        Test t =((Studente)utenteAutenticato).avviaSimulazione(idTemplate);
        return t;
    }

    public void selezionaRisposta(String idQuesitoReale, String idRisposta){
        ((Studente)utenteAutenticato).selezionaRisposta(idQuesitoReale, idRisposta);
    }

    public Test terminaSimulazione(){
        Test t = ((Studente)utenteAutenticato).terminaSimulazione();
        return t;
    }

                        ////////////METODI PER CASO D'USO DI AVVIAMENTO//////////////

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

    private void loadUtenti(){
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
        Studente s = new Studente("Luigi","Verdi","VRDLGI99R21C351J");
        mappaUtenti.put(t.getCf(),t);
        mappaUtenti.put(s.getCf(),s);
        t.addMateriaInsegnata(mappaMaterie.get("MAT01"));
        t.addMateriaInsegnata(mappaMaterie.get("ITA02"));
    }

}
