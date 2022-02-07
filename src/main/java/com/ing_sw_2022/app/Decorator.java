package com.ing_sw_2022.app;

import com.ing_sw_2022.app.eccezioni.NotEnoughQuestionsException;
import com.ing_sw_2022.app.eccezioni.QuestionNotFoundException;
import com.ing_sw_2022.app.eccezioni.TemplateSectionException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Decorator extends Impiegato implements Serializable{
    private static long serialVersionUID = 1;
    protected Impiegato impiegato;

    public Decorator(Impiegato imp){
        super(imp.getNome(), imp.getCognome(), imp.getCf());
        impiegato=imp;
    }
    @Override
    public abstract String whoAmI();
    @Override
    public abstract boolean isAmministratore();
    @Override
    public abstract boolean isTutorSimulazione();

    @Override
    public Impiegato getImpiegato() {
        return impiegato;
    }
    @Override
    public Impiegato setImpiegato(Impiegato impiegato) {
        this.impiegato = impiegato;
        return impiegato;
    }
    @Override
    public Impiegato rendiAmministratore(Impiegato imp) throws NotAllowedException {
        return impiegato.rendiAmministratore(imp);
    }
    @Override
    public Impiegato rendiTutorSimulazione(Impiegato imp) throws NotAllowedException {
        return impiegato.rendiTutorSimulazione(imp);
    }
    @Override
    public Impiegato rimuoviAmministratore(Impiegato imp) throws NotAllowedException{
        return impiegato.rimuoviAmministratore(imp);
    }
    @Override
    public Impiegato rimuoviTutorSimulazione(Impiegato imp) throws NotAllowedException{
        return impiegato.rimuoviTutorSimulazione(imp);
    }

    @Override
    public TemplatePersonalizzato getTemplatePersonalizzatoCorrente() throws NotAllowedException {
        return impiegato.getTemplatePersonalizzatoCorrente();
    }

    @Override
    public TemplateUfficiale getTemplateUfficialeCorrente() throws NotAllowedException {
        return impiegato.getTemplateUfficialeCorrente();
    }

    @Override
    public TreeMap<String, TemplatePersonalizzato> getMappaTemplatePersonalizzati() throws NotAllowedException{
        return impiegato.getMappaTemplatePersonalizzati();
    }

    @Override
    public void addMateriaInsegnata(Materia m){
        impiegato.addMateriaInsegnata(m);
    }

    @Override
    public List<Materia> getMaterieInsegnate(){
        return impiegato.getMaterieInsegnate();
    }

    @Override
    public Materia getMateriaCorrente(){
        return impiegato.getMateriaCorrente();
    }

    @Override
    public TreeMap<String, Template> getMappaTemplateTestScritti() throws NotAllowedException{
        return impiegato.getMappaTemplateTestScritti();
    }

    /*@Override
    public String toString(){
        return impiegato.toString();
    }*/
    //////////////////////////////////////////////METODI DCD//////////////////////////////////////////////
    /*
    * ALGORITMO DI FUNZIONAMENTO: RESPONSABILITA' IN CASCATA
    * Chi non reimplementa un metodo del Decorator non lo fa perché non è in grado di soddisfare la richiesta.
    * Quindi, se non riesco a soddisfare la richiesta, rinvio la responsabilità a cascata sul Decorator che ho all'interno.
    * Se nessuno riesce a soddisfare la richiesta, essa arriverà al Tutor. Se questi non riesce a sua volta a soddisfare la richiesta, allora viene lanciata (e gestita) un'eccezione
    * L'eccezione indica che qualcuno ha chiamato un metodo pur non avendo le responsabilità adeguate per farlo.
    * */
    /////////////////////////////UC7 NUOVO QUESITO///////////////////////////////
    @Override
    public void nuovoQuesito(String codiceMateria){
        impiegato.nuovoQuesito(codiceMateria);
    }

    @Override
    public void inserisciFonte(String fonte){
        impiegato.inserisciFonte(fonte);
    }

    @Override
    public void inserisciTesto(String testo){
        impiegato.inserisciTesto(testo);
    }

    @Override
    public void inserisciRisposta(String testo, boolean valore){
        impiegato.inserisciRisposta(testo,valore);
    }

    @Override
    public void inserisciDifficoltà(int difficoltà){
        impiegato.inserisciDifficoltà(difficoltà);
    }

    @Override
    public void confermaQuesito(Visibilità v){
        impiegato.confermaQuesito(v);
    }
    /////////////////////////////UC2/A CREA TEMPLATE DI TEST UFFICIALE///////////////////////////////
    public void nuovoTemplateU(String nome) throws NotAllowedException {
        impiegato.nuovoTemplateU(nome);
    }

    public void inserisciInfoTemplateU(String fonte,float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoTotale) throws NotAllowedException {
        impiegato.inserisciInfoTemplateU(fonte, puntiCorretta, puntiErrata, puntiNonData, numRisposte, minRisposteCorrette, maxRisposteCorrette, tempoTotale);
    }

    public void creaSezioneU(String nomeMateria, int numQuesiti) throws NotAllowedException {
        impiegato.creaSezioneU(nomeMateria,numQuesiti);
    }

    public void confermaTemplateU() throws NotAllowedException {
        impiegato.confermaTemplateU();
    }
    ////////////////////UC2/T CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////
    public void nuovoTemplateP(String nome) throws NotAllowedException {
        impiegato.nuovoTemplateP(nome);
    }

    public void inserisciInfoTemplateP(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio) throws NotAllowedException {
        impiegato.inserisciInfoTemplateP(puntiCorretta, puntiErrata, puntiNonData, numRisposte, minRisposteCorrette, maxRisposteCorrette, tempoMedio);
    }

    public void creaSezioneP(Materia m, int numQuesiti) throws NotAllowedException {
        impiegato.creaSezioneP(m, numQuesiti);
    }

    public void confermaTemplateP() throws NotAllowedException {
        impiegato.confermaTemplateP();
    }
    //////////////////////UC9 COMPONI TEST PER SIMULAZIONE CARTACEA/////////////////
    @Override
    public ArrayList<TemplatePersonalizzato> visualizzaTemplateTutor() throws NotAllowedException{
        return impiegato.visualizzaTemplateTutor();
    }
    @Override
    public List<Sezione> creaTestCartaceo(String idTemplate) throws NotAllowedException{
        return impiegato.creaTestCartaceo(idTemplate);
    }
    @Override
    public ArrayList<QuesitoDescrizione> visualizzaQuesiti(String idSezione) throws NotAllowedException, NotEnoughQuestionsException {
        return impiegato.visualizzaQuesiti(idSezione);
    }
    @Override
    public void inserisciQuesiti(List<String> listaIdQuesiti) throws NotAllowedException, QuestionNotFoundException, TemplateSectionException {
        impiegato.inserisciQuesiti(listaIdQuesiti);
    }
    @Override
    public void stampaTest(String nomeFile) throws NotAllowedException{
        impiegato.stampaTest(nomeFile);
    }
    ////////////////////////////UC10 CORREGGI SIMULAZIONI CARTACEO////////////////////////
    @Override
    public Map<String,String> recuperaInfoTestCartaceo(String fileName) throws NotAllowedException{
        return impiegato.recuperaInfoTestCartaceo(fileName);
    }

    @Override
    public Test correggiTestCartaceo(String cfStudente, String cfTutor, String idTest) throws NotAllowedException {
        return impiegato.correggiTestCartaceo(cfStudente, cfTutor, idTest);
    }

    @Override
    public Test confermaCorrezione() throws NotAllowedException {
        return impiegato.confermaCorrezione();
    }
    @Override
    public void selezionaRisposta(String idQuesitoReale, String idRisposta) throws NotAllowedException{
        impiegato.selezionaRisposta(idQuesitoReale, idRisposta);
    }
    ///////////////////////UC6 NUOVO TUTOR//////////////////////7
    @Override
    public Impiegato nuovoTutor(String cf, String nome, String cognome) throws NotAllowedException {
        return impiegato.nuovoTutor(cf, nome, cognome);
    }
    ///////////////////////UC5 NUOVO STUDENTE//////////////////////7
    @Override
    public Studente nuovoStudente(String cf, String nome, String cognome) throws NotAllowedException{
        return impiegato.nuovoStudente(cf, nome, cognome);
    }
    ///////////////////////UC AGGIUNGI MATERIA INSEGNATA//////////////////////
    @Override
    public void aggiungiMateriaInsegnata(Impiegato tutor, String nomeMateria) throws NotAllowedException {
        impiegato.aggiungiMateriaInsegnata(tutor, nomeMateria);
    }
}
