package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VisualizzaTemplatePanel implements ActionListener {
    private JPanel mainPanel;
    private JPanel buttonsContainer;

    public VisualizzaTemplatePanel() throws Exception {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;

        UniCTest uniCTest = UniCTest.getInstance();
        ArrayList<Template> listaTemplate = uniCTest.visualizzaTemplate();
        for(Template t : listaTemplate){
            JButton btnTemplate = new JButton(t.getNome());
            btnTemplate.setName(String.valueOf(t.getId()));
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
        UniCTest unictest = UniCTest.getInstance();
        if(unictest.getUtenteAutenticato() instanceof Studente){
            AvviaSimulazioneFrame avviaSimulazioneFrame = AvviaSimulazioneFrame.getInstance();
            String idTemplate=((JButton)e.getSource()).getName();
            Test t= null;
            try {
                t = unictest.avviaSimulazione(idTemplate);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if(t!=null) {
                avviaSimulazioneFrame.setContentPane(new AvviaSimulazionePanel(t).getMainPanel());
                avviaSimulazioneFrame.revalidate();
            }
        } else if(unictest.getUtenteAutenticato() instanceof Impiegato){
            TestCartaceoFrame testCartaceoFrame = TestCartaceoFrame.getInstance();
            String idTemplate=((JButton)e.getSource()).getName();
            testCartaceoFrame.setContentPane(new CreaTestCartaceoPanel(idTemplate).getMainPanel());
            testCartaceoFrame.revalidate();
        }


    }
}
