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

    @Test
    @BeforeAll
    static void initTest() {
        unictest = UniCTest.getInstance();

        //1. L'Amministratore che aggiunge le responsabilità viene creato "a mano". Le primitive per l'aggiunta delle responsabilità vengono testate dopo.
        aggiungeResponsabilità = new Amministratore(new Tutor("Luca", "Bianchi", "LCABNC80A02C456P"));
        UniCTest.getInstance().getMappaUtenti().put("LCABNC80A02C456P", aggiungeResponsabilità);
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
        UniCTest.getInstance().getMappaUtenti().put("GCMGCM80A02C555X", daDecorare);
    }

    //Test driven development --> Test BLACK BOX
    @Test
    void dirittiDiAggiuntaAmministratore(){
        try {
            aggiungeResponsabilità.rendiAmministratore(daDecorare);
        } catch (NotAllowedException e) {
            fail("Eccezione inattesa: dovresti poter rendere Amministratore qualcuno, dato che sei Amministratore");
        }
    }

    @Test
    void dirittiDiAggiuntaTutorSimulazione(){
        try {
            aggiungeResponsabilità.rendiTutorSimulazione(daDecorare);
        } catch (NotAllowedException e) {
            fail("Eccezione inattesa: dovresti poter rendere Amministratore qualcuno, dato che sei Amministratore");
        }
    }

    @Test
    void aggiungiAmministratore(){
        try {
            aggiungeResponsabilità.rendiAmministratore(daDecorare);
        } catch (NotAllowedException e) {
            fail("Eccezione inattesa relativa al test dirittiDiAggiuntaAmministratore()");
        }
        assertTrue(daDecorare.isAmministratore());
    }

    @Test
    void aggiungiTutorSimulazione(){
        try {
            aggiungeResponsabilità.rendiTutorSimulazione(daDecorare);
        } catch (NotAllowedException e) {
            fail("Eccezione inattesa relativa al test dirittiDiAggiuntaTutorSimulazione()");
        }
        assertTrue(daDecorare.isTutorSimulazione());
    }

    //Test WHITE BOX --> Conoscendo i dettagli implementativi delle funzioni realizzate
    @Test
    void testoResponsabilitàInterna(){ //Testo la responsabilità più "interna"
        try {
            aggiungeResponsabilità.rendiTutorSimulazione(daDecorare); //Lui è la responsabilità più interna
            aggiungeResponsabilità.rendiAmministratore(daDecorare);
        } catch (NotAllowedException e) {
            fail("Eccezione inattesa relativa al test dirittiDiAggiuntaTutorSimulazione()");
        }
        assertTrue(daDecorare.isTutorSimulazione());
    }

    @Test
    void testoResponsabilitàEsterna(){ //Testo la responsabilità più "esterna"
        try {
            aggiungeResponsabilità.rendiAmministratore(daDecorare);
            aggiungeResponsabilità.rendiTutorSimulazione(daDecorare); //Lui è la responsabilità più esterna
        } catch (NotAllowedException e) {
            fail("Eccezione inattesa relativa al test dirittiDiAggiuntaTutorSimulazione()");
        }
        assertTrue(daDecorare.isTutorSimulazione());
    }


    @Test
    void autoRimuoviAmministratore(){
        boolean exception = false;
        try {
            aggiungeResponsabilità.rimuoviAmministratore(aggiungeResponsabilità);
        } catch (NotAllowedException e) {
            exception=true;
        }
        //L'eccezione DEVE essere avvenuta, in quanto un Amministratore non ha il diritto di rimuovere a sé stesso i diritti di amministrazione
        assertTrue(exception);
    }

}
