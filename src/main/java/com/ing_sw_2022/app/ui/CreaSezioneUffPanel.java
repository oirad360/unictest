package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.Materia;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaSezioneUffPanel implements ActionListener {
    private JPanel mainPanel;
    private JLabel mainLabel;
    private JScrollPane scroller;
    private JPanel panel;
    private JSpinner numQuesiti;
    private JButton button;
    private JPanel panelBox;
    private JLabel numSezioni;
    private JButton buttonConferma;
    private JTextField materia;
    private Integer counter;

    public CreaSezioneUffPanel(){
        counter=0;
        numSezioni.setText(counter.toString());
        buttonConferma.setEnabled(false);
        numQuesiti.setModel(new SpinnerNumberModel(1,1 ,null,1));
        buttonConferma.addActionListener(this);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                buttonConferma.setEnabled(true);
                numSezioni.setText(counter.toString());
                UniCTest.getInstance().creaSezioneU(materia.getText(),(int)numQuesiti.getValue());
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UniCTest.getInstance().confermaTemplateP();
        UniCTest.getInstance().serialize();
        NuovoTemplatePersFrame.getInstance().dispose();
    }

}