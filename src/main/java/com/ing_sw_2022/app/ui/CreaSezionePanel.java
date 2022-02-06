package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
    private JLabel labelDifficoltà;
    private Integer counter;
    private List<Materia> listaMaterie;

    public CreaSezionePanel(final List<Materia> listaMaterie){
        if(UniCTest.getInstance().getUtenteAutenticato() instanceof Impiegato) {
            labelDifficoltà.setVisible(false);
            difficoltàMedia.setVisible(false);
        }
        this.listaMaterie=listaMaterie;
        counter=0;
        numSezioni.setText(counter.toString());
        buttonConferma.setEnabled(false);
        Item items[]=new Item[listaMaterie.size()];
        int i=0;
        for (Materia m : listaMaterie) {
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
                boolean exception=false;
                    if(UniCTest.getInstance().getUtenteAutenticato() instanceof Studente) {
                        try {
                            UniCTest.getInstance().creaSezioneP(((Item)materiaBox.getSelectedItem()).getId(),(int)numQuesiti.getValue(),(int)difficoltàMedia.getValue());
                        } catch (EmployeeNotAllowedException ex) {
                            exception=true;
                            ex.printStackTrace();
                        }
                    }
                    else if(UniCTest.getInstance().getUtenteAutenticato() instanceof Impiegato) {
                        try {
                            UniCTest.getInstance().creaSezioneP(((Item)materiaBox.getSelectedItem()).getId(),(int)numQuesiti.getValue());
                        } catch (StudentNotAllowedException ex) {
                            exception=true;
                            ex.printStackTrace();
                        } catch (NotAllowedException ex) {
                            exception=true;
                            ex.printStackTrace();
                        }
                    }
                    if(!exception){
                        counter++;
                        buttonConferma.setEnabled(true);
                        numSezioni.setText(counter.toString());
                        listaMaterie.remove(materiaBox.getSelectedIndex());
                        Item items[]=new Item[listaMaterie.size()];
                        int i=0;
                        for (Materia m: listaMaterie) {
                            items[i]=new Item(m.getCodice(),m.getNome());
                            i++;
                        }
                        materiaBox.setModel(new DefaultComboBoxModel<Item>(items));
                        if(listaMaterie.isEmpty()){
                            buttonConferma.doClick();
                        }
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
            UniCTest.getInstance().confermaTemplateP();
            UniCTest.getInstance().serialize();
            NuovoTemplatePersFrame.getInstance().dispose();
        } catch (NotAllowedException ex) {
            ex.printStackTrace();
        }

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

    public String toString()
    {
        return description;
    }
}