package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NomeFilePdfPanel implements ActionListener {
    private JPanel mainPanel;
    private JTextField textField;
    private JButton confermaButton;

    public NomeFilePdfPanel(){
        confermaButton.addActionListener(this);
        confermaButton.setEnabled(false);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!textField.getText().trim().isEmpty());
            }
            public void removeUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!textField.getText().trim().isEmpty());
            }
            public void insertUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!textField.getText().trim().isEmpty());
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            UniCTest.getInstance().stampaTest(textField.getText());
            TestCartaceoFrame.getInstance().dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
