package com.ing_sw_2022.app;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class TestNuovoQuesito {

    private static UniCTest unictest;

    @BeforeClass
    public static void initTest() {
        unictest = UniCTest.getInstance();
    }


    @Test
    public void testVisualizzaMaterieInsegnate() {
        //Visualizza materie restituisce la lista di Materie insegnate dal tutor autenticato
        //Verifichiamo che tale lista non sia null
        assertNotNull(unictest.visualizzaMaterieInsegnate());
        //E il numero di materie inserite è > 0
        assertTrue(unictest.visualizzaMaterieInsegnate().size()>0);
    }

    @Test
    public void testNuovoQuesito() {
        unictest.nuovoQuesito("MAT01");
        assertNotNull(unictest.getMateriaCorrente());
        assertNotNull(unictest.getMateriaCorrente().getQuesitoCorrente());
    }

    @Test
    public void testInserisciFonte() {
        unictest.nuovoQuesito("MAT01");
        Materia materiaCorrente = unictest.getMateriaCorrente();
        assertNotNull(materiaCorrente);
        QuesitoDescrizione quesitoDescrizioneCorrente = materiaCorrente.getQuesitoCorrente();
        assertNotNull(quesitoDescrizioneCorrente);
        unictest.inserisciFonte("MIUR");
        //Visualizza che la fonte del quesitoCorrente non sia null
        assertNotNull(quesitoDescrizioneCorrente.getFonte());
    }

    @Test
    public void testInserisciTesto(){
        unictest.nuovoQuesito("MAT01");
        Materia materiaCorrente = unictest.getMateriaCorrente();
        assertNotNull(materiaCorrente);
        QuesitoDescrizione quesitoDescrizioneCorrente = materiaCorrente.getQuesitoCorrente();
        assertNotNull(quesitoDescrizioneCorrente);
        unictest.inserisciTesto("Quanto fa 2+2?");
        //Visualizza che il testo del quesitoCorrente non sia null
        assertNotNull(quesitoDescrizioneCorrente.getTesto());
    }

    @Test
    public void testInserisciDifficoltà(){
        unictest.nuovoQuesito("MAT01");
        Materia materiaCorrente = unictest.getMateriaCorrente();
        assertNotNull(materiaCorrente);
        QuesitoDescrizione quesitoDescrizioneCorrente = materiaCorrente.getQuesitoCorrente();
        assertNotNull(quesitoDescrizioneCorrente);
        unictest.inserisciDifficoltà(3);
        //Visualizza che la difficoltà del quesitoCorrente non sia null
        assertNotNull(quesitoDescrizioneCorrente.getDifficoltà());
    }

    @Test
    public void testInserisciRisposte(){
        unictest.nuovoQuesito("MAT01");
        Materia materiaCorrente = unictest.getMateriaCorrente();
        assertNotNull(materiaCorrente);
        QuesitoDescrizione quesitoDescrizioneCorrente = materiaCorrente.getQuesitoCorrente();
        assertNotNull(quesitoDescrizioneCorrente);
        unictest.inserisciRisposta("4",true);
        unictest.inserisciRisposta("5",false);
        unictest.inserisciRisposta("sqrt(16)",true);
        unictest.inserisciRisposta("9",false);
        //Visualizza che la lista delle rispsote del quesitoCorrente non sia null
        assertEquals(4, quesitoDescrizioneCorrente.getRisposte().size());
    }

    @Test
    public void testConfermaQuesito(){
        unictest.nuovoQuesito("MAT01");
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
        unictest.inserisciDifficoltà(3);
        Map<String, QuesitoDescrizione> mappaQuesiti = unictest.getMappaMaterie().get("MAT01").getMappaQuesiti();
        int size = mappaQuesiti.size();
        unictest.confermaQuesito("p1");
        assertEquals(size+1, mappaQuesiti.size());
        assertNull(unictest.getMateriaCorrente());
    }

}
