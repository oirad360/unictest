package com.ing_sw_2022.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CreaSezionePanel implements ActionListener {
    private JPanel mainPanel;
    private JLabel mainLabel;
    private JScrollPane scroller;
    private JPanel panel;
    private JSpinner numQuesiti;
    private JSpinner difficoltàMedia;
    private JButton button;
    private JPanel panelBox;
    private JComboBox materiaBox;
    private JLabel numSezioni;
    private JButton buttonConferma;
    private Integer counter;
    private HashMap<String, Materia> mappaMaterie;

    public CreaSezionePanel(HashMap<String,Materia> mappaMaterie){
        counter=0;
        numSezioni.setText(counter.toString());
        buttonConferma.setEnabled(false);
        this.mappaMaterie=mappaMaterie;
        Item items[]=new Item[mappaMaterie.size()];
        int i=0;
        for (Materia m : mappaMaterie.values()) {
            items[i]=new Item(m.getCodice(),m.getNome());
            i++;
        }
        materiaBox.setModel(new DefaultComboBoxModel<Item>(items));
        difficoltàMedia.setModel(new SpinnerNumberModel(3,1 ,5,1));
        numQuesiti.setModel(new SpinnerNumberModel(1,1 ,null,1));
        buttonConferma.addActionListener(this);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                buttonConferma.setEnabled(true);
                numSezioni.setText(counter.toString());
                UniCTest.getInstance().creaSezione(((Item)materiaBox.getSelectedItem()).getId(),(int)numQuesiti.getValue(),(int)difficoltàMedia.getValue());
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UniCTest.getInstance().confermaTemplate();
        NuovoTemplatePersFrame.getInstance().dispose();
    }

}

class Item
{
    private String id;
    private String description;

    public Item(String id, String description)
    {
        this.id = id;
        this.description = description;
    }

    public String getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }

    public String toString()
    {
        return description;
    }
}