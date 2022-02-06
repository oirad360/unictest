package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.NotAllowedException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;
import com.ing_sw_2022.app.Test;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfermaInfoTestPanel implements ActionListener {
    private JPanel mainPanel;
    private JTextField cfTutor;
    private JTextField cfStudente;
    private JTextField idTest;
    private JButton confermaDatiButton;

    public ConfermaInfoTestPanel(String cfStudente1, String cfTutor1, String idTest1){
        cfStudente.setText(cfStudente1);
        cfTutor.setText(cfTutor1);
        idTest.setText(idTest1);
        cfStudente.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                confermaDatiButton.setEnabled(!cfStudente.getText().trim().isEmpty());
            }
            public void removeUpdate(DocumentEvent e) {
                confermaDatiButton.setEnabled(!cfStudente.getText().trim().isEmpty());
            }
            public void insertUpdate(DocumentEvent e) {
                confermaDatiButton.setEnabled(!cfStudente.getText().trim().isEmpty());
            }
        });
        cfTutor.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                confermaDatiButton.setEnabled(!cfTutor.getText().trim().isEmpty());
            }
            public void removeUpdate(DocumentEvent e) {
                confermaDatiButton.setEnabled(!cfTutor.getText().trim().isEmpty());
            }
            public void insertUpdate(DocumentEvent e) {
                confermaDatiButton.setEnabled(!cfTutor.getText().trim().isEmpty());
            }
        });
        idTest.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                confermaDatiButton.setEnabled(!idTest.getText().trim().isEmpty());
            }
            public void removeUpdate(DocumentEvent e) {
                confermaDatiButton.setEnabled(!idTest.getText().trim().isEmpty());
            }
            public void insertUpdate(DocumentEvent e) {
                confermaDatiButton.setEnabled(!idTest.getText().trim().isEmpty());
            }
        });
        confermaDatiButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Test t=null;
        try {
            t= UniCTest.getInstance().correggiTestCartaceo(cfStudente.getText(),cfTutor.getText(),idTest.getText());
        } catch (NotAllowedException ex) {
            ex.printStackTrace();
        } catch (StudentNotAllowedException ex) {
            ex.printStackTrace();
        }
        if(t!=null){
            CorreggiTestCartaceoFrame.getInstance().setContentPane(new CorreggiRispostePanel(t).getMainPanel());
            CorreggiTestCartaceoFrame.getInstance().revalidate();
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
