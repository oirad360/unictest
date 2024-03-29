package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        } catch (NotAllowedException ex) {
            ex.printStackTrace();
        } catch (StudentNotAllowedException ex) {
            ex.printStackTrace();
        }
        if(t!=null){
            UniCTest.getInstance().serialize();
            CorreggiTestCartaceoFrame.getInstance().setContentPane(new TestCorrettoPanel(t).getMainPanel());
            CorreggiTestCartaceoFrame.getInstance().revalidate();
        }

    }
}
