package com.ing_sw_2022.app.finestre;

import com.ing_sw_2022.app.QuesitoReale;
import com.ing_sw_2022.app.Risposta;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class TabQuesitoPanel {
    private JPanel mainPanel;
    private JTextArea textArea;
    private JPanel risposteContainer;
    private JLabel materia;

    public TabQuesitoPanel(QuesitoReale qr){
        String codiceMateria=qr.getQuesitoDescrizione().getId().split("-")[0];
        UniCTest unictest = UniCTest.getInstance();
        materia.setText(unictest.getMappaMaterie().get(codiceMateria).getNome());
        textArea.setText(qr.getQuesitoDescrizione().getTesto());
        TreeMap<String,Risposta> risposte=qr.getQuesitoDescrizione().getRisposte();
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}
