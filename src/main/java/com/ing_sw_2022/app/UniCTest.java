package com.ing_sw_2022.app;

import com.ing_sw_2022.app.eccezioni.*;

import java.io.*;
import java.util.*;

public class UniCTest implements Serializable{
    private static UniCTest unictest;
    private HashMap<String,Materia> mappaMaterie;
    private HashMap<String,Visibilità> mappaVisibilità;
    private Utente utenteAutenticato;
    private HashMap<String, Utente> mappaUtenti;
    private TreeMap<String, TemplateUfficiale> mappaTemplateUfficiali;
    private static final long serialVersionUID = 1;

    private UniCTest() {
        mappaMaterie = new HashMap<>();
        mappaVisibilità = new HashMap<>();
        mappaUtenti = new HashMap<>();
        mappaTemplateUfficiali = new TreeMap<>();
        loadMaterie();
        loadVisibilità();
        loadUtenti();
        serialize();
    }

    public static UniCTest getInstance() {
        if (unictest == null) {
            int res=deserialize();
            if(res==0) unictest = new UniCTest();
        }
        return unictest;
    }

    public Map<String, Materia> getMappaMaterie() {
        return mappaMaterie;
    }

    public Map<String, Visibilità> getMappaVisibilità() {
        return mappaVisibilità;
    }

    public Map<String, Utente> getMappaUtenti() {
        return mappaUtenti;
    }

    public Utente getUtenteAutenticato(){
        return utenteAutenticato;
    }

    public TreeMap<String, TemplateUfficiale> getMappaTemplateUfficiali() {
        return mappaTemplateUfficiali;
    }

    public void serialize(){
        try {
            OutputStream fout = new FileOutputStream("ser.txt");
            ObjectOutput oout = new ObjectOutputStream(fout);
            oout.writeObject(this);
            fout.close();
            oout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Integer deserialize(){
        try {
            InputStream fin=new FileInputStream("ser.txt");
            ObjectInput oin=new ObjectInputStream(fin);
            unictest=(UniCTest) oin.readObject();
            fin.close();
            oin.close();
            System.out.println("Object DeSerialization completed.");
            return 1;
        } catch (FileNotFoundException e) {
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("errore IO nella deserializzazione");
            return -1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("errore ClassNotFound nella deserializzazione");
            return -1;
        }
    }

    public void setUtenteAutenticato(String cf){
        utenteAutenticato=getMappaUtenti().get(cf);
    } //MOMENTANEO

    public Impiegato setAmministratore(String cf){
        if(mappaUtenti.get(cf) instanceof Impiegato){
            Impiegato oldImp = (Impiegato)mappaUtenti.get(cf);
            Impiegato newImp = new Amministratore(oldImp);
            mappaUtenti.replace(cf,newImp);
            if(utenteAutenticato.getCf().equals(cf)) utenteAutenticato=newImp;
            return oldImp;
        } else System.out.println(cf+" non è un impiegato");
        return null;
    } //MOMENTANEO

    public Impiegato setTutorSimulazione(String cf){
        if(mappaUtenti.get(cf) instanceof Impiegato){
            Impiegato oldImp = (Impiegato)mappaUtenti.get(cf);
            Impiegato newImp = new TutorSimulazione(oldImp);
            mappaUtenti.replace(cf,newImp);
            if(utenteAutenticato.getCf().equals(cf)) utenteAutenticato=newImp;
            return oldImp;
        } else System.out.println(cf+" non è un impiegato");
        return null;
    } //MOMENTANEO
    /////////////////////////////////////////////METODI DCD///////////////////////////////////////////////
                     ////////////////////UC7 INSERISCI QUESITO/////////////////////
    public List<Materia> visualizzaMaterieInsegnate() throws StudentNotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono inserire quesiti.");
        return ((Impiegato)utenteAutenticato).getMaterieInsegnate();
    }

    public void nuovoQuesito(String codiceMateria)throws StudentNotAllowedException{
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono inserire quesiti.");
        ((Impiegato)utenteAutenticato).nuovoQuesito(codiceMateria);
    }

    public void inserisciFonte(String fonte) throws StudentNotAllowedException{
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono inserire quesiti.");
        ((Impiegato)utenteAutenticato).inserisciFonte(fonte);
    }

    public void inserisciTesto(String testo) throws StudentNotAllowedException{
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono inserire quesiti.");
        ((Impiegato)utenteAutenticato).inserisciTesto(testo);
    }

    public void inserisciRisposta(String testo, boolean valore) throws StudentNotAllowedException{
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono inserire quesiti.");
        ((Impiegato)utenteAutenticato).inserisciRisposta(testo, valore);
    }

    public void inserisciDifficoltà(int difficoltà) throws StudentNotAllowedException{
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono inserire quesiti.");
        ((Impiegato)utenteAutenticato).inserisciDifficoltà(difficoltà);
    }

    public void confermaQuesito(String codiceVisibilità) throws StudentNotAllowedException{
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono inserire quesiti.");
        Visibilità v = mappaVisibilità.get(codiceVisibilità);
        ((Impiegato)utenteAutenticato).confermaQuesito(v);
    }

                        ////////////////////UC2 CREA TEMPLATE DI TEST PERSONALIZZATO/////////////////////

    public void nuovoTemplateP(String nome) throws NotAllowedException {
        if(utenteAutenticato instanceof Studente) ((Studente)utenteAutenticato).nuovoTemplateP(nome);
        else if(utenteAutenticato instanceof Impiegato) ((Impiegato)utenteAutenticato).nuovoTemplateP(nome);//potrebbe lanciare eccezione se l'impiegato non è tutor di simulazione
    }

    public List<Materia> inserisciInfoTemplateP(float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoMedio) throws NotAllowedException {
        if(utenteAutenticato instanceof Studente) ((Studente)utenteAutenticato).inserisciInfoTemplateP(puntiCorretta, puntiErrata, puntiNonData, numRisposte, minRisposteCorrette, maxRisposteCorrette, tempoMedio);
        else if(utenteAutenticato instanceof Impiegato) {
            //potrebbe lanciare eccezione se l'impiegato non è tutor di simulazione
            ((Impiegato)utenteAutenticato).inserisciInfoTemplateP(puntiCorretta, puntiErrata, puntiNonData, numRisposte, minRisposteCorrette, maxRisposteCorrette, tempoMedio);
        }
        List<Materia> list = new ArrayList<>(mappaMaterie.values());
        return list;
    }

    public void creaSezioneP(String codiceMateria, int numQuesiti, int difficoltàMedia) throws EmployeeNotAllowedException {
        if(utenteAutenticato instanceof Impiegato) throw new EmployeeNotAllowedException("I tutor non possono creare template per simulazioni online.");
        Materia m=mappaMaterie.get(codiceMateria);
        ((Studente)utenteAutenticato).creaSezioneP(m,numQuesiti,difficoltàMedia);
    }

    public void creaSezioneP(String codiceMateria, int numQuesiti) throws StudentNotAllowedException, NotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono creare template per simulazioni cartacee.");
        Materia m=mappaMaterie.get(codiceMateria);
        ((Impiegato)utenteAutenticato).creaSezioneP(m,numQuesiti);//potrebbe lanciare eccezione se l'impiegato non è tutor di simulazione (Exception generica, non catturata da ClassCastException)
    }

