package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.QuesitoReale;
import com.ing_sw_2022.app.Risposta;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

public class TabQuesitoPanel implements ActionListener {
    private JPanel mainPanel;
    private JTextArea textArea;
    private JPanel risposteContainer;
    private JLabel materia;
    private QuesitoReale qr;
    public TabQuesitoPanel(QuesitoReale qr){
        this.qr=qr;
        String codiceMateria=qr.getQuesitoDescrizione().getId().split("-")[0];
        UniCTest unictest = UniCTest.getInstance();
        materia.setText(unictest.getMappaMaterie().get(codiceMateria).getNome());
        textArea.setText(qr.getQuesitoDescrizione().getTesto());
        TreeMap<String,Risposta> risposte=qr.getQuesitoDescrizione().getRisposte();
        risposteContainer.setLayout(new GridLayout(0,1));
        for(Risposta r : risposte.values()){
            JCheckBox checkBox = new JCheckBox(r.getTesto());
            checkBox.setName(r.getId());
            checkBox.addActionListener(this);
            for(Risposta r1:qr.getRisposteDate().values()){
                if(r1.getId().equals(r.getId())){
                    checkBox.setSelected(true);
                    break;
                }
            }
            risposteContainer.add(checkBox);
        }
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UniCTest unictest=UniCTest.getInstance();
        try {
            unictest.selezionaRisposta(qr.getId(),((JCheckBox)e.getSource()).getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
