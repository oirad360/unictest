package com.ing_sw_2022.app;

import com.ing_sw_2022.app.eccezioni.EmployeeNotAllowedException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;
import com.ing_sw_2022.app.eccezioni.UserNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class TestAggiungiResponsabilità {
    static UniCTest unictest;
    static Impiegato aggiungeResponsabilità;
    static Impiegato daDecorare;
    static Studente studente;
    static Impiegato nonAmministratore;

    @BeforeAll
    static void initTest() {
        unictest = UniCTest.getInstance();

        //1. L'Amministratore che aggiunge le responsabilità viene creato "a mano". Le primitive per l'aggiunta delle responsabilità vengono testate dopo.
        aggiungeResponsabilità = new Amministratore(new Tutor("Luca", "Bianchi", "LCABNC87702C456P"));
        UniCTest.getInstance().getMappaUtenti().put("LCABNC87702C456P", aggiungeResponsabilità);
        //Quindi DEVE essere Amministratore.
        assertTrue(aggiungeResponsabilità.isAmministratore());

        //2. L'Impiegato da decorare è, in partenza, solo un Tutor.
        daDecorare = new Tutor("Marta", "Caruso", "CRSMRT80A02C456P");
        UniCTest.getInstance().getMappaUtenti().put("CRSMRT80A02C456P", daDecorare);
        //Quindi NON deve essere Amministratore né TutorSimulazione.
        assertFalse(daDecorare.isAmministratore());
        assertFalse(daDecorare.isTutorSimulazione());

        //3. Inserisco uno Studente
        studente = new Studente("Giacomo", "Giacomi", "GCMGCM80A02C555X");
        UniCTest.getInstance().getMappaUtenti().put("GCMGCM80A02C555X", studente);

        //4. Creo anche un Tutor NON Amministratore che tenterà di godere dei diritti di Amministratore senza successo.
        nonAmministratore = new  Tutor("Marco", "Bianco", "MRCBNC80A02C997Z");
        UniCTest.getInstance().getMappaUtenti().put("MRCBNC80A02C997Z", nonAmministratore);
    }

    //Test driven development --> Test BLACK BOX
    @Test
    void dirittiDiAggiuntaAmministratore(){
        boolean exception = false;
        //nonAmministratore diventa l'utenteAutenticato del Sistema.
        //nonAmministratore non è un Amministratore, quindi non può aggiungere Amministratori
        try {
            unictest.setUtenteAutenticato(nonAmministratore.getCf());
        } catch (UserNotFoundException e) {
            fail("L'Utente inserito non è presente nel Sistema");
        }

        try {
            unictest.rendiAmministratore(daDecorare.getCf());
        } catch (NotAllowedException e) {
            exception=true;
        } catch (Exception e) {
            fail("Non è stata catchata l'eccezione corretta.");
        }
        assertTrue(exception);
    }

    @Test
    void dirittiDiAggiuntaTutorSimulazione(){
        boolean exception = false;
        //nonAmministratore diventa l'utenteAutenticato del Sistema.
        //nonAmministratore non è un Amministratore, quindi non può aggiungere Amministratori
        try {
            unictest.setUtenteAutenticato(nonAmministratore.getCf());
        } catch (UserNotFoundException e) {
            fail("L'Utente inserito non è presente nel Sistema");
        }

        try {
            unictest.rendiTutorSimulazione(daDecorare.getCf());
            daDecorare= (Impiegato) unictest.getMappaUtenti().get(daDecorare.getCf());
        } catch (NotAllowedException e) {
            exception=true;
        } catch (Exception e) {
            fail("Non è stata catchata l'eccezione corretta.");
        }
        assertTrue(exception);
    }

    @Test
    void aggiungiAmministratore(){
        //aggiungeResponsabilità diventa l'utenteAutenticato del Sistema.
        try {
            unictest.setUtenteAutenticato(aggiungeResponsabilità.getCf());
        } catch (UserNotFoundException e) {
            fail("L'Utente inserito non è presente nel Sistema");
        }

        try {
            unictest.rendiAmministratore(daDecorare.getCf());
            daDecorare= (Impiegato) unictest.getMappaUtenti().get(daDecorare.getCf());
        } catch (NotAllowedException e) {
            fail("NotAllowedException inattesa. NON dovrebbe essere generata alcuna eccezione NotAllowedException.");
        } catch (StudentNotAllowedException e) {
            fail("StudentNotAllowedException inattesa. NON dovrebbe essere generata alcuna eccezione StudentNotAllowedException.");
        } catch (UserNotFoundException e) {
            fail("UserNotFoundException inattesa. NON dovrebbe essere generata alcuna eccezione UserNotFoundException.");
        }
        assertTrue(daDecorare.isAmministratore());
    }

    @Test
    void aggiungiTutorSimulazione(){
        //aggiungeResponsabilità diventa l'utenteAutenticato del Sistema.
        try {
            unictest.setUtenteAutenticato(aggiungeResponsabilità.getCf());
        } catch (UserNotFoundException e) {
            fail("L'Utente inserito non è presente nel Sistema");
        }

        try {
            unictest.rendiTutorSimulazione(daDecorare.getCf());
            daDecorare= (Impiegato) unictest.getMappaUtenti().get(daDecorare.getCf());
        } catch (NotAllowedException e) {
            fail("NotAllowedException inattesa. NON dovrebbe essere generata alcuna eccezione NotAllowedException.");
        } catch (StudentNotAllowedException e) {
            fail("StudentNotAllowedException inattesa. NON dovrebbe essere generata alcuna eccezione StudentNotAllowedException.");
        } catch (UserNotFoundException e) {
            fail("UserNotFoundException inattesa. NON dovrebbe essere generata alcuna eccezione UserNotFoundException.");
        }

        assertTrue(daDecorare.isTutorSimulazione());
    }

    //Test WHITE BOX --> Conoscendo i dettagli implementativi delle funzioni realizzate
    @Test
    void testoResponsabilitàInterna(){ //Testo la responsabilità più "interna"
        //aggiungeResponsabilità diventa l'utenteAutenticato del Sistema.
        try {
            unictest.setUtenteAutenticato(aggiungeResponsabilità.getCf());
        } catch (UserNotFoundException e) {
            fail("L'Utente inserito non è presente nel Sistema");
        }

        try {
            unictest.rendiTutorSimulazione(daDecorare.getCf()); //Lui è la responsabilità più interna
            unictest.rendiAmministratore(daDecorare.getCf());
            daDecorare= (Impiegato) unictest.getMappaUtenti().get(daDecorare.getCf());
        } catch (NotAllowedException e) {
            fail("NotAllowedException inattesa. NON dovrebbe essere generata alcuna eccezione NotAllowedException.");
        } catch (StudentNotAllowedException e) {
            fail("StudentNotAllowedException inattesa. NON dovrebbe essere generata alcuna eccezione StudentNotAllowedException.");
        } catch (UserNotFoundException e) {
            fail("UserNotFoundException inattesa. NON dovrebbe essere generata alcuna eccezione UserNotFoundException.");
        }

        assertTrue(daDecorare.isTutorSimulazione());
    }

    @Test
    void testoResponsabilitàEsterna(){ //Testo la responsabilità più "esterna"
        //aggiungeResponsabilità diventa l'utenteAutenticato del Sistema.
        try {
            unictest.setUtenteAutenticato(aggiungeResponsabilità.getCf());
        } catch (UserNotFoundException e) {
            fail("L'Utente inserito non è presente nel Sistema");
        }

        try {
            unictest.rendiTutorSimulazione(daDecorare.getCf());
            unictest.rendiAmministratore(daDecorare.getCf()); //Lui è la responsabilità più esterna
            daDecorare= (Impiegato) unictest.getMappaUtenti().get(daDecorare.getCf());
        } catch (NotAllowedException e) {
            fail("NotAllowedException inattesa. NON dovrebbe essere generata alcuna eccezione NotAllowedException.");
        } catch (StudentNotAllowedException e) {
            fail("StudentNotAllowedException inattesa. NON dovrebbe essere generata alcuna eccezione StudentNotAllowedException.");
        } catch (UserNotFoundException e) {
            fail("UserNotFoundException inattesa. NON dovrebbe essere generata alcuna eccezione UserNotFoundException.");
        }

        assertTrue(daDecorare.isAmministratore());
    }


    @Test
    void autoRimuoviAmministratore(){ //NON deve essere possibile auto-rimuovere la responsabilità di Amministratore
        boolean exception = false;
        //aggiungeResponsabilità diventa l'utenteAutenticato del Sistema.
        try {
            unictest.setUtenteAutenticato(aggiungeResponsabilità.getCf());
        } catch (UserNotFoundException e) {
            fail("L'Utente inserito non è presente nel Sistema");
        }

        try {
            unictest.rimuoviAmministratore(aggiungeResponsabilità.getCf());
            aggiungeResponsabilità= (Impiegato) unictest.getMappaUtenti().get(aggiungeResponsabilità.getCf());
        } catch (NotAllowedException e) {
            exception = true;
        } catch (StudentNotAllowedException e) {
            fail("StudentNotAllowedException inattesa. NON dovrebbe essere generata alcuna eccezione StudentNotAllowedException.");
        } catch (UserNotFoundException e) {
            fail("UserNotFoundException inattesa. NON dovrebbe essere generata alcuna eccezione UserNotFoundException.");
        }

        assertTrue(aggiungeResponsabilità.isAmministratore()); //DEVO essere ancora un amministratore
        assertTrue(exception); //DEVO aver generato l'eccezione
    }

}
