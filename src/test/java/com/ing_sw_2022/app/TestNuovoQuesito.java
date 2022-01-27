package com.ing_sw_2022.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.runners.Parameterized;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestNuovoQuesito {

    static UniCTest unictest;
    @BeforeAll
    static void initTest() {
        unictest = UniCTest.getInstance();
        unictest.setUtenteAutenticato("RSSMRA80A01C351O"); //autentico un tutor
    }


    @Test
    void testVisualizzaMaterieInsegnate() {
        System.out.println("1");
        //Visualizza materie restituisce la lista di Materie insegnate dal tutor autenticato
        //Verifichiamo che tale lista non sia null
        assertNotNull(unictest.visualizzaMaterieInsegnate());
        //E il numero di materie inserite è > 0
        assertTrue(unictest.visualizzaMaterieInsegnate().size()>0);
    }

    @Test
    @BeforeEach
    void testNuovoQuesito() {
        System.out.println("2");
        unictest.nuovoQuesito("MAT01");
        assertNotNull(unictest.getMateriaCorrente());
        assertNotNull(unictest.getMateriaCorrente().getQuesitoCorrente());


    }

    @Test
    void testInserisciFonte() {
        System.out.println("3");
        //unictest.nuovoQuesito("MAT01");
        //Materia materiaCorrente = unictest.getMateriaCorrente();
        //assertNotNull(materiaCorrente);
        //QuesitoDescrizione quesitoDescrizioneCorrente = materiaCorrente.getQuesitoCorrente();
        //assertNotNull(quesitoDescrizioneCorrente);
        unictest.inserisciFonte("MIUR");
        //Visualizza che la fonte del quesitoCorrente non sia null
        assertNotNull(unictest.getMateriaCorrente().getQuesitoCorrente().getFonte());

    }

    @Test
    void testInserisciTesto(){
        System.out.println("4");
        /*unictest.nuovoQuesito("MAT01");
        Materia materiaCorrente = unictest.getMateriaCorrente();
        assertNotNull(materiaCorrente);
        QuesitoDescrizione quesitoDescrizioneCorrente = materiaCorrente.getQuesitoCorrente();
        assertNotNull(quesitoDescrizioneCorrente);*/
        unictest.inserisciTesto("Quanto fa 2+2?");
        //Visualizza che il testo del quesitoCorrente non sia null
        assertNotNull(unictest.getMateriaCorrente().getQuesitoCorrente().getTesto());

    }

    @Test
    void testInserisciDifficoltà(){
        System.out.println("5");
        /*unictest.nuovoQuesito("MAT01");
        Materia materiaCorrente = unictest.getMateriaCorrente();
        assertNotNull(materiaCorrente);
        QuesitoDescrizione quesitoDescrizioneCorrente = materiaCorrente.getQuesitoCorrente();
        assertNotNull(quesitoDescrizioneCorrente);*/
        unictest.inserisciDifficoltà(3);
        //Visualizza che la difficoltà del quesitoCorrente non sia null
        assertNotNull(unictest.getMateriaCorrente().getQuesitoCorrente().getDifficoltà());

    }

    @Test
    void testInserisciRisposte(){
        System.out.println("6");
        /*unictest.nuovoQuesito("MAT01");
        Materia materiaCorrente = unictest.getMateriaCorrente();
        assertNotNull(materiaCorrente);
        QuesitoDescrizione quesitoDescrizioneCorrente = materiaCorrente.getQuesitoCorrente();
        assertNotNull(quesitoDescrizioneCorrente);*/
        unictest.inserisciRisposta("4",true);
        unictest.inserisciRisposta("5",false);
        unictest.inserisciRisposta("sqrt(16)",true);
        unictest.inserisciRisposta("9",false);
        //Visualizza che la lista delle rispsote del quesitoCorrente non sia null
        assertEquals(4, unictest.getMateriaCorrente().getQuesitoCorrente().getRisposte().size());

    }

    @Test
    void testConfermaQuesito(){
        System.out.println("7");
        /*unictest.nuovoQuesito("MAT01");
        Materia materiaCorrente = unictest.getMateriaCorrente();
        assertNotNull(materiaCorrente);
        QuesitoDescrizione quesitoDescrizioneCorrente = materiaCorrente.getQuesitoCorrente();
        assertNotNull(quesitoDescrizioneCorrente);
        unictest.inserisciFonte("MIUR");
        unictest.inserisciTesto("Quanto fa 2+2?");
        unictest.inserisciRisposta("4",true);
        unictest.inserisciRisposta("5",false);
        unictest.inserisciRisposta("sqrt(16)",true);
        unictest.inserisciRisposta("9",false);
        unictest.inserisciDifficoltà(3);*/
        Map<String, QuesitoDescrizione> mappaQuesiti = unictest.getMappaMaterie().get("MAT01").getMappaQuesiti();
        int size = mappaQuesiti.size();
        unictest.confermaQuesito("p1");
        assertEquals(size+1, mappaQuesiti.size());
        assertNull(unictest.getMateriaCorrente());
    }

}
