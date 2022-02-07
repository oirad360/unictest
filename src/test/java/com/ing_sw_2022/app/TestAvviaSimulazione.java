package com.ing_sw_2022.app;

import com.ing_sw_2022.app.eccezioni.EmployeeNotAllowedException;
import com.ing_sw_2022.app.eccezioni.NotEnoughQuestionsException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;
import com.ing_sw_2022.app.eccezioni.UserNotFoundException;
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
        try {
            unictest.setUtenteAutenticato("RSSMRA80A01C351O"); //autentico un tutor
        } catch (UserNotFoundException e) {
            fail("Eccezione inaspettata");
        }
        try {
            unictest.nuovoQuesito(m.getCodice());
            unictest.inserisciTesto("Quanto fa 2+2?");
            unictest.inserisciDifficoltà(3);
            unictest.inserisciRisposta("4",true);
            unictest.inserisciRisposta("5",false);
            unictest.confermaQuesito("p3");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }

        //creo un template per sceglierlo nel test dell'avvio della simulazione
        try {
            unictest.setUtenteAutenticato("VRDLGI99R21C351J"); //autentico uno studente
        } catch (UserNotFoundException e) {
            fail("Eccezione inaspettata");
        }

        try {
            unictest.nuovoTemplateP("Test template personalizzato");
            unictest.inserisciInfoTemplateP((float)1.0,(float)0.0,(float)0.0,2,1,2,1);
            unictest.creaSezioneP(m.getCodice(),1,3);
            unictest.confermaTemplateP();
        } catch (EmployeeNotAllowedException e) {
            fail("Eccezione inaspettata");
            /*non mi aspetto che lanci questa eccezione perchè ho autenticato uno studente, che dovrebbe
             * avere sempre i permessi per creare un template personalizzato. Invece, se avessi autenticato
             * un impiegato, lancerebbe un'eccezione qualora quell'impiegato non fosse un Tutor di Simulazione
             * poichè solo quest'ultimo ha i permessi per creare un template personalizzato.*/
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
            //questa non avverrà perchè l'utente è uno studente
        }
        mappaTemplate= (TreeMap<String, TemplatePersonalizzato>) ((Studente)unictest.getUtenteAutenticato()).getMappaTemplatePersonalizzati();
        tp1 = mappaTemplate.get(mappaTemplate.lastKey());
    }

    @Test
    @BeforeEach
    void testAvviaSimulazione() {
        //adesso avvio la simulazione con il template creato da initTest (ha parametri idonei per l'avvio della simulazione)
        try {
            com.ing_sw_2022.app.Test t = unictest.avviaSimulazione(tp1.getId());
            assertNotNull(t);
            assertTrue(t.getMappaQuesiti().size()>0);
            assertNotNull(((Studente)unictest.getUtenteAutenticato()).getTemplateSelezionato());
            assertNotNull(((Studente)unictest.getUtenteAutenticato()).getTemplateSelezionato().getTestCorrente());
        } catch (Exception e) {
            fail("Eccezione inaspettata");
        }
    }

    @Test
    void testEccezioniAvviaSimulazione(){
        Materia m = unictest.getMappaMaterie().get("MAT01");//matematica
        //creo un template che richiede 800 quesiti di matematica (mi aspetto che l'avvio della simulazione fallisca)

        try {
            unictest.nuovoTemplateP("Test template personalizzato");
            unictest.inserisciInfoTemplateP((float)1.0,(float)0.0,(float)0.0,2,1,2,1);
            unictest.creaSezioneP(m.getCodice(),800,3);
            unictest.confermaTemplateP();
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (EmployeeNotAllowedException e) {
            fail("Eccezione inaspettata");
        }


        //lancio la simulazione che dovrebbe tornare null
        com.ing_sw_2022.app.Test t=null;

        try {
            t=unictest.avviaSimulazione(mappaTemplate.get(mappaTemplate.lastKey()).getId());
            fail("Eccezione non avvenuta"); //mi aspetto che l'istruzione avviaSimulazione lanci un'eccezione, se arrivo qui considero fallito il test

        } catch (NotEnoughQuestionsException e) {
            assertNull(t);
        } catch (CloneNotSupportedException e) {
            fail("Eccezione inaspettata");
        } catch (EmployeeNotAllowedException e) {
            fail("Eccezione inaspettata");
        }

        mappaTemplate.remove(mappaTemplate.lastKey());
        //creo un template che richiede quesiti con 100 risposte (mi aspetto che l'avvio della simulazione fallisca)
        try {
            unictest.nuovoTemplateP("Test template personalizzato");
            unictest.inserisciInfoTemplateP((float)1.0,(float)0.0,(float)0.0,100,1,2,1);
            unictest.creaSezioneP(m.getCodice(),1,3);
            unictest.confermaTemplateP();
        } catch (Exception e) {
            fail("Eccezione inaspettata");
        }

        //lancio la simulazione che dovrebbe tornare null
        try{
            t=unictest.avviaSimulazione(mappaTemplate.get(mappaTemplate.lastKey()).getId());
            fail("Eccezione non avvenuta"); //mi aspetto che l'istruzione avviaSimulazione lanci un'eccezione, se arrivo qui considero fallito il test
        }catch (Exception e){
            assertNull(t);
        }
        mappaTemplate.remove(mappaTemplate.lastKey());
    }
    @Test
    void testVisualizzaTemplate() {
        ArrayList<Template> listaTemplate = null;
        try {
            listaTemplate = unictest.visualizzaTemplate();
        } catch (Exception e) {
            fail("Eccezione inaspettata");
        }
        assertTrue(listaTemplate.size()>0);
    }


    @Test
    void testSelezionaRisposta() {
        TreeMap<String,QuesitoReale> mappaQuesiti=tp1.getTestCorrente().getMappaQuesiti();
        QuesitoReale qr = mappaQuesiti.get(mappaQuesiti.lastKey());
        TreeMap<String,Risposta> mappaRisposte=qr.getQuesitoDescrizione().getRisposte();
        Risposta r=mappaRisposte.get(mappaRisposte.lastKey());
        try {
            unictest.selezionaRisposta(qr.getId(),r.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(qr.getRisposteDate().values().size(),1);
        try {
            unictest.selezionaRisposta(qr.getId(),r.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(qr.getRisposteDate().values().size(),0);
    }

    @Test
    void testTerminaSimulazione() {
        com.ing_sw_2022.app.Test t= null;
        try {
            t = unictest.terminaSimulazione();
        } catch (EmployeeNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        assertNotNull(t);
        assertNotNull(t.getPunteggioComplessivo());
        assertNull(((Studente)unictest.getUtenteAutenticato()).getTemplateSelezionato());
    }
}
