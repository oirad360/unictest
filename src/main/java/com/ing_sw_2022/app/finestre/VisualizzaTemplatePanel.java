package com.ing_sw_2022.app.finestre;

import com.ing_sw_2022.app.TemplatePersonalizzato;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisualizzaTemplatePanel implements ActionListener {
    private JPanel mainPanel;
    private JPanel buttonsContainer;

    public VisualizzaTemplatePanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;

        UniCTest uniCTest = UniCTest.getInstance();
        ArrayList<TemplatePersonalizzato> listaTemplate = uniCTest.visualizzaTemplate();
        for(TemplatePersonalizzato tp : listaTemplate){

            JButton btnTemplate = new JButton(tp.getNome());
            btnTemplate.setName(String.valueOf(tp.getId()));
            btnTemplate.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnTemplate.addActionListener(this);

            buttonsContainer.add(btnTemplate, c);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //esegue il metodo nuovoQuesito di unictest e apre la nuova panel per il metodo inserisciFonte
        UniCTest unictest = UniCTest.getInstance();
        AvviaSimulazioneFrame avviaSimulazioneFrame = AvviaSimulazioneFrame.getInstance();
        String idTemplate=((JButton)e.getSource()).getName(); //prende il nome del bottone, cioè l'id del template
        unictest.avviaSimulazione(idTemplate);
        avviaSimulazioneFrame.setContentPane(new InserisciFontePanel().getMainPanel());
        avviaSimulazioneFrame.revalidate(); //serve per far comparire la nuova panel, altrimenti compare solo dopo aver ridimensionato la finestra (boh)
    }
}
