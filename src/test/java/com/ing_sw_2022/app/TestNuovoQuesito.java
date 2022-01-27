package com.ing_sw_2022.app;

import org.junit.jupiter.api.AfterAll;
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
        assertNotNull(unictest.getMateriaCorrente());
        assertNotNull(unictest.getMateriaCorrente().getQuesitoCorrente());
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
        //unictest.nuovoQuesito("MAT01");
        //Materia materiaCorrente = unictest.getMateriaCorrente();
        //assertNotNull(materiaCorrente);
        //QuesitoDescrizione quesitoDescrizioneCorrente = materiaCorrente.getQuesitoCorrente();
        //assertNotNull(quesitoDescrizioneCorrente);
        unictest.inserisciFonte("MIUR");
        //Visualizza che la fonte del quesitoCorrente non sia null
        assertNotNull(unictest.getMateriaCorrente().getQuesitoCorrente().getFonte());
        assertEquals(unictest.getMateriaCorrente().getQuesitoCorrente().getFonte(),"MIUR");

    }

    @Test
    void testInserisciTesto(){
        /*unictest.nuovoQuesito("MAT01");
        Materia materiaCorrente = unictest.getMateriaCorrente();
        assertNotNull(materiaCorrente);
        QuesitoDescrizione quesitoDescrizioneCorrente = materiaCorrente.getQuesitoCorrente();
        assertNotNull(quesitoDescrizioneCorrente);*/
        unictest.inserisciTesto("Quanto fa 2+2?");
        //Visualizza che il testo del quesitoCorrente non sia null
        assertNotNull(unictest.getMateriaCorrente().getQuesitoCorrente().getTesto());
        assertEquals(unictest.getMateriaCorrente().getQuesitoCorrente().getTesto(),"Quanto fa 2+2?");

    }

    @Test
    void testInserisciDifficoltà(){
        /*unictest.nuovoQuesito("MAT01");
        Materia materiaCorrente = unictest.getMateriaCorrente();
        assertNotNull(materiaCorrente);
        QuesitoDescrizione quesitoDescrizioneCorrente = materiaCorrente.getQuesitoCorrente();
        assertNotNull(quesitoDescrizioneCorrente);*/
        unictest.inserisciDifficoltà(3);
        //Visualizza che la difficoltà del quesitoCorrente non sia null
        assertNotNull(unictest.getMateriaCorrente().getQuesitoCorrente().getDifficoltà());
        assertEquals(unictest.getMateriaCorrente().getQuesitoCorrente().getDifficoltà(),3);

    }

    @Test
    void testInserisciRisposte(){
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
        assertNotNull(unictest.getMateriaCorrente().getQuesitoCorrente().getRisposte());
        assertEquals(4, unictest.getMateriaCorrente().getQuesitoCorrente().getRisposte().size());

    }

    @Test
    void testConfermaQuesito(){
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
