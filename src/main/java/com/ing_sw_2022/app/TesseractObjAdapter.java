package com.ing_sw_2022.app;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class TesseractObjAdapter implements Lettore{
    transient private Tesseract tesseract;
    private String text;
    private Studente studenteCorrente;
    private Template templateCorrente;
    private Test testCorrente;
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    public TesseractObjAdapter() {

    }

    @Override
    public Map<String, String> recuperaInfoTestCartaceo(String fileName) {
        tesseract = new Tesseract();
        tesseract.setDatapath("tesseract");
        tesseract.setLanguage("ita");
        try {
            text= tesseract.doOCR(new File(fileName+".png"));
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        Object[] o=text.lines().toArray();
        String[] line=new String[o.length];
        int i=0;
        for(Object o1:o){
            line[i]=(String) o1;
            if(i==3) break;
            i++;
        }
        String[] lineaCfTutor=line[1].split(" ");
        String cfTutor=lineaCfTutor[lineaCfTutor.length-1];
        String[] lineaIdTest=line[2].split(" ");
        String idTest=lineaIdTest[lineaIdTest.length-1];
        String[] lineaCfStudente=line[3].split(" ");
        String cfStudente=lineaCfStudente[lineaCfStudente.length-1];
        Map<String,String> mappaInfo=new HashMap<>();
        mappaInfo.put("cfTutor",cfTutor);
        mappaInfo.put("idTest",idTest);
        mappaInfo.put("cfStudente",cfStudente);
        return mappaInfo;
    }

    @Override
    public Test correggiTestCartaceo(String cfStudente, String cfTutor, String idTest) throws Exception {
        studenteCorrente=(Studente) UniCTest.getInstance().getMappaUtenti().get(cfStudente);
        Impiegato imp=(Impiegato) UniCTest.getInstance().getMappaUtenti().get(cfTutor);
        Template te=imp.getMappaTemplateTestScritti().get(idTest.split("-")[0]);//lancia eccezione nel caso in cui l'impiegato che ha scritto il test non risulta essere tutor di simulazione
        //La prima parte dell'id del Test è l'id del Template su cui esso è basato
        testCorrente=(Test)te.getMappaTest().get(idTest).clone();
        //il template corrente deve essere un clone del template con il quale il tutor ha scritto il test
        //vedo se il clone è già presente nella mappaTemplateTestSvolti dello studente, se lo trovo lo metto corrente
        //se non c'è creo il clone e lo metto corrente.
        boolean found=false;
        for(Template tec: studenteCorrente.getMappaTemplateTestSvolti().values()){
            if(tec.getId().equals(te.getId())){
                found=true;
                templateCorrente=tec;
                break;
            }
        }
        if(!found){
            templateCorrente=(Template)te.clone();
        }
        //Start correzione
        Object[] o=text.lines().toArray();
        String[] line=new String[o.length-4];
        int i=0;
        System.out.println(text);
        for(Object o1:o){
            if(i>3){
                line[i-4]=(String) o1;
                System.out.println(line[i-4]);
                System.out.println("---------");
            }
            i++;
        }
        Map<String,QuesitoReale> quesiti=testCorrente.getMappaQuesiti();
        for(String s: line){
            String parte1=s.split(" Risposta ")[0];
            String parte2=s.split(" Risposta ")[1];
            String numDomandaString=parte1.split(" ")[1];
            String[] numRisposte=parte2.split(" ");
            int numDomanda=0;
            if(isNumeric(numDomandaString)) {
                numDomanda=Integer.parseInt(numDomandaString);
                QuesitoReale qr=quesiti.get(idTest+"-"+(numDomanda-1));
                Map<String,Risposta> risposteDate = qr.getRisposteDate();
                for(String r: numRisposte){
                    if(isNumeric(r)){
                        Risposta risp=qr.getQuesitoDescrizione().getRisposte().get(qr.getQuesitoDescrizione().getId()+"-"+(Integer.parseInt(r)-1));
                        risposteDate.put(risp.getId(),risp);
                    }
                }
            }


        }
        return testCorrente;
    }

    public void selezionaRisposta(String idQuesitoReale, String idRisposta){
        Map<String,QuesitoReale> quesiti=testCorrente.getMappaQuesiti();
        QuesitoReale qr=quesiti.get(idQuesitoReale);
        Map<String,Risposta> risposteDate = qr.getRisposteDate();
        Risposta risp=qr.getQuesitoDescrizione().getRisposte().get(idRisposta);
        if(risposteDate.containsValue(risp)){ //Allora la risposta l'avevo già messa, quindi devo toglierla
            risposteDate.remove(risp.getId(),risp);
        } else { //Allora la risposta NON l'avevo messa, quindi devo metterla
            risposteDate.put(risp.getId(), risp);
        }
    }

    @Override
    public Test confermaCorrezione() {
        boolean found=false;
        for(Template tec: studenteCorrente.getMappaTemplateTestSvolti().values()){
            if(tec.getId().equals(templateCorrente.getId())){
                found=true;
                break;
            }
        }
        if(!found){
            studenteCorrente.getMappaTemplateTestSvolti().put(templateCorrente.getId(),templateCorrente);
        }
        templateCorrente.getMappaTest().put(testCorrente.getId(),testCorrente);
        testCorrente.setTemplate(templateCorrente);
        Test t=testCorrente;
        t.terminaSimulazione(); //In realtà qui sfrutto il metodo terminaSimulazione per calcolare il punteggio complessivo del test.
        templateCorrente=null;
        testCorrente=null;
        studenteCorrente=null;
        return t;

    }
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}
