package com.ing_sw_2022.app;

import com.ing_sw_2022.app.eccezioni.EmployeeNotAllowedException;
import com.ing_sw_2022.app.eccezioni.UserNotFoundException;
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
        try {
            unictest.setUtenteAutenticato("VRDLGI99R21C351J"); //autentico uno studente
        } catch (UserNotFoundException e) {
            fail("Eccezione inaspettata");
        }
    }

    @Test
    @BeforeEach
    void testNuovoTemplate(){
        try {
            unictest.nuovoTemplateP("Test template personalizzato"); //inizializzo il template corrente per i test successivi
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
            /*non mi aspetto che lanci un'eccezione perchè ho autenticato uno studente, che dovrebbe
             * avere sempre i permessi per creare un template personalizzato. Invece, se avessi autenticato
             * un impiegato, lancerebbe un'eccezione qualora quell'impiegato non fosse un Tutor di Simulazione
             * poichè solo quest'ultimo ha i permessi per creare un template personalizzato.*/
        }
        TemplatePersonalizzato tp=((Studente)unictest.getUtenteAutenticato()).getTemplateCorrente();
        assertNotNull(tp);
        assertEquals(tp.getNome(),"Test template personalizzato");
    }

    @Test
    void testInserisciInfoTemplate() {
        TemplatePersonalizzato tp=((Studente)unictest.getUtenteAutenticato()).getTemplateCorrente();
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
        TemplatePersonalizzato tp=((Studente)unictest.getUtenteAutenticato()).getTemplateCorrente();
        int i=0;
        for(Materia m : unictest.getMappaMaterie().values()){
            try {
                unictest.creaSezioneP(m.getCodice(),10,3);
            } catch (EmployeeNotAllowedException e) {
                fail("Eccezione inaspettata");
            }
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
        try {
            unictest.confermaTemplateP(); //il template corrente viene eliminato
        } catch (NotAllowedException e) {
            fail("Eccezione inaspettata");
        }
        assertNull(((Studente)unictest.getUtenteAutenticato()).getTemplateCorrente());
        assertTrue(((Studente)unictest.getUtenteAutenticato()).getMappaTemplatePersonalizzati().size()>0);
    }

}
