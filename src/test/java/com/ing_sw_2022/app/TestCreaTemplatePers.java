package com.ing_sw_2022.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class TestCreaTemplatePers {

    static UniCTest unictest;

    @BeforeAll
    static void initTest() {
        unictest = UniCTest.getInstance();
        unictest.setUtenteAutenticato("VRDLGI99R21C351J"); //autentico uno studente
    }

    @Test
    @BeforeEach
    void testNuovoTemplate(){
        unictest.nuovoTemplate("Test template personalizzato"); //inizializzo il template corrente per i test successivi
        TemplatePersonalizzato tp=((Studente)unictest.getUtenteAutenticato()).getTemplateCorrente();
        assertNotNull(tp);
        assertEquals(tp.getNome(),"Test template personalizzato");
    }

    @Test
    void testInserisciInfoTemplate() {
        /*unictest.nuovoTemplate("Test template personalizzato");
        TemplatePersonalizzato tp=((Studente)unictest.getUtenteAutenticato()).getTemplateCorrente();
        assertNotNull(tp);
        assertEquals(tp.getNome(),"Test template personalizzato");*/
        TemplatePersonalizzato tp=((Studente)unictest.getUtenteAutenticato()).getTemplateCorrente();
        List<Materia> listaMaterie=unictest.inserisciInfoTemplate((float)1.0,(float)0.0,(float)0.0,4,1,4,1);
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
        /*unictest.nuovoTemplate("Test template personalizzato");
        TemplatePersonalizzato tp=((Studente)unictest.getUtenteAutenticato()).getTemplateCorrente();
        assertNotNull(tp);
        assertEquals(tp.getNome(),"Test template personalizzato");
        List<Materia> listaMaterie=unictest.inserisciInfoTemplate((float)1.0,(float)0.0,(float)0.0,4,1,4,1);
        assertNotNull(listaMaterie);
        assertTrue(listaMaterie.size()>0);
        assertEquals((float)1.0,tp.getPuntiCorretta(),(float)0.0);
        assertEquals((float)0.0,tp.getPuntiErrata(),(float)0.0);
        assertEquals((float)0.0,tp.getPuntiNonData(),(float)0.0);
        assertEquals(4,tp.getNumRisposte());
        assertEquals(1,tp.getMinRisposteCorrette());
        assertEquals(4,tp.getMaxRisposteCorrette());
        assertEquals(1,tp.getTempoMedio());*/
        TemplatePersonalizzato tp=((Studente)unictest.getUtenteAutenticato()).getTemplateCorrente();
        int i=0;
        for(Materia m : unictest.getMappaMaterie().values()){
            unictest.creaSezione(m.getCodice(),10,3);
            Sezione s = tp.getListaSezioni().get(i);
            assertNotNull(tp.getListaSezioni());
            assertEquals(tp.getListaSezioni().size(),i+1);
            assertEquals(s.getMateria(),unictest.getMappaMaterie().get(m.getCodice()));
            assertEquals(s.getNumQuesiti(),10);
            assertEquals(s.getDifficoltàMedia(),3);
            i++;
        }

    }

    @Test
    void testConfermaTemplate(){
        /*unictest.nuovoTemplate("Test template personalizzato");
        TemplatePersonalizzato tp=((Studente)unictest.getUtenteAutenticato()).getTemplateCorrente();
        assertNotNull(tp);
        assertEquals(tp.getNome(),"Test template personalizzato");
        List<Materia> listaMaterie=unictest.inserisciInfoTemplate((float)1.0,(float)0.0,(float)0.0,4,1,4,1);
        assertNotNull(listaMaterie);
        assertTrue(listaMaterie.size()>0);
        assertEquals((float)1.0,tp.getPuntiCorretta(),(float)0.0);
        assertEquals((float)0.0,tp.getPuntiErrata(),(float)0.0);
        assertEquals((float)0.0,tp.getPuntiNonData(),(float)0.0);
        assertEquals(4,tp.getNumRisposte());
        assertEquals(1,tp.getMinRisposteCorrette());
        assertEquals(4,tp.getMaxRisposteCorrette());
        assertEquals(1,tp.getTempoMedio());
        int i=0;
        for(Materia m : listaMaterie){
            unictest.creaSezione(m.getCodice(),2,3);
            Sezione s = tp.getListaSezioni().get(i);
            assertNotNull(tp.getListaSezioni());
            assertEquals(tp.getListaSezioni().size(),i+1);
            assertEquals(s.getMateria(),unictest.getMappaMaterie().get(m.getCodice()));
            assertEquals(s.getNumQuesiti(),2);
            assertEquals(s.getDifficoltàMedia(),3);
            i++;
        }*/
        unictest.confermaTemplate(); //il template corrente viene eliminato
        assertNull(((Studente)unictest.getUtenteAutenticato()).getTemplateCorrente());
        assertTrue(((Studente)unictest.getUtenteAutenticato()).getMappaTemplatePersonalizzati().size()>0);
    }

}
