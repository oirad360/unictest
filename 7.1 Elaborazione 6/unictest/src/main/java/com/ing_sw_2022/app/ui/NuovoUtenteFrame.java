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

public class NuovoUtenteFrame extends JFrame implements ActionListener{
    private static NuovoUtenteFrame nuovoUtenteFrame;
    private JPanel mainPanel;
    private JButton confermaButton;
    private JTextField cf;
    private JTextField nome;
    private JTextField cognome;
    private JRadioButton studenteRadioButton;
    private JRadioButton tutorRadioButton;
    private Boolean val;

    private NuovoUtenteFrame(){
        setContentPane(mainPanel);
        setTitle("Nuovo utente");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+30,UniCTestFrame.getPos()+30);
        confermaButton.setEnabled(false);
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
        cf.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || nome.getText().trim().isEmpty() || cognome.getText().trim().isEmpty()));
            }
            public void removeUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || nome.getText().trim().isEmpty() || cognome.getText().trim().isEmpty()));            }
            public void insertUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || nome.getText().trim().isEmpty() || cognome.getText().trim().isEmpty()));            }
        });
        nome.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || nome.getText().trim().isEmpty() || cognome.getText().trim().isEmpty()));            }
            public void removeUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || nome.getText().trim().isEmpty() || cognome.getText().trim().isEmpty()));            }
            public void insertUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || nome.getText().trim().isEmpty() || cognome.getText().trim().isEmpty()));
            }
        });
        cognome.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || nome.getText().trim().isEmpty() || cognome.getText().trim().isEmpty()));            }
            public void removeUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || nome.getText().trim().isEmpty() || cognome.getText().trim().isEmpty()));            }
            public void insertUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || nome.getText().trim().isEmpty() || cognome.getText().trim().isEmpty()));            }
        });
    }
    public static NuovoUtenteFrame getInstance(){
        if(nuovoUtenteFrame==null) nuovoUtenteFrame=new NuovoUtenteFrame();
        return nuovoUtenteFrame;
    }
    public static void destroyInstance() {
        nuovoUtenteFrame = null;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(val) UniCTest.getInstance().nuovoStudente(cf.getText(),nome.getText(),cognome.getText());
            else UniCTest.getInstance().nuovoTutor(cf.getText(),nome.getText(),cognome.getText());
            UniCTest.getInstance().serialize();
            cf.setText("");
            nome.setText("");
            cognome.setText("");
        } catch (NotAllowedException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(),
                    ex.getMessage(),
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
        } catch (StudentNotAllowedException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(),
                    ex.getMessage(),
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
        } catch (AlreadyRegisteredException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(),
                    ex.getMessage(),
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
