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
        tesseract.setDatapath("C:/Users/oirad/unictest/tesseract");
        tesseract.setLanguage("ita");
        String text = null;
        try {

            text= tesseract.doOCR(new File("C:/Users/oirad/unictest/prova.png"));

        } catch (TesseractException e) {
            e.printStackTrace();
        }*/
        //System.out.println(text);
        /*Object[] o=text.lines().toArray();
        String[] line=new String[o.length];
        int i=0;
        for(Object o1:o){
            line[i]=(String) o1;
            System.out.println(line[i]);
            System.out.println("---------");
            if(i==3) break;
            i++;
        }
        String[] lineaCfTutor=line[1].split(" ");
        String cfTutor=lineaCfTutor[lineaCfTutor.length-1];
        String[] lineaIdTest=line[2].split(" ");
        String idTest=lineaIdTest[lineaIdTest.length-1];
        String[] lineaCfStudente=line[3].split(" ");
        String cfStudente=lineaCfStudente[lineaCfStudente.length-1];
        System.out.println(cfTutor+", "+idTest+", "+cfStudente);
*/
        /*Object[] o=text.lines().toArray();
        String[] line=new String[o.length-4];
        int i=0;
        for(Object o1:o){
            if(i>3){
                line[i-4]=(String) o1;
                System.out.println(line[i-4]);
                System.out.println("---------");
            }
            i++;
        }*/


        Impiegato prova = new Amministratore(new Tutor("Luca", "Bianchi", "LCABNC80A02C456P"));
        prova.whoAmI();
        Impiegato daDecorare = new Tutor("Luca", "Bianchi", "LCABNC80A02C456P");
        UniCTest.getInstance().getMappaUtenti().put("LCABNC80A02C456P", daDecorare);
        daDecorare.whoAmI();
        daDecorare = prova.rendiTutorSimulazione(daDecorare);
        daDecorare = prova.rendiAmministratore(daDecorare);
        System.out.println(daDecorare instanceof Amministratore);
        daDecorare.whoAmI();
        daDecorare = prova.rimuoviTutorSimulazione(daDecorare);
        daDecorare.whoAmI();

        //UniCTest.getInstance().getMappaUtenti().put(prova.getCf(),prova);
        System.out.println("UTENTI:");
        System.out.println(UniCTest.getInstance().getMappaUtenti());

    }
}
