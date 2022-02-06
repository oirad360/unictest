package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TutorSimulazione extends Decorator implements Serializable {
    private static long serialVersionUID = 1;
    private TemplatePersonalizzato templateCorrente;
    private TreeMap<String, TemplatePersonalizzato> mappaTemplatePersonalizzati;
    private TreeMap<String, Template> mappaTemplateTestScritti;
    private Template templateSelezionato;
    private Lettore lettore;

    public TutorSimulazione(Impiegato imp) {
        super(imp);
        mappaTemplatePersonalizzati = new TreeMap<>();
        mappaTemplateTestScritti = new TreeMap<>();
        lettore = new TesseractObjAdapter();
    }
    @Override
    public String whoAmI(){
        String chiSonoIo = "TutorSimulazione";
        System.out.println(chiSonoIo + ", ");
        return chiSonoIo+this.impiegato.whoAmI();
    }
    @Override
    public void rendiAmministratore(Impiegato imp){
        this.impiegato.rendiAmministratore(imp);
    }
    @Override
    public void rendiTutorSimulazione(Impiegato imp){
        this.impiegato.rendiAmministratore(imp);
    }
    @Override
    public void rimuoviAmministratore(Impiegato imp){
        this.impiegato.rendiAmministratore(imp);
    }
    @Override
    public void rimuoviTutorSimulazione(Impiegato imp){
        this.impiegato.rendiAmministratore(imp);
    }

    public TemplatePersonalizzato getTemplatePersonalizzatoCorrente() {
        return templateCorrente;
    }

    public TreeMap<String, TemplatePersonalizzato> getMappaTemplatePersonalizzati() {
        return mappaTemplatePersonalizzati;
    }

    public TreeMap<String, Template> getMappaTemplateTestScritti() {
        return mappaTemplateTestScritti;
    }
    @Override
    public String toString() {
        return impiegato.toString()+"\nSono un Tutor di Simulazione";
    }
    ////////////////////////////////////////////METODI DCD//////////////////////////////////////////////////
                ////////////////////UC2/T CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////
    public void nuovoTemplateP(String nome){
        String id = "0";
        if(!mappaTemplatePersonalizzati.isEmpty()) id = String.valueOf(Integer.parseInt(mappaTemplatePersonalizzati.lastKey())+1);
        templateCorrente = new TemplatePersonalizzato(id, nome);
    }

    public void inserisciInfoTemplateP(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio){
        templateCorrente.setInfoTemplate(puntiCorretta,puntiErrata,puntiNonData,numRisposte,minRisposteCorrette,maxRisposteCorrette);
        templateCorrente.setTempoMedio(tempoMedio);
    }

    public void creaSezioneP(Materia m, int numQuesiti){
        templateCorrente.creaSezione(m,numQuesiti);
    }

    public void confermaTemplateP(){
        mappaTemplatePersonalizzati.put(templateCorrente.getId(),templateCorrente);
        templateCorrente=null;
    }
    //////////////////////UC9 COMPONI TEST PER SIMULAZIONE CARTACEA/////////////////
    @Override
    public ArrayList<TemplatePersonalizzato> visualizzaTemplateTutor(){
        ArrayList<TemplatePersonalizzato> lista = new ArrayList<>(mappaTemplatePersonalizzati.values());
        return lista;
    }
    @Override
    public List<Sezione> creaTestCartaceo(String idTemplate) {
        /*
         * Quando creo un test cartaceo devo clonare il template ufficiale nella mia
         * mappaTemplateTestScritti per salvare il Test nella mappaTest del clone.
         * Questo perchè altrimenti il template ufficiale avrebbe nella sua mappaTest i test scritti
         * da tutti i tutor di simulazione. Invece, facendo un clone, esso apparterrà solo al tutor che
         * crea il test e nella mappaTest del template clone ci saranno solo i suoi test.
         * Se invece il test è basato su un TemplatePersonalizzato non ho bisogno di fare un clone
         * dell'oggetto perché il TemplatePersonalizzato l'ho creato io e dunque posso riempirne
         * la mappaTest con i Test da me creati.
         */
        Template template = mappaTemplatePersonalizzati.get(idTemplate);
        if(template==null) {
            boolean found=false;
            for(Template te:mappaTemplateTestScritti.values()){
                if(te.getId().equals(idTemplate)){
                    template=te;
                    found=true;
                    break;
                }
            }
            if(!found) {
                try {
                    template = (Template) UniCTest.getInstance().getMappaTemplateUfficiali().get(idTemplate).clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        templateSelezionato=template;
        return template.creaTestCartaceo();
    }

    @Override
    public ArrayList<QuesitoDescrizione> visualizzaQuesiti(String idSezione) throws NotEnoughQuestionsException {
        ArrayList<QuesitoDescrizione> lista = templateSelezionato.visualizzaQuesiti(idSezione);
        return lista;
    }

    @Override
    public void inserisciQuesiti(List<String> listaIdQuesiti) throws QuestionNotFoundException, TemplateSectionException {
        templateSelezionato.inserisciQuesiti(listaIdQuesiti);
    }

    @Override
    public void stampaTest(String nomeFile) {
        /* cerco il template selezionato nella mappaTemplateTestScritti, se non
         * è presente vuol dire che non ho mai creato un test basato su quel template,
         * dunque devo aggiungere il template in mappaTemplateTestScritti. Dopo posso
         * chiamare stampaTest() su templateSelezionato che provvederà ad
         * aggiungere il test nella sua mappaTest */
        boolean found=false;
        for(Template te:mappaTemplateTestScritti.values()){
            if(te.getId().equals(templateSelezionato.getId())){
                found=true;
                break;
            }
        }
        if(!found) {
            if(templateSelezionato instanceof TemplateUfficiale){
                try {
                    Template te=(Template) templateSelezionato.clone();
                    mappaTemplateTestScritti.put(te.getId(),te);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            } else mappaTemplateTestScritti.put(templateSelezionato.getId(),templateSelezionato);

        }
        templateSelezionato.stampaTest(nomeFile);
        templateSelezionato=null;
    }
    ////////////////////////////UC10 CORREGGI SIMULAZIONI CARTACEO////////////////////////
    @Override
    public Map<String,String> recuperaInfoTestCartaceo(String fileName) {
        return lettore.recuperaInfoTestCartaceo(fileName);
    }
    @Override
    public Test correggiTestCartaceo(String cfStudente, String cfTutor, String idTest) {
        try {
            return lettore.correggiTestCartaceo(cfStudente, cfTutor, idTest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void selezionaRisposta(String idQuesitoReale, String idRisposta){
        lettore.selezionaRisposta(idQuesitoReale, idRisposta);
    }

    @Override
    public Test confermaCorrezione(){
        return lettore.confermaCorrezione();
    }
}
