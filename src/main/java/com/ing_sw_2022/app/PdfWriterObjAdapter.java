package com.ing_sw_2022.app;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;


import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
//import sandbox.WrapToTest;

public class PdfWriterObjAdapter implements Stampante{
    private static PdfWriterObjAdapter pdfWriterObjAdapter;
    private static Font fontTitolo = new Font(Font.FontFamily.COURIER, 18,
            Font.BOLD, BaseColor.BLUE);
    private static Font fontSottotitolo = new Font(Font.FontFamily.COURIER, 16,
            Font.NORMAL, BaseColor.GRAY);
    private static Font fontClassico = new Font(Font.FontFamily.COURIER, 13,
            Font.NORMAL);
    /*private static Font fontClassico = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);*/

    private PdfWriterObjAdapter(){

    }

    public static PdfWriterObjAdapter getInstance() {
        if (pdfWriterObjAdapter == null) pdfWriterObjAdapter = new PdfWriterObjAdapter();
        return pdfWriterObjAdapter;
    }

    public void aggiungiImmagineBackground(PdfWriter writer, String pathImmagine){
        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = null;
        try {
           image = Image.getInstance(pathImmagine);
        } catch (BadElementException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
        //image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());//scaleAbsolute(PageSize.A4,PageSize.A4.rotate());
        /*float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
        float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
        image.scaleToFit(documentWidth, documentHeight);*/
        image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
        image.setAbsolutePosition(0, 0);
        canvas.saveState();
        PdfGState state = new PdfGState();
        state.setFillOpacity(0.2f);
        canvas.setGState(state);
        try {
           canvas.addImage(image);
        } catch (DocumentException e) {
           e.printStackTrace();
        }
        canvas.restoreState();
        //document.close();
    }

    @Override
    public void stampaTest(String idTest, List<QuesitoReale> listaQuesiti, String nomeFile) {



        Document document = new Document();
        Impiegato imp = (Impiegato) UniCTest.getInstance().getUtenteAutenticato();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nomeFile+".pdf"));
            document.open();
            Paragraph titoloTest = new Paragraph("TEST",fontTitolo);
            titoloTest.setAlignment(Element.ALIGN_CENTER);
            document.add(titoloTest);
            aggiungiImmagineBackground(writer, "unictest.png");
            document.add(new Paragraph("______________________________________________________________________________"));
            for(QuesitoReale qr: listaQuesiti){
                document.add(new Paragraph((Integer.parseInt(qr.getId().split("-")[2])+1)+") "+qr.getQuesitoDescrizione().getTesto(),fontClassico));
                for(Risposta r: qr.getQuesitoDescrizione().getRisposte().values()){
                    document.add(new Paragraph("  "+(Integer.parseInt(r.getId().split("-")[2])+1)+") "+r.getTesto(),fontClassico));
                }
                document.add(new Paragraph("______________________________________________________________________________"));
            }
            document.newPage();
            Paragraph titoloFoglioRisposte = new Paragraph("FOGLIO RISPOSTE",fontTitolo);
            titoloFoglioRisposte.setAlignment(Element.ALIGN_CENTER);
            document.add(titoloFoglioRisposte);
            document.add(new Paragraph("TUTOR   "+imp.getCf(),fontSottotitolo));
            document.add(new Paragraph("TEST   "+idTest,fontSottotitolo));
            document.add(new Paragraph("STUDENTE  ",fontSottotitolo));
            for(QuesitoReale qr: listaQuesiti){
                String s="Domanda  "+(Integer.parseInt(qr.getId().split("-")[2])+1)+"  Risposta  ";
                for(Risposta r: qr.getQuesitoDescrizione().getRisposte().values()){
                    s=s+(Integer.parseInt(r.getId().split("-")[2])+1)+"  ";
                }
                document.add(new Paragraph(s,fontClassico));
            }
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
