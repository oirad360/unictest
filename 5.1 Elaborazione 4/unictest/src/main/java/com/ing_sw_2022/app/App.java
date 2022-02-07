package com.ing_sw_2022.app;


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


import java.io.File;
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
        }
        /*Document document=new Document();
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
        }*/

    }
}
