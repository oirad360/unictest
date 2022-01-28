package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.QuesitoReale;
import com.ing_sw_2022.app.Test;

import javax.swing.*;

public class TestCorrettoPanel {
    private JPanel mainPanel;
    private JLabel idTest;
    private JLabel nomeTemplate;
    private JLabel punteggio;
    private JTabbedPane tabbedPane;

    public TestCorrettoPanel(Test t){
        punteggio.setText(String.valueOf(t.getPunteggioComplessivo()));
        idTest.setText(t.getId().split("-")[1]);
        nomeTemplate.setText(t.getTemplatePersonalizzato().getNome());
        for(QuesitoReale qr : t.getMappaQuesiti().values()){
            int index = Integer.parseInt(qr.getId().split("-")[2])+1;
            tabbedPane.addTab(String.valueOf(index),new TabQuesitoCorrettoPanel(qr).getMainPanel());
        }
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}