    public void confermaTemplateP() throws NotAllowedException {
        if(utenteAutenticato instanceof Studente) ((Studente)utenteAutenticato).confermaTemplateP();
        if(utenteAutenticato instanceof Impiegato) ((Impiegato)utenteAutenticato).confermaTemplateP();//potrebbe lanciare eccezione se l'impiegato non è tutor di simulazione
    }

                        ////////////////////UC1 AVVIA SIMULAZIONE/////////////////////

    public ArrayList<Template> visualizzaTemplate() throws EmployeeNotAllowedException {
        if(utenteAutenticato instanceof Impiegato) throw new EmployeeNotAllowedException("Gli impiegati non possono avviare simulazioni.");
        ArrayList<TemplatePersonalizzato> listaPersonalizzati = ((Studente)utenteAutenticato).visualizzaTemplate();
        ArrayList<TemplateUfficiale> listaUfficiali = new ArrayList<>(UniCTest.getInstance().getMappaTemplateUfficiali().values());
        ArrayList<Template> lista = new ArrayList<>();
        for(TemplatePersonalizzato t:listaPersonalizzati) lista.add(t);
        for(TemplateUfficiale t:listaUfficiali) lista.add(t);
        return lista;
    }


    public Test avviaSimulazione(String idTemplate) throws NotEnoughQuestionsException, CloneNotSupportedException, EmployeeNotAllowedException {
        if(utenteAutenticato instanceof Impiegato) throw new EmployeeNotAllowedException("Gli impiegati non possono avviare simulazioni.");
        return ((Studente)utenteAutenticato).avviaSimulazione(idTemplate);
    }

    public void selezionaRisposta(String idQuesitoReale, String idRisposta) throws NotAllowedException {
        if(utenteAutenticato instanceof Studente) ((Studente)utenteAutenticato).selezionaRisposta(idQuesitoReale, idRisposta);
        else if(utenteAutenticato instanceof Impiegato) ((Impiegato)utenteAutenticato).selezionaRisposta(idQuesitoReale, idRisposta);//potrebbe lanciare eccezione se l'impiegato non è tutor di simulazione
    }

