package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Studente extends Utente implements Serializable {
    private TemplatePersonalizzato templateCorrente;
    private TreeMap<String, TemplatePersonalizzato> mappaTemplatePersonalizzati;
    private Template templateSelezionato;
    private TreeMap<String, Template> mappaTemplateTestSvolti;
    private static final long serialVersionUID = 1;

    public Studente(String nome, String cognome, String cf) {
        super(nome,cognome,cf);
        mappaTemplatePersonalizzati = new TreeMap<>();
        mappaTemplateTestSvolti = new TreeMap<>();
    }

    public Map<String, TemplatePersonalizzato> getMappaTemplatePersonalizzati() {
        return mappaTemplatePersonalizzati;
    }

    public TemplatePersonalizzato getTemplateCorrente() {
        return templateCorrente;
    }

    public Template getTemplateSelezionato() {
        return templateSelezionato;
    }

    public TreeMap<String, Template> getMappaTemplateTestSvolti() {
        return mappaTemplateTestSvolti;
    }

    @Override
    public String toString() {
        return "Studente{" +
                "nome='" + super.getNome() + '\'' +
                ", cognome='" + super.getCognome() + '\'' +
                ", cf='" + super.getCf() + '\'' +
                ", mappaTemplatePersonalizzati=" + mappaTemplatePersonalizzati +
                '}';
    }

    /////////////////////////////////////////////METODI DCD///////////////////////////////////////////////
               ////////////////////UC2 CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////
    public void nuovoTemplateP(String nome){
        String id = "0";
        if(!mappaTemplatePersonalizzati.isEmpty()) id = String.valueOf(Integer.parseInt(mappaTemplatePersonalizzati.lastKey())+1);
        templateCorrente = new TemplatePersonalizzato(id, nome);
    }

    public void inserisciInfoTemplateP(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio){
        templateCorrente.setInfoTemplate(puntiCorretta,puntiErrata,puntiNonData,numRisposte,minRisposteCorrette,maxRisposteCorrette);
        templateCorrente.setTempoMedio(tempoMedio);
    }

    public void creaSezioneP(Materia m, int numQuesiti, int difficoltàMedia){
        templateCorrente.creaSezione(m,numQuesiti,difficoltàMedia);
    }

    public void confermaTemplateP(){
        mappaTemplatePersonalizzati.put(templateCorrente.getId(),templateCorrente);
        templateCorrente=null;
    }

               ////////////////////UC1 AVVIA SIMULAZIONE/////////////////////
    public ArrayList<TemplatePersonalizzato> visualizzaTemplate(){
        ArrayList<TemplatePersonalizzato> listaTemplate = new ArrayList<>(mappaTemplatePersonalizzati.values());
        return listaTemplate;
    }
    public Test avviaSimulazione(String idTemplate) throws NotEnoughQuestionsException, CloneNotSupportedException {
        /*
         * Quando avvio una simulazione online devo clonare il template ufficiale nella mia
         * mappaTemplateTestSvolti per salvare il Test nella mappaTest del clone.
         * Questo perchè altrimenti il template ufficiale avrebbe nella sua mappaTest i test svolti
         * da tutti gli studenti. Invece, facendo un clone, esso apparterrà solo allo studente che
         * effettua il test e nella mappaTest del template clone ci saranno solo i suoi test.
         * Se invece il test è basato su un TemplatePersonalizzato non ho bisogno di fare un clone
         * dell'oggetto perché il TemplatePersonalizzato l'ho creato io e dunque posso riempirne
         * la mappaTest con i Test da me effettuati.
        */
        Template template=mappaTemplatePersonalizzati.get(idTemplate);
        if(template==null) {
            boolean found=false;
            for(Template te:mappaTemplateTestSvolti.values()){
                if(te.getId().equals(idTemplate)){
                    template=te;
                    found=true;
                    break;
                }
            }
            if(!found) template = (Template) UniCTest.getInstance().getMappaTemplateUfficiali().get(idTemplate).clone();
        }
        templateSelezionato=template;
        Test t = template.avviaSimulazione();
        return t;
    }

    public void selezionaRisposta(String idQuesitoReale, String idRisposta){
        templateSelezionato.selezionaRisposta(idQuesitoReale, idRisposta);
    }

    public Test terminaSimulazione(){
        /* cerco il template selezionato nella mappaTemplateTestSvolti, se non
        * è presente vuol dire che non ho mai svolto un test basato su quel template,
        * dunque devo aggiungere il template in mappaTemplateTestSvolti. Dopo posso
        * chiamare terminaSimulazione() su templateSelezionato che provvederà ad
        * aggiungere il test nella sua mappaTest */
        boolean found=false;
        for(Template te:mappaTemplateTestSvolti.values()){
            if(te.getId().equals(templateSelezionato.getId())){
                found=true;
                break;
            }
        }
        if(!found) mappaTemplateTestSvolti.put(templateSelezionato.getId(),templateSelezionato);
        Test t=templateSelezionato.terminaSimulazione();
        templateSelezionato=null;
        return t;
    }


}
