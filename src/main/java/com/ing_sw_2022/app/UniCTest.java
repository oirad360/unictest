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

    private UniCTest() {
        mappaMaterie = new HashMap<>();
        mappaVisibilità = new HashMap<>();
        mappaUtenti = new HashMap<>();
        loadMaterie();
        loadVisibilità();
        loadUtenti();
        serialize();
    }

    public static UniCTest getInstance() {
        if (unictest == null) {
            int res=deserialize();
            if(res==0) unictest = new UniCTest();
            utenteAutenticato = unictest.getMappaUtenti().get("RSSMRA80A01C351O");
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

    public void serialize(){
        try {
            OutputStream fout = new FileOutputStream("ser.txt");
            ObjectOutput oout = new ObjectOutputStream(fout);
            oout.writeObject(this);
            fout.close();
            oout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Integer deserialize(){
        try {
            InputStream fin=new FileInputStream("ser.txt");
            ObjectInput oin=new ObjectInputStream(fin);
            unictest=(UniCTest) oin.readObject();
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

    public void setUtenteAutenticato(String cf){
        utenteAutenticato=getMappaUtenti().get(cf);
    } //MOMENTANEO

    /////////////////////////////////////////////METODI DCD///////////////////////////////////////////////
                     ////////////////////UC7 INSERISCI QUESITO/////////////////////
    public List<Materia> visualizzaMaterieInsegnate(){
        return ((Tutor)utenteAutenticato).getMaterieInsegnate();
    }

    public void nuovoQuesito(String codiceMateria){
        ((Tutor)utenteAutenticato).nuovoQuesito(codiceMateria);
    }

    public void inserisciFonte(String fonte){
        ((Tutor)utenteAutenticato).inserisciFonte(fonte);
    }

    public void inserisciTesto(String testo){
        ((Tutor)utenteAutenticato).inserisciTesto(testo);
    }

    public void inserisciRisposta(String testo, boolean valore){
        ((Tutor)utenteAutenticato).inserisciRisposta(testo, valore);
    }

    public void inserisciDifficoltà(int difficoltà){
        ((Tutor)utenteAutenticato).inserisciDifficoltà(difficoltà);
    }

    public void confermaQuesito(String codiceVisibilità){
        Visibilità v = mappaVisibilità.get(codiceVisibilità);
        ((Tutor)utenteAutenticato).confermaQuesito(v);
    }

                        ////////////////////UC2 CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////

    public void nuovoTemplateP(String nome){
        ((Studente)utenteAutenticato).nuovoTemplateP(nome);
    }

    public List<Materia> inserisciInfoTemplateP(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio){
        ((Studente)utenteAutenticato).inserisciInfoTemplateP(puntiCorretta, puntiErrata, puntiNonData, numRisposte, minRisposteCorrette, maxRisposteCorrette, tempoMedio);
        List<Materia> list = new ArrayList<Materia>(mappaMaterie.values());
        return list;
    }

    public void creaSezioneP(String codiceMateria, int numQuesiti, int difficoltàMedia){
        Materia m=mappaMaterie.get(codiceMateria);
        ((Studente)utenteAutenticato).creaSezioneP(m,numQuesiti,difficoltàMedia);
    }

    public void confermaTemplateP(){
        ((Studente)utenteAutenticato).confermaTemplateP();
    }
                        ////////////////////UC1 AVVIA SIMULAZIONE/////////////////////
    public ArrayList<TemplatePersonalizzato> visualizzaTemplate(){
        ArrayList<TemplatePersonalizzato> lista = ((Studente)utenteAutenticato).visualizzaTemplate();
        return lista;
    }

    public Test avviaSimulazione(int idTemplate) throws Exception{
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
        Materia m1 = new Materia("Matematica", "MAT01");
        Materia m2 = new Materia("Italiano", "ITA02");
        mappaMaterie.put(m1.getCodice(),m1);
        mappaMaterie.put(m2.getCodice(),m2);
    }

    private void loadVisibilità(){
        Visibilità v1 = new Visibilità("Personale", "p1");
        Visibilità v2 = new Visibilità("Privato", "p2");
        Visibilità v3 = new Visibilità("Pubblico", "p3");
        mappaVisibilità.put(v1.getCodice(),v1);
        mappaVisibilità.put(v2.getCodice(),v2);
        mappaVisibilità.put(v3.getCodice(),v3);
    }

    private void loadUtenti(){
        Tutor t = new Tutor("Mario", "Rossi", "RSSMRA80A01C351O");
        Studente s = new Studente("Luigi","Verdi","VRDLGI99R21C351J");
        mappaUtenti.put(t.getCf(),t);
        mappaUtenti.put(s.getCf(),s);
        t.addMateriaInsegnata(mappaMaterie.get("MAT01"));
        t.addMateriaInsegnata(mappaMaterie.get("ITA02"));
    }

}
