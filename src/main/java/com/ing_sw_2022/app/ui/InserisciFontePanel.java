package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
        button.setEnabled(false);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                button.setEnabled(!textField.getText().trim().isEmpty());
            }
            public void removeUpdate(DocumentEvent e) {
                button.setEnabled(!textField.getText().trim().isEmpty());
            }
            public void insertUpdate(DocumentEvent e) {
                button.setEnabled(!textField.getText().trim().isEmpty());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
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
