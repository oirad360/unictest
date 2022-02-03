package com.ing_sw_2022.app;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:/Users/oirad/unictest/tesseract/tessdata");
        tesseract.setLanguage("ita");
        String text = null;
        try {

            text= tesseract.doOCR(new File("C:/Users/oirad/unictest/tesseract/immagine6.png"));

        } catch (TesseractException e) {
            e.printStackTrace();
        }
        System.out.println(text);
        Object[] o=text.lines().toArray();
        String[] s=new String[o.length];
        int i=0;
        for(Object o1:o){
            s[i]=(String) o1;
            System.out.println(s[i]);
            System.out.println("---------");
        }*/
        UniCTest unictest = UniCTest.getInstance();
        unictest.setUtenteAutenticato("CTNLCU80A01C351K");
        unictest.nuovoQuesito("MAT01");
        unictest.inserisciFonte("MIUR");
        unictest.inserisciTesto("mat1 dfsdfdffgf sgsg d fggsdf sdsh gfdh dg dsg dfgfgdfdfd ddfdf gfgf gsdff  fsgdthr tr trseg sdg dfgdssdsd  fggfg fgf fgsfsssssretr g dffgfg g gfuhgffguui dfgfoisdu sdfs sdfgfjgifsdfiisijfsdjijjgshsifshuiuig  sfuhsguhfsghfsduidfsifg  ghfs  ppg uigfuifufh g fuhg fuhfg u ifg fg ui sguihg ig fhugfhi gfhigf hifgigfip ghf  sh");
        unictest.inserisciRisposta("vero",true);
        unictest.inserisciRisposta("falso",false);
        unictest.inserisciRisposta("falso",false);
        unictest.inserisciRisposta("falso",false);
        unictest.confermaQuesito("p2");
        unictest.nuovoQuesito("MAT01");
        unictest.inserisciFonte("MIUR");
        unictest.inserisciTesto("mat2");
        unictest.inserisciRisposta("falso",false);
        unictest.inserisciRisposta("falso",false);
        unictest.inserisciRisposta("vero",true);
        unictest.inserisciRisposta("falso",false);
        unictest.confermaQuesito("p2");
        unictest.nuovoQuesito("ITA02");
        unictest.inserisciFonte("MIUR");
        unictest.inserisciTesto("ita1");
        unictest.inserisciRisposta("falso",false);
        unictest.inserisciRisposta("falso",false);
        unictest.inserisciRisposta("vero",true);
        unictest.inserisciRisposta("vero",true);
        unictest.confermaQuesito("p2");
        List<QuesitoReale> listaQR=new ArrayList<>();
        int i=1;
        for(Materia m: unictest.getMappaMaterie().values()) {
            for(QuesitoDescrizione qd : m.getMappaQuesiti().values()){
                QuesitoReale qr = new QuesitoReale(String.valueOf(i),qd);
                listaQR.add(qr);
                i++;
            }
        }
        Document document=new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("provaPdf.pdf"));
            document.open();
            document.add(new Paragraph("______________________________________________________________________________"));
            for(QuesitoReale qr: listaQR){
                document.add(new Paragraph(qr.getId()+") "+qr.getQuesitoDescrizione().getTesto()));
                for(Risposta r: qr.getQuesitoDescrizione().getRisposte().values()){
                    document.add(new Paragraph("  "+(Integer.parseInt(r.getId().split("-")[2])+1)+") "+r.getTesto()));
                }
                document.add(new Paragraph("______________________________________________________________________________"));
            }
            document.newPage();
            document.add(new Paragraph("TUTOR   "+"CTNLCU80A01C351K"));
            document.add(new Paragraph("TEST   "));
            document.add(new Paragraph("STUDENTE  "));
            for(QuesitoReale qr: listaQR){
                String s="Domanda  "+qr.getId()+"  Risposta  ";
                for(Risposta r: qr.getQuesitoDescrizione().getRisposte().values()){
                    s=s+(Integer.parseInt(r.getId().split("-")[2])+1)+"  ";
                }
                document.add(new Paragraph(s));
            }
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
