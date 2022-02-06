package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.NotAllowedException;
import com.ing_sw_2022.app.UniCTest;
import com.ing_sw_2022.app.eccezioni.AlreadyRegisteredException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AggiungiResponsabilitàFrame extends JFrame{
    private static AggiungiResponsabilitàFrame aggiungiResponsabilitàFrame;
    private JPanel mainPanel;
    private JTextField cfTextField;
    private JButton rendiAmministratoreButton;
    private JButton rendiTutorSimulazioneButton;
    private JButton rimuoviAmministratoreButton;
    private JButton rimuoviTutorSimulazioneButton;

    private AggiungiResponsabilitàFrame(){
        setContentPane(mainPanel);
        setTitle("Aggiungi responsabilità");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+30,UniCTestFrame.getPos()+30);

        rendiAmministratoreButton.setEnabled(false);
        rendiTutorSimulazioneButton.setEnabled(false);
        rimuoviAmministratoreButton.setEnabled(false);
        rimuoviTutorSimulazioneButton.setEnabled(false);

        rendiAmministratoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        rendiTutorSimulazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        rimuoviAmministratoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        rimuoviTutorSimulazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /*confermaButton.setEnabled(false);
        val=true;
        confermaButton.addActionListener(this);

        studenteRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tutorRadioButton.setSelected(false);
                studenteRadioButton.setSelected(true);
                val=true;
            }
        });
        tutorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studenteRadioButton.setSelected(false);
                tutorRadioButton.setSelected(true);
                val=false;
            }
        });
        */
        cfTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                rendiAmministratoreButton.setEnabled(!(cfTextField.getText().trim().isEmpty()));
                rendiTutorSimulazioneButton.setEnabled(!(cfTextField.getText().trim().isEmpty()));
                rimuoviAmministratoreButton.setEnabled(!(cfTextField.getText().trim().isEmpty()));
                rimuoviTutorSimulazioneButton.setEnabled(!(cfTextField.getText().trim().isEmpty()));
            }
            public void removeUpdate(DocumentEvent e) {
                rendiAmministratoreButton.setEnabled(!(cfTextField.getText().trim().isEmpty()));
                rendiTutorSimulazioneButton.setEnabled(!(cfTextField.getText().trim().isEmpty()));
                rimuoviAmministratoreButton.setEnabled(!(cfTextField.getText().trim().isEmpty()));
                rimuoviTutorSimulazioneButton.setEnabled(!(cfTextField.getText().trim().isEmpty()));
            }
            public void insertUpdate(DocumentEvent e) {
                rendiAmministratoreButton.setEnabled(!(cfTextField.getText().trim().isEmpty()));
                rendiTutorSimulazioneButton.setEnabled(!(cfTextField.getText().trim().isEmpty()));
                rimuoviAmministratoreButton.setEnabled(!(cfTextField.getText().trim().isEmpty()));
                rimuoviTutorSimulazioneButton.setEnabled(!(cfTextField.getText().trim().isEmpty()));
            }
        });
    }
    public static AggiungiResponsabilitàFrame getInstance(){
        if(aggiungiResponsabilitàFrame==null) aggiungiResponsabilitàFrame=new AggiungiResponsabilitàFrame();
        return aggiungiResponsabilitàFrame;
    }
    public static void destroyInstance() {
        aggiungiResponsabilitàFrame = null;
    }

}
