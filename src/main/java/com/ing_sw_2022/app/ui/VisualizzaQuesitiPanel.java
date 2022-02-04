package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.QuesitoDescrizione;
import com.ing_sw_2022.app.Risposta;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VisualizzaQuesitiPanel implements ActionListener {
    private JPanel mainPanel;
    private JLabel quesitiRestanti;
    private JButton btnConferma;
    private JPanel quesitiContainer;

    public VisualizzaQuesitiPanel(String idSezione){
        quesitiContainer.setLayout(new GridLayout(0,1));
        try {
            List<QuesitoDescrizione> listaQuesiti= UniCTest.getInstance().visualizzaQuesiti(idSezione);
            for(QuesitoDescrizione qd : listaQuesiti){
                JCheckBox checkBox = new JCheckBox(qd.getTesto());
                checkBox.setName(qd.getId());
                checkBox.addActionListener(this);
                quesitiContainer.add(checkBox);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UniCTest unictest=UniCTest.getInstance();
        try {
            unictest.selezionaQuesito(((JCheckBox)e.getSource()).getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
