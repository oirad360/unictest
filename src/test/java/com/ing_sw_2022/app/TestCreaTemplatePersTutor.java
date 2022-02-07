package com.ing_sw_2022.app;

import com.ing_sw_2022.app.eccezioni.EmployeeNotAllowedException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;
import com.ing_sw_2022.app.eccezioni.UserNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestCreaTemplatePersTutor {
    static UniCTest unictest;
    static Impiegato imp;

    @BeforeAll
    static void initTest() {
        unictest = UniCTest.getInstance();
        try {
            unictest.setUtenteAutenticato("RSSMRA80A01C351O"); //autentico un impiegato
        } catch (UserNotFoundException e) {
            fail("Eccezione inaspettata");
        }
        imp=(Impiegato) unictest.getUtenteAutenticato();
    }

    @Test
    @BeforeEach
    void testNuovoTemplate(){
        unictest.setTutorSimulazione("RSSMRA80A01C351O");
        TemplatePersonalizzato tp=null;
        try {
            unictest.nuovoTemplateP("Test template personalizzato");
            tp = ((Impiegato)unictest.getUtenteAutenticato()).getTemplatePersonalizzatoCorrente();
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        assertNotNull(tp);
        assertEquals(tp.getNome(),"Test template personalizzato");
    }

    @Test
    void testEccezioni(){
        try {
            unictest.nuovoTemplateP("Test template personalizzato");
        } catch (NotAllowedException e) {
            assertEquals(e.getMessage(),"Non ho i permessi di TutorSimulazione");
        }
        unictest.setAmministratore("RSSMRA80A01C351O");
        try {
            unictest.nuovoTemplateP("Test template personalizzato");
        } catch (NotAllowedException e) {
            assertEquals(e.getMessage(),"Non ho i permessi di TutorSimulazione");
        }
    }

    @Test
    void testInserisciInfoTemplate() {
        TemplatePersonalizzato tp= null;
        try {
            tp = ((Impiegato)unictest.getUtenteAutenticato()).getTemplatePersonalizzatoCorrente();
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        List<Materia> listaMaterie= null;
        try {
            listaMaterie = unictest.inserisciInfoTemplateP((float)1.0,(float)0.0,(float)0.0,4,1,4,1);
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        assertNotNull(listaMaterie);
        assertTrue(listaMaterie.size()>0);
        assertEquals((float)1.0,tp.getPuntiCorretta(),(float)0.0);
        assertEquals((float)0.0,tp.getPuntiErrata(),(float)0.0);
        assertEquals((float)0.0,tp.getPuntiNonData(),(float)0.0);
        assertEquals(4,tp.getNumRisposte());
        assertEquals(1,tp.getMinRisposteCorrette());
        assertEquals(4,tp.getMaxRisposteCorrette());
        assertEquals(1,tp.getTempoMedio());
    }

    @Test
    void testCreaSezione() {
        TemplatePersonalizzato tp= null;
        try {
            tp = ((Impiegato)unictest.getUtenteAutenticato()).getTemplatePersonalizzatoCorrente();
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        int i=0;
        for(Materia m : unictest.getMappaMaterie().values()){
            try {
                unictest.creaSezioneP(m.getCodice(),10);
            } catch (NotAllowedException e) {
                fail("Eccezione inaspettata");
            } catch (StudentNotAllowedException e) {
                fail("Eccezione inaspettata");
            }
            Sezione s = tp.getListaSezioni().get(i);
            assertNotNull(tp.getListaSezioni());
            assertEquals(tp.getListaSezioni().size(),i+1);
            assertEquals(s.getMateria(),unictest.getMappaMaterie().get(m.getCodice()));
            assertEquals(s.getNumQuesiti(),10);
            i++;
        }

    }

    @Test
    void testConfermaTemplate(){
        try {
            unictest.confermaTemplateP(); //il template corrente viene eliminato
            assertNull(((Impiegato)unictest.getUtenteAutenticato()).getTemplatePersonalizzatoCorrente());
            assertTrue(((Impiegato)unictest.getUtenteAutenticato()).getMappaTemplatePersonalizzati().size()>0);
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        }
    }
}
