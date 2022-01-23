package com.ing_sw_2022.app.finestre;

import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserisciRispostePanel {
    private JLabel label;
    private JPanel panel;
    private JPanel mainPanel;
    private JScrollPane scroller;
    private JRadioButton rbVero;
    private JRadioButton rbFalso;
    private JPanel radioPanel;
    private JTextField textField;
    private JPanel tfPanel;
    private JButton btnInserisci;
    private JButton btnConfermaRisposte;
    private JPanel btnPanel;
    private JLabel labelRisposteInserite;
    private JLabel labelNumRisposte;
    private Integer counter;
    private Boolean val;
    private Boolean checkTrue;
    public InserisciRispostePanel() {
        counter = 0;
        val = true;
        checkTrue = false;
        labelNumRisposte.setText(counter.toString());
        btnInserisci.setEnabled(false);
        btnConfermaRisposte.setEnabled(false);
        rbVero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rbFalso.setSelected(false);
                rbVero.setSelected(true);
                val=true;
            }
        });
        rbFalso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rbVero.setSelected(false);
                rbFalso.setSelected(true);
                val=false;
            }
        });
        btnInserisci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(val) checkTrue=true;
                UniCTest.getInstance().inserisciRisposta(textField.getText(),val);
                counter++;
                labelNumRisposte.setText(counter.toString());
                if(counter >= 2 && checkTrue){
                    btnConfermaRisposte.setEnabled(true);
                }
                textField.setText("");

            }
        });
        btnConfermaRisposte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NuovoQuesitoFrame nuovoQuesitoFrame= NuovoQuesitoFrame.getInstance();
                nuovoQuesitoFrame.setContentPane(new InserisciDifficoltàPanel().getMainPanel());
                nuovoQuesitoFrame.revalidate();
            }
        });
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                btnInserisci.setEnabled(!textField.getText().trim().isEmpty());
            }
            public void removeUpdate(DocumentEvent e) {
                btnInserisci.setEnabled(!textField.getText().trim().isEmpty());
            }
            public void insertUpdate(DocumentEvent e) {
                btnInserisci.setEnabled(!textField.getText().trim().isEmpty());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
