package com.ing_sw_2022.app;

import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;
import com.ing_sw_2022.app.eccezioni.UserNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCreaTemplateUff {
    static UniCTest unictest;

    @BeforeAll
    static void initTest() {
        unictest = UniCTest.getInstance();
        try {
            unictest.setUtenteAutenticato("RSSMRA80A01C351O"); //autentico uno tutor amministratore
        } catch (UserNotFoundException e) {
            fail("Eccezione inaspettata");
        }
        unictest.setAmministratore("RSSMRA80A01C351O");
    }

    @Test
    @BeforeEach
    void testNuovoTemplateU(){
        try {
            unictest.nuovoTemplateU("Test template ufficiale"); //inizializzo il template corrente per i test successivi
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        TemplateUfficiale t=((Amministratore)unictest.getUtenteAutenticato()).getTemplateUfficialeCorrente();
        assertNotNull(t);
        assertEquals(t.getNome(),"Test template ufficiale");
    }

    @Test
    void testInserisciInfoTemplateU() {
        TemplateUfficiale t=((Amministratore)unictest.getUtenteAutenticato()).getTemplateUfficialeCorrente();
        try {
            unictest.inserisciInfoTemplateU("MIUR",(float)1.0,(float)0.0,(float)0.0,4,1,4,50);
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
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
        Template t=((Amministratore)unictest.getUtenteAutenticato()).getTemplateUfficialeCorrente();
        try {
            unictest.creaSezioneU("Matematica",10);
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        assertNotNull(t.getListaSezioni());
        Sezione s = t.getListaSezioni().get(0);
        assertEquals(t.getListaSezioni().size(),1);
        assertEquals(s.getMateria(),unictest.getMappaMaterie().get("MAT01"));
        assertEquals(s.getNumQuesiti(),10);
        try {
            unictest.creaSezioneU("Astronomia",10);
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        assertNotNull(t.getListaSezioni());
        s = t.getListaSezioni().get(1);
        assertEquals(t.getListaSezioni().size(),2);
        assertNotNull(unictest.getMappaMaterie().get(s.getMateria().getCodice()));
        assertEquals(s.getMateria(),unictest.getMappaMaterie().get(s.getMateria().getCodice()));
        assertEquals(s.getNumQuesiti(),10);
    }

    @Test
    void testConfermaTemplateU(){
        try {
            unictest.confermaTemplateU(); //il template corrente viene eliminato
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        assertNull(((Amministratore)unictest.getUtenteAutenticato()).getTemplateUfficialeCorrente());
        assertTrue(unictest.getMappaTemplateUfficiali().size()>0);
    }
}
