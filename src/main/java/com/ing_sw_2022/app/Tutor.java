package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.*;

public class Tutor extends Impiegato implements Serializable{
    private HashMap<String,Materia> materieInsegnate;
    private Materia materiaCorrente;
    private static long serialVersionUID = 1;

    public Tutor(String nome, String cognome, String cf) {
        super(nome,cognome,cf);
        this.materieInsegnate = new HashMap<>();
    }

    public Impiegato getImpiegato() {
        return null;
    }

    public Impiegato setImpiegato(Impiegato impiegato) {
        return null;
    }

    public String whoAmI(){
        String chiSonoIo = "Tutor";
        System.out.println(chiSonoIo + " e basta così.");
        return chiSonoIo;
    }

    @Override
    public Impiegato rendiAmministratore(Impiegato imp){
        System.out.println("Non lo posso fare. Non sono un Amministratore.");
        return imp;
    }
    @Override
    public Impiegato rendiTutorSimulazione(Impiegato imp){
        System.out.println("Non lo posso fare. Non sono un Amministratore.");
        return imp;
    }
    @Override
    public Impiegato rimuoviAmministratore(Impiegato imp){
        System.out.println("Non lo posso fare. Non sono un Amministratore.");
        return imp;
    }
    @Override
    public Impiegato rimuoviTutorSimulazione(Impiegato imp){
        System.out.println("Non lo posso fare. Non sono un Amministratore.");
        return imp;
    }

    @Override
    public void addMateriaInsegnata(Materia m){
        materieInsegnate.put(m.getCodice(),m);
    }

    @Override
    public List<Materia> getMaterieInsegnate() {
        return new ArrayList<>(materieInsegnate.values());
    }

    @Override
    public Materia getMateriaCorrente() {
        return materiaCorrente;
    }

    @Override
    public TemplatePersonalizzato getTemplatePersonalizzatoCorrente() throws Exception{
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public TemplateUfficiale getTemplateUfficialeCorrente() throws Exception{
        throw new Exception("Non ho i permessi di Amministratore");
    }

    @Override
    public TreeMap<String, TemplatePersonalizzato> getMappaTemplatePersonalizzati() throws Exception{
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public TreeMap<String, Template> getMappaTemplateTestScritti() throws Exception {
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }


    @Override
    public String toString() {
        return "\nTutor{" +
                "nome='" + super.getNome() + '\'' +
                ", cognome='" + super.getCognome() + '\'' +
                ", cf='" + super.getCf() + '\'' +
                ", materieInsegnate=" + materieInsegnate +
                '}';
    }
    //////////////////////////////////////////////METODI DCD//////////////////////////////////////////////
                /////////////////////////////UC7 NUOVO QUESITO///////////////////////////////
    @Override
    public void nuovoQuesito(String codiceMateria){
        materiaCorrente=materieInsegnate.get(codiceMateria);
        materiaCorrente.nuovoQuesito(this);
    }

    @Override
    public void inserisciFonte(String fonte){
        materiaCorrente.inserisciFonte(fonte);
    }

    @Override
    public void inserisciTesto(String testo){
        materiaCorrente.inserisciTesto(testo);
    }

    @Override
    public void inserisciRisposta(String testo, boolean valore){
        materiaCorrente.inserisciRisposta(testo, valore);
    }

    @Override
    public void inserisciDifficoltà(int difficoltà){
        materiaCorrente.inserisciDifficoltà(difficoltà);
    }

    @Override
    public void confermaQuesito(Visibilità v){
        materiaCorrente.confermaQuesito(v);
        materiaCorrente = null;
    }
            /////////////////////////////UC2/A CREA TEMPLATE DI TEST UFFICIALE///////////////////////////////
    @Override
    public void nuovoTemplateU(String nome) throws Exception{
        throw new Exception("Non ho i permessi di Amministratore");
    }

    @Override
    public void inserisciInfoTemplateU(String fonte, float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoTotale) throws Exception{
        throw new Exception("Non ho i permessi di Amministratore");
    }

    @Override
    public void creaSezioneU(String nomeMateria, int numQuesiti) throws Exception{
        throw new Exception("Non ho i permessi di Amministratore");
    }

    @Override
    public void confermaTemplateU() throws Exception{
        throw new Exception("Non ho i permessi di Amministratore");
    }

    ////////////////////UC2/T CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////

    @Override
    public void nuovoTemplateP(String nome) throws Exception {
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public void inserisciInfoTemplateP(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio) throws Exception {
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public void creaSezioneP(Materia m, int numQuesiti) throws Exception {
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public void confermaTemplateP() throws Exception {
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }


    ////////////////////////////UC9 COMPONI TEST PER SIMULAZIONE CARTACEA//////////////////

    @Override
    public ArrayList<TemplatePersonalizzato> visualizzaTemplate() throws Exception{
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public List<Sezione> creaTestCartaceo(String idTemplate) throws Exception {
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public ArrayList<QuesitoDescrizione> visualizzaQuesiti(String idSezione) throws Exception {
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public void inserisciQuesiti(List<String> listaIdQuesiti) throws Exception {
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public void stampaTest(String nomeFile) throws Exception {
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }
    ////////////////////////////UC10 CORREGGI SIMULAZIONI CARTACEO////////////////////////
    @Override
    public Map<String,String> recuperaInfoTestCartaceo(String fileName) throws Exception{
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public Test correggiTestCartaceo(String cfStudente, String cfTutor, String idTest) throws Exception {
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public Test confermaCorrezione() throws Exception {
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }
    @Override
    public void selezionaRisposta(String idQuesitoReale, String idRisposta) throws Exception{
        throw new Exception("Non ho i permessi di TutorSimulazione");
    }
}