    public Test terminaSimulazione() throws EmployeeNotAllowedException{
        if(utenteAutenticato instanceof Impiegato) throw new EmployeeNotAllowedException("Gli impiegati non possono avviare simulazioni.");
        return ((Studente)utenteAutenticato).terminaSimulazione();
    }

    ////////////////////UC2/A CREA TEMPLATE DI TEST UFFICIALE/////////////////////

    public void nuovoTemplateU(String nome) throws NotAllowedException, StudentNotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono inserire template ufficiali.");
        ((Impiegato)utenteAutenticato).nuovoTemplateU(nome);
    }

    public void inserisciInfoTemplateU(String fonte, float puntiCorretta, float puntiErrata, float puntiNonData, int numRisposte, int minRisposteCorrette, int maxRisposteCorrette, int tempoTotale) throws NotAllowedException, StudentNotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono inserire template ufficiali.");
        ((Impiegato)utenteAutenticato).inserisciInfoTemplateU(fonte, puntiCorretta, puntiErrata, puntiNonData, numRisposte, minRisposteCorrette, maxRisposteCorrette, tempoTotale);
    }

    public void creaSezioneU(String nomeMateria, int numQuesiti) throws NotAllowedException, StudentNotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono inserire template ufficiali.");
        ((Impiegato) utenteAutenticato).creaSezioneU(nomeMateria, numQuesiti);
    }

    public Materia getMateriaFlyweight(String nomeMateria){ //Risolve un nomeMateria in una Materia, applicando il pattern GoF Flyweight
        for (Materia m: mappaMaterie.values()) if(m.getNome().equalsIgnoreCase(nomeMateria)) return m;
        String codiceMateria=nomeMateria.substring(0,3).toUpperCase();
        int count=1; /*ALGORITMO di assegnamento dei codici delle materie:
        Prendo tutte le keys, recupero le prime tre lettere, conto quante keys hanno le prime tre lettere uguali, quindi assegno come parte numerica esattamente quante sono +1
        Esempio di scenario:
        Biologia -> BIO01 (è la prima materia inserita nel Sistema che inizia per "BIO")
        Biomedicina -> BIO02 (ho 2 materie che iniziano per "BIO", quindi 1+1=2)
        Biochimica -> BIO03 (ho 3 materie che iniziano per "BIO", quindi 2+1=3)*/
        for(String k: mappaMaterie.keySet()) if(k.substring(0,3).equals(codiceMateria)) count++;
        codiceMateria=codiceMateria+String.format("%02d",count); //Esempio: MAT01
        nomeMateria = nomeMateria.substring(0, 1).toUpperCase() + nomeMateria.substring(1); //Rendo il nomeMateria nella forma "prima lettera maiuscola". Es. Ciao (e non ciao o CIAO o CiAO ecc).
        Materia m = new Materia(nomeMateria, codiceMateria);
        mappaMaterie.put(m.getCodice(),m);
        return m;
    }

    public void confermaTemplateU() throws NotAllowedException, StudentNotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono inserire template ufficiali.");
        ((Impiegato)utenteAutenticato).confermaTemplateU();
    }
    /////////////////////UC9 COMPONI TEST PER SIMULAZIONE CARTACEA////////////////
    public List<Template> visualizzaTemplateTutor() throws NotAllowedException, StudentNotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono creare test cartacei.");
        ArrayList<TemplatePersonalizzato> listaPersonalizzati = ((Impiegato)utenteAutenticato).visualizzaTemplateTutor();
        ArrayList<TemplateUfficiale> listaUfficiali = new ArrayList<>(UniCTest.getInstance().getMappaTemplateUfficiali().values());
        ArrayList<Template> lista = new ArrayList<>();
        for(TemplatePersonalizzato t:listaPersonalizzati) lista.add(t);
        for(TemplateUfficiale t:listaUfficiali) lista.add(t);
        return lista;
    }

    public List<Sezione> creaTestCartaceo(String idTemplate) throws StudentNotAllowedException,NotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono creare test cartacei.");
        return ((Impiegato)utenteAutenticato).creaTestCartaceo(idTemplate);
    }

    public ArrayList<QuesitoDescrizione> visualizzaQuesiti(String idSezione) throws NotAllowedException, NotEnoughQuestionsException, StudentNotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono creare test cartacei.");
        return ((Impiegato)utenteAutenticato).visualizzaQuesiti(idSezione);
    }

    public void inserisciQuesiti(List<String> listaIdQuesiti) throws NotAllowedException, QuestionNotFoundException, TemplateSectionException, StudentNotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono creare test cartacei.");
        ((Impiegato)utenteAutenticato).inserisciQuesiti(listaIdQuesiti);
    }

    public void stampaTest(String nomeFile) throws NotAllowedException, StudentNotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono creare test cartacei.");
        ((Impiegato)utenteAutenticato).stampaTest(nomeFile);
    }
    ////////////////////////////UC10 CORREGGI SIMULAZIONI CARTACEO////////////////////////
    public Map<String,String> recuperaInfoTestCartaceo(String fileName) throws NotAllowedException, StudentNotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono correggere simulazioni cartacee.");
        return ((Impiegato)utenteAutenticato).recuperaInfoTestCartaceo(fileName);
    }

    public Test correggiTestCartaceo(String cfStudente, String cfTutor, String idTest) throws NotAllowedException, StudentNotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono correggere simulazioni cartacee.");
        return ((Impiegato)utenteAutenticato).correggiTestCartaceo(cfStudente, cfTutor, idTest);
    }

    public Test confermaCorrezione() throws NotAllowedException, StudentNotAllowedException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono correggere simulazioni cartacee.");
        return ((Impiegato)utenteAutenticato).confermaCorrezione();
    }
    ///////////////////////////////UC6 NUOVO TUTOR///////////////////////////////
    public void nuovoTutor(String cf, String nome, String cognome) throws NotAllowedException, StudentNotAllowedException, AlreadyRegisteredException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono registrare nuovi tutor");
        if(mappaUtenti.containsKey(cf.toUpperCase())) throw new AlreadyRegisteredException("Codice fiscale già presente nel sistema.");
        nome=nome.substring(0,1).toUpperCase()+nome.substring(1).toLowerCase();
        cognome=cognome.substring(0,1).toUpperCase()+cognome.substring(1).toLowerCase();
        Impiegato t=((Impiegato)utenteAutenticato).nuovoTutor(cf.toUpperCase(), nome, cognome);
        mappaUtenti.put(t.getCf(),t);
    }
    //////////////////////////////UC5 NUOVO STUDENTE/////////////////////
    public void nuovoStudente(String cf, String nome, String cognome) throws NotAllowedException, StudentNotAllowedException, AlreadyRegisteredException {
        if(utenteAutenticato instanceof Studente) throw new StudentNotAllowedException("Gli studenti non possono registrare nuovi studenti");
        if(mappaUtenti.containsKey(cf.toUpperCase())) throw new AlreadyRegisteredException("Codice fiscale già presente nel sistema.");
        nome=nome.substring(0,1).toUpperCase()+nome.substring(1).toLowerCase();
        cognome=cognome.substring(0,1).toUpperCase()+cognome.substring(1).toLowerCase();
        Studente s=((Impiegato)utenteAutenticato).nuovoStudente(cf.toUpperCase(), nome, cognome);
        mappaUtenti.put(s.getCf(),s);
    }
                        ////////////METODI PER CASO D'USO DI AVVIAMENTO//////////////

    private void loadMaterie(){
        Materia m1 = new Materia("Matematica", "MAT01");
        Materia m2 = new Materia("Italiano", "ITA02");
        mappaMaterie.put(m1.getCodice(),m1);
        mappaMaterie.put(m2.getCodice(),m2);
    }

    private void loadVisibilità(){
        Visibilità v1 = new Visibilità("Personale", "p1");
        Visibilità v2 = new Visibilità("Privato", "p2");
        Visibilità v3 = new Visibilità("Pubblico", "p3");
        mappaVisibilità.put(v1.getCodice(),v1);
        mappaVisibilità.put(v2.getCodice(),v2);
        mappaVisibilità.put(v3.getCodice(),v3);
    }

    private void loadUtenti(){
        Impiegato t1 = new Tutor("Mario", "Rossi", "RSSMRA80A01C351O");
        Impiegato t2 = new Tutor("Luca","Catania","CTNLCU80A01C351K");
        t2 = new TutorSimulazione(t2);
        Impiegato t3 = new Tutor("Andrea","Messina","MSSNDR80A01C351P");
        t3 = new Amministratore(t3);
        Impiegato t4 = new Tutor("Maria","Pappalardo","PPPMRA80A01C351X");
        t4 = new Amministratore(t4);
        t4 = new TutorSimulazione(t4);
        Studente s = new Studente("Luigi","Verdi","VRDLGI99R21C351J");
        mappaUtenti.put(t1.getCf(),t1);
        mappaUtenti.put(t2.getCf(),t2);
        mappaUtenti.put(t3.getCf(),t3);
        mappaUtenti.put(t4.getCf(),t4);
        mappaUtenti.put(s.getCf(),s);
        t1.addMateriaInsegnata(mappaMaterie.get("MAT01"));
        t1.addMateriaInsegnata(mappaMaterie.get("ITA02"));
        t2.addMateriaInsegnata(mappaMaterie.get("MAT01"));
        t2.addMateriaInsegnata(mappaMaterie.get("ITA02"));
        t3.addMateriaInsegnata(mappaMaterie.get("ITA02"));
        t4.addMateriaInsegnata(mappaMaterie.get("MAT01"));
    }


}
