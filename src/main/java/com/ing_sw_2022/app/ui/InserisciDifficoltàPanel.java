package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserisciDifficoltàPanel implements ActionListener {
    private JPanel mainPanel;
    private JLabel label;
    private JScrollPane scroller;
    private JPanel panel;
    private JButton button;
    private JSpinner spinner;
    private SpinnerNumberModel spinnerNumberModel;
    public InserisciDifficoltàPanel(){
        spinnerNumberModel = (SpinnerNumberModel) spinner.getModel();
        spinnerNumberModel.setMaximum(5);
        spinnerNumberModel.setMinimum(1);
        spinner.setValue(3);
        button.addActionListener(this);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            UniCTest.getInstance().inserisciDifficoltà(Integer.parseInt(spinner.getValue().toString()));
            NuovoQuesitoFrame.getInstance().setContentPane(new ConfermaQuesitoPanel().getMainPanel());
            NuovoQuesitoFrame.getInstance().revalidate();
        } catch (StudentNotAllowedException ex) {
            ex.printStackTrace();
        }

    }
}
