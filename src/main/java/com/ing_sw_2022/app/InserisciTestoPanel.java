package com.ing_sw_2022.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserisciTestoPanel extends JPanel implements ActionListener {
    private JLabel label;
    private JTextArea textArea;
    private JButton button;
    private JPanel mainPanel;

    public InserisciTestoPanel(){
        button.addActionListener(this);
    }

    public JLabel getLabel() {
        return label;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JButton getButton() {
        return button;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UniCTest unictest = UniCTest.getInstance();
        unictest.inserisciTesto(textArea.getText());
        System.out.println(unictest.getCorrente().getCorrente());
    }
}
