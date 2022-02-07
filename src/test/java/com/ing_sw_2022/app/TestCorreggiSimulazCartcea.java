package com.ing_sw_2022.app;

import com.ing_sw_2022.app.eccezioni.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class TestCorreggiSimulazCartcea {
    static UniCTest unictest;
    static String idTest;
    @BeforeAll
    static void initTest(){
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
        TreeMap<String,TemplatePersonalizzato> mappaTemplate=null;
        try {
            mappaTemplate= ((Impiegato)unictest.getUtenteAutenticato()).getMappaTemplatePersonalizzati();
            idTest=mappaTemplate.lastKey();

        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        TemplatePersonalizzato tp1 = mappaTemplate.get(mappaTemplate.lastKey());
        List<QuesitoDescrizione> listaQuesiti=null;
        try {
            unictest.creaTestCartaceo(tp1.getId());
            listaQuesiti=unictest.visualizzaQuesiti(tp1.getListaSezioni().get(0).getId());
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (NotEnoughQuestionsException e) {
            fail("Eccezione inaspettata");
        }

        List<String> listaIdQuesiti= new ArrayList<>();
        listaIdQuesiti.add(listaQuesiti.get(listaQuesiti.size()-1).getId());
        try {
            unictest.inserisciQuesiti(listaIdQuesiti);
            unictest.stampaTest("testingCorrezioneTest");
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (QuestionNotFoundException e) {
            fail("Eccezione inaspettata");
        } catch (TemplateSectionException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }

    }
    @Test
    void testRecuperaInfo(){
        Map<String,String> mappaInfo=null;
        try {
            mappaInfo=unictest.recuperaInfoTestCartaceo("testingCorrezioneTest");
            assertNotNull(mappaInfo.get("cfTutor"));
            assertNotNull(mappaInfo.get("cfStudente"));
            assertNotNull(mappaInfo.get("idTest"));
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
    }
    @Test
    void correggiTestCartaceo(){
        com.ing_sw_2022.app.Test t=null;
        try {
            t=unictest.correggiTestCartaceo("VRDLGI99R21C351J","RSSMRA80A01C351O",idTest);
            assertNotNull(t);
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
    }
    @Test
    void confermaCorrezione(){
        try {
            unictest.confermaCorrezione();
            assertTrue(((Studente)unictest.getMappaUtenti().get("VRDLGI99R21C351J")).getMappaTemplateTestSvolti().size()>0);
            assertNotNull(((Studente)unictest.getMappaUtenti().get("VRDLGI99R21C351J")).getMappaTemplateTestSvolti().get(idTest.split("-")[0]).getMappaTest().get(idTest.split("-")[0]));
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        } catch (StudentNotAllowedException e) {
            fail("Eccezione inaspettata");
        }
    }
}
