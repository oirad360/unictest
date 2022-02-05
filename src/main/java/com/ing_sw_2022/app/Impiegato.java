package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Impiegato extends Utente implements Serializable {
    private static long serialVersionUID = 1;

    public Impiegato(String nome, String cognome, String cf) {
        super(nome, cognome, cf);
    }

    public abstract void addMateriaInsegnata(Materia m);

    public abstract List<Materia> getMaterieInsegnate();

    public abstract Materia getMateriaCorrente();

    public abstract TemplatePersonalizzato getTemplatePersonalizzatoCorrente() throws Exception;

    public abstract TemplateUfficiale getTemplateUfficialeCorrente() throws Exception;

    public abstract TreeMap<String, TemplatePersonalizzato> getMappaTemplatePersonalizzati() throws Exception;

    public abstract TreeMap<String, Template> getMappaTemplateTestScritti() throws Exception;
    //////////////////////////////////////////////METODI DCD//////////////////////////////////////////////
    /////////////////////////////UC7 NUOVO QUESITO///////////////////////////////
    public abstract void nuovoQuesito(String codiceMateria);

    public abstract void inserisciFonte(String fonte);

    public abstract void inserisciTesto(String testo);

    public abstract void inserisciRisposta(String testo, boolean valore);

    public abstract void inserisciDifficoltà(int difficoltà);

    public abstract void confermaQuesito(Visibilità v);

    /////////////////////////////UC2/A CREA TEMPLATE DI TEST UFFICIALE///////////////////////////////
    public abstract void nuovoTemplateU(String nome) throws Exception;

    public abstract void inserisciInfoTemplateU(String fonte,float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoTotale) throws Exception;

    public abstract void creaSezioneU(String nomeMateria, int numQuesiti) throws Exception;

    public abstract void confermaTemplateU() throws Exception;
    ////////////////////UC2/T CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////
    public abstract void nuovoTemplateP(String nome) throws Exception;

    public abstract void inserisciInfoTemplateP(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio) throws Exception;

    public abstract void creaSezioneP(Materia m, int numQuesiti) throws Exception;

    public abstract void confermaTemplateP() throws Exception;
    /////////////////////////////UC9 COMPONI TEST PER SIMULAZIONE CARTACEA/////////////////////
    public abstract ArrayList<TemplatePersonalizzato> visualizzaTemplate() throws Exception;

    public abstract List<Sezione> creaTestCartaceo(String idTemplate) throws Exception;

    public abstract ArrayList<QuesitoDescrizione> visualizzaQuesiti(String idSezione) throws Exception;

    public abstract void inserisciQuesiti(List<String> listaIdQuesiti) throws Exception;

    public abstract void stampaTest(String nomeFile) throws Exception;
    ////////////////////////////UC10 CORREGGI SIMULAZIONI CARTACEO////////////////////////
    public abstract Map<String,String> recuperaInfoTestCartaceo(String fileName) throws Exception;

    public abstract Test correggiTestCartaceo(String cfStudente, String cfTutor, String idTest) throws Exception;

    public abstract void confermaCorrezione() throws Exception;
}
