package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfermaQuesitoPanel implements ActionListener {
    private JPanel mainPanel;
    private JLabel label;
    private JPanel panel;
    private JScrollPane scroller;
    private JButton button;
    private JTextField textField;

    public ConfermaQuesitoPanel(){
        textField.setText("p2");
        textField.getDocument().addDocumentListener(new DocumentListener() {
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
                button.setEnabled(!textField.getText().trim().isEmpty());
            }
        });
        button.addActionListener(this);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            UniCTest.getInstance().confermaQuesito(textField.getText());
            UniCTest.getInstance().serialize();
            NuovoQuesitoFrame.getInstance().dispose();
        } catch (StudentNotAllowedException ex) {
            ex.printStackTrace();
        }

    }
}
