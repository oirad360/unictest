package com.ing_sw_2022.app;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NuovoQuesitoFrame extends JFrame{
    //private UniCTest unictest;
    public NuovoQuesitoFrame(final JPanel nextPanel){
        /*unictest = UniCTest.getInstance();
        ArrayList<JButton> buttons = new ArrayList<JButton>()
        List<Materia> materieInsegnate = unictest.visualizzaMaterieInsegnate();
        for (Iterator<Materia> i = materieInsegnate.iterator(); i.hasNext();) {
            Materia materia = i.next();

            JButton btnMateria = new JButton(materia.getNome());
            btnMateria.setName(materia.getCodice());
            btnMateria.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnMateria.addActionListener(this);

            buttons.add(JButton);
        }*/
        setContentPane(nextPanel);
        setTitle("Nuovo quesito");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

    }
}
