package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserisciTestoPanel implements ActionListener {
    private JLabel label;
    private JTextArea textArea;
    private JButton button;
    private JPanel mainPanel;

    public InserisciTestoPanel(){
        button.addActionListener(this);
        button.setEnabled(false);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                button.setEnabled(!textArea.getText().trim().isEmpty());
            }
            public void removeUpdate(DocumentEvent e) {
                button.setEnabled(!textArea.getText().trim().isEmpty());
            }
            public void insertUpdate(DocumentEvent e) {
                button.setEnabled(!textArea.getText().trim().isEmpty());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UniCTest unictest = UniCTest.getInstance();
        NuovoQuesitoFrame nuovoQuesitoFrame= null;
        try {
            unictest.inserisciTesto(textArea.getText());
            nuovoQuesitoFrame = NuovoQuesitoFrame.getInstance();
        } catch (StudentNotAllowedException ex) {
            ex.printStackTrace();
        }
        nuovoQuesitoFrame.setContentPane(new InserisciRispostePanel().getMainPanel());
        nuovoQuesitoFrame.revalidate();
    }
}
