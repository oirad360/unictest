package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.Sezione;
import com.ing_sw_2022.app.Template;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CreaTestCartaceoPanel implements ActionListener {
    private JPanel mainPanel;
    private JPanel sezioniContainer;
    private List<Sezione> listaSezioni;

    public CreaTestCartaceoPanel(String idTemplate){
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;

        UniCTest uniCTest = UniCTest.getInstance();
        try {
            listaSezioni = uniCTest.creaTestCartaceo(idTemplate);
            for(Sezione s : listaSezioni){
                JButton btnSezione = new JButton(s.getMateria().getNome());
                btnSezione.setName(s.getId()+"//"+s.getNumQuesiti());
                btnSezione.setAlignmentX(Component.CENTER_ALIGNMENT);
                btnSezione.addActionListener(this);
                sezioniContainer.add(btnSezione, c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public CreaTestCartaceoPanel(List<Sezione> listaSezioni) {
        this.listaSezioni=listaSezioni;
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;

        for(Sezione s : listaSezioni){
            JButton btnSezione = new JButton(s.getMateria().getNome());
            btnSezione.setName(s.getId()+"//"+s.getNumQuesiti());
            btnSezione.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnSezione.addActionListener(this);
            sezioniContainer.add(btnSezione, c);
        }
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TestCartaceoFrame testCartaceoFrame = TestCartaceoFrame.getInstance();
        String idSezione=((JButton)e.getSource()).getName().split("//")[0];
        String numQuesiti=((JButton)e.getSource()).getName().split("//")[1];
        try {
            testCartaceoFrame.setContentPane(new VisualizzaQuesitiPanel(idSezione,Integer.parseInt(numQuesiti),listaSezioni).getMainPanel());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        testCartaceoFrame.revalidate();
    }
}
