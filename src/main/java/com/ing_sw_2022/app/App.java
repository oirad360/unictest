package com.ing_sw_2022.app;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.*;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
            text= tesseract.doOCR(new File("C:/Users/oirad/unictest/tesseract/Immagine.png"));
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        System.out.println(text);*/
        Document document=new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("provaPdf.pdf"));
            document.open();

            Paragraph paragraph = new Paragraph("AAAAAa aaaaaaaa aaaaaa aaa a aaa");
            document.add(paragraph);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
