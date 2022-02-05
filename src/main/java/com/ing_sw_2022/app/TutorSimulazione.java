package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TutorSimulazione extends Decorator implements Serializable {
    private static long serialVersionUID = 1;
    private TemplatePersonalizzato templateCorrente;
    private TreeMap<String, TemplatePersonalizzato> mappaTemplatePersonalizzati;
    private TreeMap<String, Template> mappaTemplateTestScritti;
    private Template templateSelezionato;

    public TutorSimulazione(Impiegato imp) {
        super(imp);
        mappaTemplatePersonalizzati = new TreeMap<>();
        mappaTemplateTestScritti = new TreeMap<>();
    }

    public TemplatePersonalizzato getTemplatePersonalizzatoCorrente() {
        return templateCorrente;
    }

    public TreeMap<String, TemplatePersonalizzato> getMappaTemplatePersonalizzati() {
        return mappaTemplatePersonalizzati;
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
    public ArrayList<TemplatePersonalizzato> visualizzaTemplate(){
        ArrayList<TemplatePersonalizzato> lista = new ArrayList<>(mappaTemplatePersonalizzati.values());
        return lista;
    }
    @Override
    public List<Sezione> creaTestCartaceo(String idTemplate) {
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
    public ArrayList<QuesitoDescrizione> visualizzaQuesiti(String idSezione) throws Exception {
        ArrayList<QuesitoDescrizione> lista = templateSelezionato.visualizzaQuesiti(idSezione);
        return lista;
    }

    @Override
    public void inserisciQuesiti(List<String> listaIdQuesiti) throws Exception {
        templateSelezionato.inserisciQuesiti(listaIdQuesiti);
    }

    @Override
    public void stampaTest(String nomeFile) {
        templateSelezionato.stampaTest(nomeFile);
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
        templateSelezionato=null;
    }
}
