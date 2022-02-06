package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.*;

public class Tutor extends Impiegato implements Serializable {
    private HashMap<String, Materia> materieInsegnate;
    private Materia materiaCorrente;
    private static long serialVersionUID = 1;

    public Tutor(String nome, String cognome, String cf) {
        super(nome, cognome, cf);
        this.materieInsegnate = new HashMap<>();
    }

    public Impiegato getImpiegato() {
        return null;
    }

    public Impiegato setImpiegato(Impiegato impiegato) {
        return null;
    }

    public String whoAmI() {
        String chiSonoIo = "Tutor";
        System.out.println(chiSonoIo + " e basta così.");
        return chiSonoIo;
    }

    @Override
    public Impiegato rendiAmministratore(Impiegato imp) {
        System.out.println("Non lo posso fare. Non sono un Amministratore.");
        return imp;
    }

    @Override
    public Impiegato rendiTutorSimulazione(Impiegato imp) {
        System.out.println("Non lo posso fare. Non sono un Amministratore.");
        return imp;
    }

    @Override
    public Impiegato rimuoviAmministratore(Impiegato imp) {
        System.out.println("Non lo posso fare. Non sono un Amministratore.");
        return imp;
    }

    @Override
    public Impiegato rimuoviTutorSimulazione(Impiegato imp) {
        System.out.println("Non lo posso fare. Non sono un Amministratore.");
        return imp;
    }

    @Override
    public void addMateriaInsegnata(Materia m) {
        materieInsegnate.put(m.getCodice(), m);
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
    public TemplatePersonalizzato getTemplatePersonalizzatoCorrente() throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public TemplateUfficiale getTemplateUfficialeCorrente() throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di Amministratore");
    }

    @Override
    public TreeMap<String, TemplatePersonalizzato> getMappaTemplatePersonalizzati() throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public TreeMap<String, Template> getMappaTemplateTestScritti() throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }


    @Override
    public String toString() {
        return "Tutor{" +
                "nome='" + super.getNome() + '\'' +
                ", cognome='" + super.getCognome() + '\'' +
                ", cf='" + super.getCf() + '\'' +
                ", materieInsegnate=" + materieInsegnate +
                "}\n";
    }

    //////////////////////////////////////////////METODI DCD//////////////////////////////////////////////
    /////////////////////////////UC7 NUOVO QUESITO///////////////////////////////
    @Override
    public void nuovoQuesito(String codiceMateria) {
        materiaCorrente = materieInsegnate.get(codiceMateria);
        materiaCorrente.nuovoQuesito(this);
    }

    @Override
    public void inserisciFonte(String fonte) {
        materiaCorrente.inserisciFonte(fonte);
    }

    @Override
    public void inserisciTesto(String testo) {
        materiaCorrente.inserisciTesto(testo);
    }

    @Override
    public void inserisciRisposta(String testo, boolean valore) {
        materiaCorrente.inserisciRisposta(testo, valore);
    }

    @Override
    public void inserisciDifficoltà(int difficoltà) {
        materiaCorrente.inserisciDifficoltà(difficoltà);
    }

    @Override
    public void confermaQuesito(Visibilità v) {
        materiaCorrente.confermaQuesito(v);
        materiaCorrente = null;
    }

    /////////////////////////////UC2/A CREA TEMPLATE DI TEST UFFICIALE///////////////////////////////
    @Override
    public void nuovoTemplateU(String nome) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di Amministratore");
    }

    @Override
    public void inserisciInfoTemplateU(String fonte, float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoTotale) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di Amministratore");
    }

    @Override
    public void creaSezioneU(String nomeMateria, int numQuesiti) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di Amministratore");
    }

    @Override
    public void confermaTemplateU() throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di Amministratore");
    }

    ////////////////////UC2/T CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////

    @Override
    public void nuovoTemplateP(String nome) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public void inserisciInfoTemplateP(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public void creaSezioneP(Materia m, int numQuesiti) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public void confermaTemplateP() throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }


    ////////////////////////////UC9 COMPONI TEST PER SIMULAZIONE CARTACEA//////////////////

    @Override
    public ArrayList<TemplatePersonalizzato> visualizzaTemplateTutor() throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public List<Sezione> creaTestCartaceo(String idTemplate) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public ArrayList<QuesitoDescrizione> visualizzaQuesiti(String idSezione) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public void inserisciQuesiti(List<String> listaIdQuesiti) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public void stampaTest(String nomeFile) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    ////////////////////////////UC10 CORREGGI SIMULAZIONI CARTACEO////////////////////////
    @Override
    public Map<String, String> recuperaInfoTestCartaceo(String fileName) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public Test correggiTestCartaceo(String cfStudente, String cfTutor, String idTest) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public Test confermaCorrezione() throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    @Override
    public void selezionaRisposta(String idQuesitoReale, String idRisposta) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di TutorSimulazione");
    }

    ///////////////////////UC6 NUOVO TUTOR//////////////////////7
    @Override
    public Impiegato nuovoTutor(String cf, String nome, String cognome) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di Amministratore");
    }

    ///////////////////////UC6 NUOVO STUDENTE//////////////////////7
    @Override
    public Studente nuovoStudente(String cf, String nome, String cognome) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di Amministratore");

    }

    ///////////////////////UC AGGIUNGI MATERIA INSEGNATA//////////////////////
    @Override
    public void aggiungiMateriaInsegnata(Impiegato tutor, String nomeMateria) throws NotAllowedException {
        throw new NotAllowedException("Non ho i permessi di Amministratore");
    }
}
