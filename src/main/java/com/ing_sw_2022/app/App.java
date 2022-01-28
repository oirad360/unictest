package com.ing_sw_2022.app;


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

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
            text= tesseract.doOCR(new File("C:/Users/oirad/unictest/tesseract/Immagine.png"));
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        System.out.println(text);
    }
}
