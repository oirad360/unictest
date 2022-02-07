package com.ing_sw_2022.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;


class TestAvviaSimulazione {

    static UniCTest unictest;
    static TemplatePersonalizzato tp1;
    static TreeMap<String,TemplatePersonalizzato> mappaTemplate;

    @BeforeAll
    static void initTest() {
        unictest = UniCTest.getInstance();
        Materia m = unictest.getMappaMaterie().get("MAT01");
        //creo quesiti per la simulazione
        unictest.setUtenteAutenticato("RSSMRA80A01C351O"); //autentico un tutor
        unictest.nuovoQuesito(m.getCodice());
        unictest.inserisciTesto("Quanto fa 2+2?");
        unictest.inserisciDifficoltà(3);
        unictest.inserisciRisposta("4",true);
        unictest.inserisciRisposta("5",false);
        unictest.confermaQuesito("p3");
        //creo un template per sceglierlo nel test dell'avvio della simulazione
        unictest.setUtenteAutenticato("VRDLGI99R21C351J"); //autentico uno studente
        unictest.nuovoTemplateP("Test template personalizzato");
        unictest.inserisciInfoTemplateP((float)1.0,(float)0.0,(float)0.0,2,1,2,1);
        unictest.creaSezioneP(m.getCodice(),1,3);
        unictest.confermaTemplateP();

        mappaTemplate= (TreeMap<String, TemplatePersonalizzato>) ((Studente)unictest.getUtenteAutenticato()).getMappaTemplatePersonalizzati();
        tp1 = mappaTemplate.get(mappaTemplate.lastKey());
    }

    @Test
    @BeforeEach
    void testAvviaSimulazione() {
        Materia m = unictest.getMappaMaterie().get("MAT01");//matematica
        //creo un template che richiede 800 quesiti di matematica (mi aspetto che l'avvio della simulazione fallisca)
        unictest.nuovoTemplateP("Test template personalizzato");
        unictest.inserisciInfoTemplateP((float)1.0,(float)0.0,(float)0.0,2,1,2,1);
        unictest.creaSezioneP(m.getCodice(),800,3);
        unictest.confermaTemplateP();
        //lancio la simulazione che dovrebbe tornare null
        com.ing_sw_2022.app.Test t=null;
        try{
            t=unictest.avviaSimulazione(mappaTemplate.get(mappaTemplate.lastKey()).getId());
            fail("Eccezione non avvenuta"); //mi aspetto che l'istruzione avviaSimulazione lanci un'eccezione, se arrivo qui considero fallito il test
        }catch (Exception e){
            assertEquals(e.getMessage(),"la materia "+m.getNome()+" contiene solo "+m.getMappaQuesiti().size()+" quesiti contro i "+800+" richiesti");
            assertNull(t);
        }
        mappaTemplate.remove(mappaTemplate.lastKey());
        //creo un template che richiede quesiti con 100 risposte (mi aspetto che l'avvio della simulazione fallisca)
        unictest.nuovoTemplateP("Test template personalizzato");
        unictest.inserisciInfoTemplateP((float)1.0,(float)0.0,(float)0.0,100,1,2,1);
        unictest.creaSezioneP(m.getCodice(),1,3);
        unictest.confermaTemplateP();
        //lancio la simulazione che dovrebbe tornare null
        try{
            t=unictest.avviaSimulazione(mappaTemplate.get(mappaTemplate.lastKey()).getId());
            fail("Eccezione non avvenuta"); //mi aspetto che l'istruzione avviaSimulazione lanci un'eccezione, se arrivo qui considero fallito il test
        }catch (Exception e){
            assertNull(t);
        }
        mappaTemplate.remove(mappaTemplate.lastKey());
        //adesso avvio la simulazione con il template creato da initTest (ha parametri idonei per l'avvio della simulazione)
        try {
            t = unictest.avviaSimulazione(tp1.getId());
            assertNotNull(t);
            assertTrue(t.getMappaQuesiti().size()>0);
            assertNotNull(((Studente)unictest.getUtenteAutenticato()).getTemplateSelezionato());
            assertNotNull(((Studente)unictest.getUtenteAutenticato()).getTemplateSelezionato().getTestCorrente());
        } catch (Exception e) {
            fail("Eccezione inaspettata");
        }
    }


    @Test
    void testVisualizzaTemplate() {
        ArrayList<Template> listaTemplate = unictest.visualizzaTemplate();
        assertTrue(listaTemplate.size()>0);
    }


    @Test
    void testSelezionaRisposta() {
        TreeMap<String,QuesitoReale> mappaQuesiti=tp1.getTestCorrente().getMappaQuesiti();
        QuesitoReale qr = mappaQuesiti.get(mappaQuesiti.lastKey());
        TreeMap<String,Risposta> mappaRisposte=qr.getQuesitoDescrizione().getRisposte();
        Risposta r=mappaRisposte.get(mappaRisposte.lastKey());
        unictest.selezionaRisposta(qr.getId(),r.getId());
        assertEquals(qr.getRisposteDate().values().size(),1);
        unictest.selezionaRisposta(qr.getId(),r.getId());
        assertEquals(qr.getRisposteDate().values().size(),0);
    }

    @Test
    void testTerminaSimulazione() {
        com.ing_sw_2022.app.Test t=unictest.terminaSimulazione();
        assertNotNull(t);
        assertNotNull(t.getPunteggioComplessivo());
        assertNull(((Studente)unictest.getUtenteAutenticato()).getTemplateSelezionato());
    }
}
