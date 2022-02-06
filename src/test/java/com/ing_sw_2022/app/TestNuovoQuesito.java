package com.ing_sw_2022.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class TestNuovoQuesito {

    static UniCTest unictest;
    @BeforeAll
    static void initTest() {
        unictest = UniCTest.getInstance();
        unictest.setUtenteAutenticato("RSSMRA80A01C351O"); //autentico un tutor
    }

    @Test
    @BeforeEach
    void testNuovoQuesito(){
        try {
            unictest.nuovoQuesito("MAT01");//inizializzo la materia corrente e il quesito corrente
        } catch (StudentNotAllowedException e) {
            e.printStackTrace();
        }
        assertNotNull(((Impiegato)unictest.getUtenteAutenticato()).getMateriaCorrente());
        assertNotNull(((Impiegato)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente());
    }

    @Test
    void testVisualizzaMaterieInsegnate() {
        //Visualizza materie restituisce la lista di Materie insegnate dal tutor autenticato
        //Verifichiamo che tale lista non sia null
        try {
            assertNotNull(unictest.visualizzaMaterieInsegnate());
            //E il numero di materie inserite è > 0
            assertTrue(unictest.visualizzaMaterieInsegnate().size()>0);
        } catch (StudentNotAllowedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInserisciFonte() {
        try {
            unictest.inserisciFonte("MIUR");
        } catch (StudentNotAllowedException e) {
            e.printStackTrace();
        }
        //Visualizza che la fonte del quesitoCorrente non sia null
        assertNotNull(((Impiegato)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getFonte());
        assertEquals(((Impiegato)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getFonte(),"MIUR");
    }

    @Test
    void testInserisciTesto(){
        try {
            unictest.inserisciTesto("Quanto fa 2+2?");
        } catch (StudentNotAllowedException e) {
            e.printStackTrace();
        }
        //Visualizza che il testo del quesitoCorrente non sia null
        assertNotNull(((Impiegato)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getTesto());
        assertEquals(((Impiegato)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getTesto(),"Quanto fa 2+2?");

    }

    @Test
    void testInserisciDifficoltà(){
        try {
            unictest.inserisciDifficoltà(3);
        } catch (StudentNotAllowedException e) {
            e.printStackTrace();
        }
        //Visualizza che la difficoltà del quesitoCorrente non sia null
        assertNotNull(((Impiegato)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getDifficoltà());
        assertEquals(((Impiegato)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getDifficoltà(),3);

    }

    @Test
    void testInserisciRisposte(){
        try {
            unictest.inserisciRisposta("4",true);
            unictest.inserisciRisposta("5",false);
            unictest.inserisciRisposta("sqrt(16)",true);
            unictest.inserisciRisposta("9",false);
        } catch (StudentNotAllowedException e) {
            e.printStackTrace();
        }

        //Visualizza che la lista delle rispsote del quesitoCorrente non sia null
        assertNotNull(((Impiegato)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getRisposte());
        assertEquals(4, ((Impiegato)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getRisposte().size());

    }

    @Test
    void testConfermaQuesito(){
        Map<String, QuesitoDescrizione> mappaQuesiti = null;
        Integer size =null;
        try {
            mappaQuesiti = ((Impiegato)unictest.getUtenteAutenticato()).getMateriaCorrente().getMappaQuesiti();
            size = mappaQuesiti.size();
            unictest.confermaQuesito("p1");
        } catch (StudentNotAllowedException e) {
            System.out.println("a");
        }

        assertEquals(size+1, mappaQuesiti.size());
        assertNull(((Impiegato)unictest.getUtenteAutenticato()).getMateriaCorrente());
    }

}
