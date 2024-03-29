package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.NotAllowedException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;
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
                boolean exception=false;
                try {
                    UniCTest.getInstance().creaSezioneU(materia.getText(),(int)numQuesiti.getValue());
                } catch (NotAllowedException ex) {
                    ex.printStackTrace();
                    exception=true;
                } catch (StudentNotAllowedException ex) {
                    ex.printStackTrace();
                    exception=true;
                }
                if(!exception){
                    counter++;
                    buttonConferma.setEnabled(true);
                    numSezioni.setText(counter.toString());
                    materia.setText("");
                }



            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            UniCTest.getInstance().confermaTemplateU();
            UniCTest.getInstance().serialize();
            NuovoTemplateUffFrame.getInstance().dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}