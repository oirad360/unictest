package com.ing_sw_2022.app;

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
        UniCTest.getInstance().inserisciDifficoltà(Integer.parseInt(spinner.getValue().toString()));
        NuovoQuesitoFrame.getInstance().setContentPane(new ConfermaQuesitoPanel().getMainPanel());
        NuovoQuesitoFrame.getInstance().revalidate();
    }
}
