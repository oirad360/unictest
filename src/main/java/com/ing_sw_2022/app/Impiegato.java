package com.ing_sw_2022.app;

import com.ing_sw_2022.app.eccezioni.NotEnoughQuestionsException;
import com.ing_sw_2022.app.eccezioni.QuestionNotFoundException;
import com.ing_sw_2022.app.eccezioni.TemplateSectionException;

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

    public abstract Impiegato getImpiegato();

    public abstract Impiegato setImpiegato(Impiegato impiegato);

    public abstract String whoAmI();
    public abstract boolean isAmministratore();
    public abstract boolean isTutorSimulazione();

    public abstract Impiegato rendiAmministratore(Impiegato imp);
    public abstract Impiegato rendiTutorSimulazione(Impiegato imp);
    public abstract Impiegato rimuoviAmministratore(Impiegato imp);
    public abstract Impiegato rimuoviTutorSimulazione(Impiegato imp);

    public abstract void addMateriaInsegnata(Materia m);

    public abstract List<Materia> getMaterieInsegnate();

    public abstract Materia getMateriaCorrente();

    public abstract TemplatePersonalizzato getTemplatePersonalizzatoCorrente() throws NotAllowedException;

    public abstract TemplateUfficiale getTemplateUfficialeCorrente() throws NotAllowedException;

    public abstract TreeMap<String, TemplatePersonalizzato> getMappaTemplatePersonalizzati() throws NotAllowedException;

    public abstract TreeMap<String, Template> getMappaTemplateTestScritti() throws NotAllowedException;
    //////////////////////////////////////////////METODI DCD//////////////////////////////////////////////
    /////////////////////////////UC7 NUOVO QUESITO///////////////////////////////
    public abstract void nuovoQuesito(String codiceMateria);

    public abstract void inserisciFonte(String fonte);

    public abstract void inserisciTesto(String testo);

    public abstract void inserisciRisposta(String testo, boolean valore);

    public abstract void inserisciDifficoltà(int difficoltà);

    public abstract void confermaQuesito(Visibilità v);

    /////////////////////////////UC2/A CREA TEMPLATE DI TEST UFFICIALE///////////////////////////////
    public abstract void nuovoTemplateU(String nome) throws NotAllowedException;

    public abstract void inserisciInfoTemplateU(String fonte,float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoTotale) throws NotAllowedException;

    public abstract void creaSezioneU(String nomeMateria, int numQuesiti) throws NotAllowedException;

    public abstract void confermaTemplateU() throws NotAllowedException;
    ////////////////////UC2/T CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////
    public abstract void nuovoTemplateP(String nome) throws NotAllowedException;

    public abstract void inserisciInfoTemplateP(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio) throws NotAllowedException;

    public abstract void creaSezioneP(Materia m, int numQuesiti) throws NotAllowedException;

    public abstract void confermaTemplateP() throws NotAllowedException;
    /////////////////////////////UC9 COMPONI TEST PER SIMULAZIONE CARTACEA/////////////////////
    public abstract ArrayList<TemplatePersonalizzato> visualizzaTemplateTutor() throws NotAllowedException;

    public abstract List<Sezione> creaTestCartaceo(String idTemplate) throws NotAllowedException;

    public abstract ArrayList<QuesitoDescrizione> visualizzaQuesiti(String idSezione) throws NotAllowedException, NotEnoughQuestionsException;

    public abstract void inserisciQuesiti(List<String> listaIdQuesiti) throws NotAllowedException, QuestionNotFoundException, TemplateSectionException;

    public abstract void stampaTest(String nomeFile) throws NotAllowedException;
    ////////////////////////////UC10 CORREGGI SIMULAZIONI CARTACEO////////////////////////
    public abstract Map<String,String> recuperaInfoTestCartaceo(String fileName) throws NotAllowedException;

    public abstract Test correggiTestCartaceo(String cfStudente, String cfTutor, String idTest) throws NotAllowedException;

    public abstract Test confermaCorrezione() throws NotAllowedException;

    public abstract void selezionaRisposta(String idQuesitoReale, String idRisposta) throws NotAllowedException;
    //////////////////////////UC6 NUOVO TUTOR//////////////////////////////////
    public abstract Impiegato nuovoTutor(String cf, String nome, String cognome) throws NotAllowedException;
    //////////////////////////UC5 NUOVO STUDENTE//////////////////////////////////
    public abstract Studente nuovoStudente(String cf, String nome, String cognome) throws NotAllowedException;
}
