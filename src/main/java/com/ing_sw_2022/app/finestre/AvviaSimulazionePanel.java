package com.ing_sw_2022.app.finestre;

import com.ing_sw_2022.app.QuesitoReale;
import com.ing_sw_2022.app.Test;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvviaSimulazionePanel implements ActionListener {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JButton btnConsegna;
    private int counter;

    public AvviaSimulazionePanel(Test test){
        counter=1;
        UniCTest unictest = UniCTest.getInstance();
        for(QuesitoReale qr : test.getMappaQuesiti().values()){
            tabbedPane.addTab(String.valueOf(counter),new TabQuesitoPanel(qr).getMainPanel());
            counter++;
        }
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
