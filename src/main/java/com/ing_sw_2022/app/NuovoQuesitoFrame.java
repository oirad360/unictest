package com.ing_sw_2022.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NuovoQuesitoFrame extends JFrame{
    private UniCTest unictest;
    private static NuovoQuesitoFrame nuovoQuesitoFrame;

    private NuovoQuesitoFrame(){
        unictest = UniCTest.getInstance();
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        List<Materia> materieInsegnate = unictest.visualizzaMaterieInsegnate();
        for (Iterator<Materia> i = materieInsegnate.iterator(); i.hasNext();) {
            Materia materia = i.next();

            JButton btnMateria = new JButton(materia.getNome());
            btnMateria.setName(materia.getCodice());
            btnMateria.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnMateria.addActionListener(new InserisciFonte());

            buttons.add(btnMateria);
        }
        setContentPane(new VisualizzaMateriePanel(buttons));
        setTitle("Nuovo quesito");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

    }
    public static NuovoQuesitoFrame getInstance() {
        if (nuovoQuesitoFrame == null) nuovoQuesitoFrame = new NuovoQuesitoFrame();
        return nuovoQuesitoFrame;
    }

    public static void destroyInstance() {
        nuovoQuesitoFrame = null;
    }
}

class InserisciFonte implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        UniCTest unictest = UniCTest.getInstance();
        NuovoQuesitoFrame nuovoQuesitoFrame = NuovoQuesitoFrame.getInstance();
        String codiceMateria=((JButton)e.getSource()).getName();
        unictest.nuovoQuesito(codiceMateria);
        nuovoQuesitoFrame.setContentPane(new NuovoQuesitoPanel());
    }
}

class InserisciTesto implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        UniCTest unictest = UniCTest.getInstance();
        UniCTestFrame unictestFrame = UniCTestFrame.getInstance();
        String codiceMateria=((JButton)e.getSource()).getName();
        unictest.nuovoQuesito(codiceMateria);
        unictestFrame.setContentPane(new InserisciFontePanel());
    }
}

