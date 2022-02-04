package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VisualizzaQuesitiPanel implements ActionListener {
    private JPanel mainPanel;
    private JLabel quesitiRichiesti;
    private JButton btnConferma;
    private JPanel quesitiContainer;
    private int numQuesiti;
    private ArrayList<String> listaIdQuesiti;

    public VisualizzaQuesitiPanel(String idSezione,int numQuesiti){
        this.numQuesiti=numQuesiti;
        listaIdQuesiti = new ArrayList<>();
        btnConferma.setEnabled(false);
        quesitiRichiesti.setText(String.valueOf(numQuesiti));
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
        btnConferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UniCTest.getInstance().inserisciQuesiti(listaIdQuesiti);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String idQuesito=((JCheckBox)e.getSource()).getName();
            boolean found=false;
            int i=0;
            for(String s:listaIdQuesiti){
                if(s==idQuesito){
                    listaIdQuesiti.remove(i);
                    found=true;
                    break;
                }
                i++;
            }
            if(!found) listaIdQuesiti.add(idQuesito);
            if(listaIdQuesiti.size()==numQuesiti) btnConferma.setEnabled(true);
            else btnConferma.setEnabled(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
