package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.QuesitoReale;
import com.ing_sw_2022.app.Risposta;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;

public class TabQuesitoCorrettoPanel {
    private JPanel mainPanel;
    private JTextArea textArea;
    private JLabel materia;
    private JLabel correttezzaLabel;
    private JPanel risposteContainer;
    public TabQuesitoCorrettoPanel(QuesitoReale qr){
        String codiceMateria=qr.getQuesitoDescrizione().getId().split("-")[0];
        UniCTest unictest = UniCTest.getInstance();
        materia.setText(unictest.getMappaMaterie().get(codiceMateria).getNome());
        textArea.setText(qr.getQuesitoDescrizione().getTesto());
        TreeMap<String, Risposta> risposte=qr.getQuesitoDescrizione().getRisposte();
        TreeMap<String, Risposta> risposteDate=qr.getRisposteDate();
        risposteContainer.setLayout(new GridLayout(0,1));
        String testo;
        for(Risposta r : risposte.values()){
            if(r.isValore()) testo="[CORRETTA] "+r.getTesto();
            else testo="[ERRATA] "+r.getTesto();
            JCheckBox checkBox = new JCheckBox(testo);
            if(risposteDate.get(r.getId())!=null) checkBox.setSelected(true);
            checkBox.setEnabled(false);
            risposteContainer.add(checkBox);
            int correttezza = qr.verificaCorrettezza();
            switch(correttezza){
                case 0 : correttezzaLabel.setText("Nessuna risposta data"); break;
                case 1 : correttezzaLabel.setText("Risposta corretta"); break;
                case -1: correttezzaLabel.setText("Risposta errata"); break;
                default: System.out.println("ERRORE: Setting errato dei punti del quesito"); break;
            }
        }
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}
