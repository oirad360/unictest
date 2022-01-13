package com.ing_sw_2022.app;

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
        textField.setText("p1");
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
        UniCTest.getInstance().confermaQuesito(textField.getText());
        NuovoQuesitoFrame.getInstance().dispose();
        System.out.println(UniCTest.getInstance().getMappaMaterie().get("MAT01").getMappaQuesiti());
        System.out.println(UniCTest.getInstance().getMappaMaterie().get("ITA02").getMappaQuesiti());
        UniCTest.getInstance().serialize();
    }
}
