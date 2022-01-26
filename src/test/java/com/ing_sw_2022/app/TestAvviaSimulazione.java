package com.ing_sw_2022.app;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class TestAvviaSimulazione {

    private static UniCTest unictest;

    @BeforeAll
    static void initTest() { //creo un template come inizializzazione
        unictest = UniCTest.getInstance();
        unictest.setUtenteAutenticato("VRDLGI99R21C351J"); //autentico un studente
        unictest.nuovoTemplate("Test template personalizzato");
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
        unictest.creaSezione(listaMaterie.get(0).getCodice(),1,3);
        Sezione s = tp.getListaSezioni().get(0);
        assertNotNull(tp.getListaSezioni());
        assertEquals(tp.getListaSezioni().size(),1);
        assertEquals(s.getMateria(),unictest.getMappaMaterie().get(listaMaterie.get(0).getCodice()));
        assertEquals(s.getNumQuesiti(),1);
        assertEquals(s.getDifficoltàMedia(),3);
        unictest.confermaTemplate();
        assertNull(((Studente)unictest.getUtenteAutenticato()).getTemplateCorrente());
        assertTrue(((Studente)unictest.getUtenteAutenticato()).getMappaTemplatePersonalizzati().size()>0);
    }


    @Test
    void testVisualizzaTemplate() {
        ArrayList<TemplatePersonalizzato> listaTemplate = unictest.visualizzaTemplate();
        assertTrue(listaTemplate.size()>0);
    }

    @Test
    void testAvviaSimulazione() {
        ArrayList<TemplatePersonalizzato> listaTemplate = unictest.visualizzaTemplate();
        assertTrue(listaTemplate.size()>0);
        com.ing_sw_2022.app.Test t = unictest.avviaSimulazione(listaTemplate.get(listaTemplate.size()-1).getId());
        assertNotNull(t);
    }

    @Test
    void testSelezionaRisposta() {
        ArrayList<TemplatePersonalizzato> listaTemplate = unictest.visualizzaTemplate();
        assertTrue(listaTemplate.size()>0);
    }
}
