package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.NotAllowedException;
import com.ing_sw_2022.app.UniCTest;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;
import com.ing_sw_2022.app.eccezioni.UserNotFoundException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AggiungiMateriaInsegnataFrame extends JFrame implements ActionListener {
    private static AggiungiMateriaInsegnataFrame aggiungiMateriaInsegnataFrame;
    private JPanel mainPanel;
    private JButton confermaButton;
    private JTextField cf;
    private JTextField materia;

    private AggiungiMateriaInsegnataFrame(){
        setContentPane(mainPanel);
        setTitle("Aggiungi materia insegnata");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+25,UniCTestFrame.getPos()+35);
        confermaButton.setEnabled(false);
        confermaButton.addActionListener(this);
        cf.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || materia.getText().trim().isEmpty()));
            }
            public void removeUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || materia.getText().trim().isEmpty()));
            }
            public void insertUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || materia.getText().trim().isEmpty()));
            }
        });
        materia.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || materia.getText().trim().isEmpty()));
            }
            public void removeUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || materia.getText().trim().isEmpty()));
            }
            public void insertUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!(cf.getText().trim().isEmpty() || materia.getText().trim().isEmpty()));
            }
        });
    }

    public static AggiungiMateriaInsegnataFrame getInstance(){
        if(aggiungiMateriaInsegnataFrame==null) aggiungiMateriaInsegnataFrame=new AggiungiMateriaInsegnataFrame();
        return aggiungiMateriaInsegnataFrame;
    }

    public static void destroyInstance(){
        aggiungiMateriaInsegnataFrame=null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            UniCTest.getInstance().aggiungiMateriaInsegnata(cf.getText(),materia.getText());
            UniCTest.getInstance().serialize();
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
        } catch (UserNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(),
                    ex.getMessage(),
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            cf.setText("");
        }
        materia.setText("");
    }
}
