package com.ing_sw_2022.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCreaTemplateUff {
    static UniCTest unictest;

    @BeforeAll
    static void initTest() {
        unictest = UniCTest.getInstance();
        unictest.setUtenteAutenticato("RSSMRA80A01C351O"); //autentico uno tutor amministratore
        unictest.setAmministratore("RSSMRA80A01C351O");
    }

    @Test
    @BeforeEach
    void testNuovoTemplateU(){
        unictest.nuovoTemplateU("Test template ufficiale"); //inizializzo il template corrente per i test successivi
        TemplateUfficiale t=((Amministratore)unictest.getUtenteAutenticato()).getTemplateCorrente();
        assertNotNull(t);
        assertEquals(t.getNome(),"Test template ufficiale");
    }

    @Test
    void testInserisciInfoTemplateU() {
        TemplateUfficiale t=((Amministratore)unictest.getUtenteAutenticato()).getTemplateCorrente();
        unictest.inserisciInfoTemplateU("MIUR",(float)1.0,(float)0.0,(float)0.0,4,1,4,50);
        assertEquals((float)1.0,t.getPuntiCorretta(),(float)0.0);
        assertEquals((float)0.0,t.getPuntiErrata(),(float)0.0);
        assertEquals((float)0.0,t.getPuntiNonData(),(float)0.0);
        assertEquals(4,t.getNumRisposte());
        assertEquals(1,t.getMinRisposteCorrette());
        assertEquals(4,t.getMaxRisposteCorrette());
        assertEquals(50,t.getTempoTotale());
    }

    @Test
    void testCreaSezioneU() {
        Template t=((Amministratore)unictest.getUtenteAutenticato()).getTemplateCorrente();
        unictest.creaSezioneU("Matematica",10);
        assertNotNull(t.getListaSezioni());
        Sezione s = t.getListaSezioni().get(0);
        assertEquals(t.getListaSezioni().size(),1);
        assertEquals(s.getMateria(),unictest.getMappaMaterie().get("MAT01"));
        assertEquals(s.getNumQuesiti(),10);
        unictest.creaSezioneU("Astronomia",10);
        assertNotNull(t.getListaSezioni());
        s = t.getListaSezioni().get(1);
        assertEquals(t.getListaSezioni().size(),2);
        assertNotNull(unictest.getMappaMaterie().get(s.getMateria().getCodice()));
        assertEquals(s.getMateria(),unictest.getMappaMaterie().get(s.getMateria().getCodice()));
        assertEquals(s.getNumQuesiti(),10);
    }

    @Test
    void testConfermaTemplateU(){
        unictest.confermaTemplateU(); //il template corrente viene eliminato
        assertNull(((Amministratore)unictest.getUtenteAutenticato()).getTemplateCorrente());
        assertTrue(unictest.getMappaTemplateUfficiali().size()>0);
    }
}
