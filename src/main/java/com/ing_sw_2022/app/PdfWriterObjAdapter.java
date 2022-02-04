package com.ing_sw_2022.app;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class PdfWriterObjAdapter implements Stampante{
    private static PdfWriterObjAdapter pdfWriterObjAdapter;
    private Document document;

    private PdfWriterObjAdapter(){

    }

    public static PdfWriterObjAdapter getInstance() {
        if (pdfWriterObjAdapter == null) pdfWriterObjAdapter = new PdfWriterObjAdapter();
        return pdfWriterObjAdapter;
    }
    @Override
    public void stampaTest(String idTest, List<QuesitoReale> listaQuesiti, String nomeFile) {
        document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(nomeFile+".pdf"));
            document.open();
            document.add(new Paragraph("______________________________________________________________________________"));
            for(QuesitoReale qr: listaQuesiti){
                document.add(new Paragraph((Integer.parseInt(qr.getId().split("-")[2])+1)+") "+qr.getQuesitoDescrizione().getTesto()));
                for(Risposta r: qr.getQuesitoDescrizione().getRisposte().values()){
                    document.add(new Paragraph("  "+(Integer.parseInt(r.getId().split("-")[2])+1)+") "+r.getTesto()));
                }
                document.add(new Paragraph("______________________________________________________________________________"));
            }
            document.newPage();
            Impiegato imp= (Impiegato) UniCTest.getInstance().getUtenteAutenticato();
            document.add(new Paragraph("TUTOR   "+imp.getCf()));
            document.add(new Paragraph("TEST   "+idTest));
            document.add(new Paragraph("STUDENTE  "));
            for(QuesitoReale qr: listaQuesiti){
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
