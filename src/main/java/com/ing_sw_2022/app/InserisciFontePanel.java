package com.ing_sw_2022.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserisciFontePanel implements ActionListener {
    private JPanel mainPanel;
    private JLabel label;
    private JPanel panel;
    private JTextField textField;
    private JButton button;

    public InserisciFontePanel(){
        button.addActionListener(this);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JLabel getLabel() {
        return label;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JButton getButton() {
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UniCTest unictest = UniCTest.getInstance();
        unictest.inserisciFonte(textField.getText());
        NuovoQuesitoFrame nuovoQuesitoFrame= NuovoQuesitoFrame.getInstance();
        nuovoQuesitoFrame.setContentPane(new InserisciTestoPanel().getMainPanel());
        nuovoQuesitoFrame.revalidate();
    }
}
