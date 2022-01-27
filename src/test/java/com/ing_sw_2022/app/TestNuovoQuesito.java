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
        unictest.nuovoQuesito("MAT01");//inizializzo la materia corrente e il quesito corrente
        assertNotNull(((Tutor)unictest.getUtenteAutenticato()).getMateriaCorrente());
        assertNotNull(((Tutor)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente());
    }

    @Test
    void testVisualizzaMaterieInsegnate() {
        //Visualizza materie restituisce la lista di Materie insegnate dal tutor autenticato
        //Verifichiamo che tale lista non sia null
        assertNotNull(unictest.visualizzaMaterieInsegnate());
        //E il numero di materie inserite è > 0
        assertTrue(unictest.visualizzaMaterieInsegnate().size()>0);
    }

    @Test
    void testInserisciFonte() {
        unictest.inserisciFonte("MIUR");
        //Visualizza che la fonte del quesitoCorrente non sia null
        assertNotNull(((Tutor)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getFonte());
        assertEquals(((Tutor)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getFonte(),"MIUR");
    }

    @Test
    void testInserisciTesto(){
        unictest.inserisciTesto("Quanto fa 2+2?");
        //Visualizza che il testo del quesitoCorrente non sia null
        assertNotNull(((Tutor)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getTesto());
        assertEquals(((Tutor)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getTesto(),"Quanto fa 2+2?");

    }

    @Test
    void testInserisciDifficoltà(){
        unictest.inserisciDifficoltà(3);
        //Visualizza che la difficoltà del quesitoCorrente non sia null
        assertNotNull(((Tutor)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getDifficoltà());
        assertEquals(((Tutor)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getDifficoltà(),3);

    }

    @Test
    void testInserisciRisposte(){
        unictest.inserisciRisposta("4",true);
        unictest.inserisciRisposta("5",false);
        unictest.inserisciRisposta("sqrt(16)",true);
        unictest.inserisciRisposta("9",false);
        //Visualizza che la lista delle rispsote del quesitoCorrente non sia null
        assertNotNull(((Tutor)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getRisposte());
        assertEquals(4, ((Tutor)unictest.getUtenteAutenticato()).getMateriaCorrente().getQuesitoCorrente().getRisposte().size());

    }

    @Test
    void testConfermaQuesito(){
        Map<String, QuesitoDescrizione> mappaQuesiti = ((Tutor)unictest.getUtenteAutenticato()).getMateriaCorrente().getMappaQuesiti();
        int size = mappaQuesiti.size();
        unictest.confermaQuesito("p1");
        assertEquals(size+1, mappaQuesiti.size());
        assertNull(((Tutor)unictest.getUtenteAutenticato()).getMateriaCorrente());
    }

}
