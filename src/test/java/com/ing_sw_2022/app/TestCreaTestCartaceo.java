package com.ing_sw_2022.app;

import com.ing_sw_2022.app.eccezioni.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class TestCreaTestCartaceo {

    static UniCTest unictest;
    static Impiegato imp;
    static TemplatePersonalizzato tp1;
    static QuesitoDescrizione qd;
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
        imp=(Impiegato) unictest.getUtenteAutenticato();
        try {
            unictest.nuovoQuesito(m.getCodice());
            qd=m.getQuesitoCorrente();
            unictest.inserisciTesto("Quanto fa 2+2?");
            unictest.inserisciDifficoltà(3);
            unictest.inserisciRisposta("4",true);
            unictest.inserisciRisposta("5",false);
            unictest.confermaQuesito("p2");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        unictest.setTutorSimulazione("RSSMRA80A01C351O");
        try {
            unictest.nuovoTemplateP("Test template personalizzato");
            unictest.inserisciInfoTemplateP((float)1.0,(float)0.0,(float)0.0,2,1,2,1);
            unictest.creaSezioneP(m.getCodice(),1);
            unictest.confermaTemplateP();
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        try {
            mappaTemplate= ((Impiegato)unictest.getUtenteAutenticato()).getMappaTemplatePersonalizzati();
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        tp1 = mappaTemplate.get(mappaTemplate.lastKey());
    }
    @Test
    void testVisualizzaTemplateTutor() {
        ArrayList<Template> listaTemplate = null;
        try {
            listaTemplate = (ArrayList<Template>) unictest.visualizzaTemplateTutor();
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        assertTrue(listaTemplate.size()>0);
    }
    @Test
    @BeforeEach
    void testCreaTestCartaceo() {
        //adesso creo un test con il template creato da initTest (ha parametri idonei per la ricerca dei quesiti)
        try {
            List<Sezione> listaSezioni = unictest.creaTestCartaceo(tp1.getId());
            assertTrue(listaSezioni.size()>0);
            assertNotNull(((Impiegato)unictest.getUtenteAutenticato()).getTemplateSelezionato());
            assertNotNull(((Impiegato)unictest.getUtenteAutenticato()).getTemplateSelezionato().getTestCorrente());
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
    }

    @Test
    void testEccezioniCreaTestCartaceo(){
        Materia m = unictest.getMappaMaterie().get("MAT01");//matematica
        //creo un template che richiede 800 quesiti di matematica (mi aspetto che l'avvio della simulazione fallisca)
        try {
            unictest.nuovoTemplateP("Test template personalizzato");
            unictest.inserisciInfoTemplateP((float)1.0,(float)0.0,(float)0.0,2,1,2,1);
            unictest.creaSezioneP(m.getCodice(),800);
            unictest.confermaTemplateP();
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        List<Sezione> listaSezioni=null;
        try {
            listaSezioni = unictest.creaTestCartaceo(mappaTemplate.lastKey()); //utilizzo il nuovo template
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        //cerco quesiti da inserire nella sezione di matematica del template appena creato
        List<QuesitoDescrizione> listaQuesiti=null;
        try {
            listaQuesiti=unictest.visualizzaQuesiti(mappaTemplate.get(mappaTemplate.lastKey()).getListaSezioni().get(0).getId());
            fail("Eccezione non avvenuta");//mi aspetto che l'istruzione visualizzaQuesiti lanci un'eccezione
        } catch (NotEnoughQuestionsException e) {
            assertNull(listaQuesiti);
        }
        catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        mappaTemplate.remove(mappaTemplate.lastKey());
        //creo un template che richiede quesiti con 100 risposte (mi aspetto che la ricerca di quesiti fallisca)
        try {
            unictest.nuovoTemplateP("Test template personalizzato");
            unictest.inserisciInfoTemplateP((float)1.0,(float)0.0,(float)0.0,100,1,2,1);
            unictest.creaSezioneP(m.getCodice(),1);
            unictest.confermaTemplateP();
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        try {
            listaSezioni = unictest.creaTestCartaceo(mappaTemplate.lastKey()); //utilizzo il nuovo template
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        //cerco quesiti da inserire nella sezione di matematica del template appena creato
        try{
            listaQuesiti=unictest.visualizzaQuesiti(mappaTemplate.get(mappaTemplate.lastKey()).getListaSezioni().get(0).getId());
            fail("Eccezione non avvenuta"); //mi aspetto che l'istruzione avviaSimulazione lanci un'eccezione, se arrivo qui considero fallito il test
        }catch (NotAllowedException e){
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (NotEnoughQuestionsException e) {
            assertNull(listaQuesiti);
        }
        mappaTemplate.remove(mappaTemplate.lastKey());
    }

    @Test
    void testInserisciQuesiti(){
        List<QuesitoDescrizione> listaQuesiti=null;
        try {
            listaQuesiti=unictest.visualizzaQuesiti(tp1.getListaSezioni().get(0).getId());
            List<String> listaIdQuesiti= new ArrayList<>();
            listaIdQuesiti.add(listaQuesiti.get(listaQuesiti.size()-1).getId());
            unictest.inserisciQuesiti(listaIdQuesiti);
            assertTrue(((Impiegato)unictest.getUtenteAutenticato()).getTemplateSelezionato().getTestCorrente().getMappaQuesiti().size()>0);
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (QuestionNotFoundException e) {
            fail("Eccezione inaspettata");
        } catch (TemplateSectionException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (NotEnoughQuestionsException e) {
            fail("Eccezione inaspettata");
        }
    }

    @Test
    void testStampaTest() {
        try {
            unictest.stampaTest("testingCreazioneTest");
            assertNull(((Impiegato)unictest.getUtenteAutenticato()).getTemplateSelezionato());
            File f = new File("testingCreazioneTest.pdf");
            assertTrue(f.exists());
            assertTrue(!f.isDirectory());
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        }

    }
}
