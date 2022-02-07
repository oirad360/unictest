package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.QuesitoDescrizione;

import javax.swing.*;

public class QuesitoPanel {
    private JPanel mainPanel;
    private JTextArea risposte;
    private JTextArea testo;
    private JLabel id;
    private JLabel fonte;
    private JLabel materia;
    private JLabel difficoltà;
    private JLabel tutor;
    private JLabel visibilità;

    public QuesitoPanel(String risposte, String testo, String id, String fonte, String materia, String difficoltà, String tutor, String visibilità) {
        this.risposte.setText(risposte);
        this.testo.setText(testo);
        this.id.setText(id);
        this.fonte.setText(fonte);
        this.materia.setText(materia);
        this.difficoltà.setText(difficoltà);
        this.tutor.setText(tutor);
        this.visibilità.setText(visibilità);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}
