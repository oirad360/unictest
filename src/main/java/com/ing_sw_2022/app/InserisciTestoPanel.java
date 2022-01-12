package com.ing_sw_2022.app;

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
                action();
            }
            public void removeUpdate(DocumentEvent e) {
                action();
            }
            public void insertUpdate(DocumentEvent e) {
                action();
            }

            public void action() {
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
        unictest.inserisciTesto(textArea.getText());
        NuovoQuesitoFrame nuovoQuesitoFrame= NuovoQuesitoFrame.getInstance();
        nuovoQuesitoFrame.setContentPane(new InserisciRispostePanel().getMainPanel());
        nuovoQuesitoFrame.revalidate();
    }
}
