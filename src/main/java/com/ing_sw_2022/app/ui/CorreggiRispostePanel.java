package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;
import com.ing_sw_2022.app.ui.AvviaSimulazioneFrame;
import com.ing_sw_2022.app.ui.TabQuesitoPanel;
import com.ing_sw_2022.app.ui.TestCorrettoPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CorreggiRispostePanel implements ActionListener {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JButton btnConsegna;

    public CorreggiRispostePanel(Test test){
        for(QuesitoReale qr : test.getMappaQuesiti().values()){
            int index = Integer.parseInt(qr.getId().split("-")[2])+1;
            tabbedPane.addTab(String.valueOf(index),new TabQuesitoPanel(qr).getMainPanel());
        }
        btnConsegna.addActionListener(this);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Test t= null;
        try {
            t = UniCTest.getInstance().confermaCorrezione();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(t!=null){
            UniCTest.getInstance().serialize();
            CorreggiTestCartaceoFrame.getInstance().setContentPane(new TestCorrettoPanel(t).getMainPanel());
            CorreggiTestCartaceoFrame.getInstance().revalidate();
        }

    }
}
