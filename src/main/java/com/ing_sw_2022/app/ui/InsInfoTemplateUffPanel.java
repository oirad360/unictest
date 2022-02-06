package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.NotAllowedException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsInfoTemplateUffPanel implements ActionListener, ChangeListener {
    private JPanel mainPanel;
    private JLabel mainLabel;
    private JScrollPane scroller;
    private JPanel panel;
    private JSpinner puntiCorretta;
    private JSpinner puntiErrata;
    private JSpinner puntiNonData;
    private JSpinner numRisposte;
    private JSpinner minRisposteCorrette;
    private JSpinner maxRisposteCorrette;
    private JButton button;
    private JSpinner tempoTotale;
    private JTextField fonte;

    public InsInfoTemplateUffPanel(){
        button.addActionListener(this);
        button.setEnabled(false);
        puntiCorretta.setModel(new SpinnerNumberModel(1.0,0.1 ,null,0.1));
        puntiErrata.setModel(new SpinnerNumberModel(0.0,null ,0.0,0.1));
        puntiNonData.setModel(new SpinnerNumberModel(0.0,null ,0.0,0.1));
        numRisposte.setModel(new SpinnerNumberModel(4,2 ,null,1));
        tempoTotale.setModel(new SpinnerNumberModel(1,1 ,null,1));
        numRisposte.setName("numRisposte");
        minRisposteCorrette.setModel(new SpinnerNumberModel(1,1 ,null,1));
        minRisposteCorrette.setName("minRisposteCorrette");
        maxRisposteCorrette.setModel(new SpinnerNumberModel(4,1 ,null,1));
        maxRisposteCorrette.setName("maxRisposteCorrette");
        numRisposte.addChangeListener(this);
        minRisposteCorrette.addChangeListener(this);
        maxRisposteCorrette.addChangeListener(this);
        fonte.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                button.setEnabled(!fonte.getText().trim().isEmpty());
            }
            public void removeUpdate(DocumentEvent e) {
                button.setEnabled(!fonte.getText().trim().isEmpty());
            }
            public void insertUpdate(DocumentEvent e) {
                button.setEnabled(!fonte.getText().trim().isEmpty());
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            UniCTest.getInstance().inserisciInfoTemplateU(fonte.getText(), Float.parseFloat(puntiCorretta.getValue().toString()),Float.parseFloat(puntiErrata.getValue().toString()),Float.parseFloat(puntiNonData.getValue().toString()),(int)numRisposte.getValue(),(int)minRisposteCorrette.getValue(),(int)maxRisposteCorrette.getValue(),(int)tempoTotale.getValue());
            NuovoTemplateUffFrame.getInstance().setContentPane(new CreaSezioneUffPanel().getMainPanel());
            NuovoTemplateUffFrame.getInstance().revalidate();
        } catch (NotAllowedException ex) {
            ex.printStackTrace();
        } catch (StudentNotAllowedException ex) {
            ex.printStackTrace();
        }



    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner field= (JSpinner) e.getSource();
        switch(field.getName()){
            case "numRisposte":
                if(Integer.parseInt(numRisposte.getValue().toString())<Integer.parseInt(maxRisposteCorrette.getValue().toString())){
                    numRisposte.setValue(Integer.parseInt(maxRisposteCorrette.getValue().toString()));
                }
                break;
            case "minRisposteCorrette":
                if(Integer.parseInt(minRisposteCorrette.getValue().toString())>Integer.parseInt(maxRisposteCorrette.getValue().toString())){
                    minRisposteCorrette.setValue(Integer.parseInt(maxRisposteCorrette.getValue().toString()));
                }
                break;
            case "maxRisposteCorrette":
                if(Integer.parseInt(maxRisposteCorrette.getValue().toString())<Integer.parseInt(minRisposteCorrette.getValue().toString())){
                    maxRisposteCorrette.setValue(Integer.parseInt(minRisposteCorrette.getValue().toString()));
                }else if(Integer.parseInt(maxRisposteCorrette.getValue().toString())>Integer.parseInt(numRisposte.getValue().toString())){
                    maxRisposteCorrette.setValue(Integer.parseInt(numRisposte.getValue().toString()));
                }
                break;
        }
    }
}

