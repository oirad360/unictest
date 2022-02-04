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
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:/Users/oirad/unictest/tesseract/tessdata");
        tesseract.setLanguage("ita");
        String text = null;
        try {

            text= tesseract.doOCR(new File("C:/Users/oirad/unictest/prova.png"));

        } catch (TesseractException e) {
            e.printStackTrace();
        }
        System.out.println(text);
        /*Object[] o=text.lines().toArray();
        String[] s=new String[o.length];
        int i=0;
        for(Object o1:o){
            s[i]=(String) o1;
            System.out.println(s[i]);
            System.out.println("---------");
        }*/

    }
}
