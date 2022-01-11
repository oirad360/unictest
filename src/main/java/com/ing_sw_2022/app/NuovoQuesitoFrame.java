package com.ing_sw_2022.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NuovoQuesitoFrame extends JFrame implements  ActionListener{
    private UniCTest unictest;
    private static NuovoQuesitoFrame nuovoQuesitoFrame;

    private NuovoQuesitoFrame(){
        //esegue il metodo visualizzaMaterieInsegnate di unictest e apre la panel per visualizzarle a schermo con dei bottoni
        unictest = UniCTest.getInstance();
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        List<Materia> materieInsegnate = unictest.visualizzaMaterieInsegnate();
        for (Iterator<Materia> i = materieInsegnate.iterator(); i.hasNext();) {
            Materia materia = i.next();

            JButton btnMateria = new JButton(materia.getNome());
            btnMateria.setName(materia.getCodice());
            btnMateria.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnMateria.addActionListener(this);

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

    public void actionPerformed(ActionEvent e) {
        //esegue il metodo nuovoQuesito di unictest e apre la nuova panel per il metodo inserisciFonte
        UniCTest unictest = UniCTest.getInstance();
        NuovoQuesitoFrame nuovoQuesitoFrame = NuovoQuesitoFrame.getInstance();
        String codiceMateria=((JButton)e.getSource()).getName(); //prende il nome della materia dal bottone che ha scatenato l'evento "e"
        unictest.nuovoQuesito(codiceMateria);
        nuovoQuesitoFrame.setContentPane(new InserisciFontePanel().getMainPanel());
        nuovoQuesitoFrame.revalidate(); //serve per far comparire la nuova panel, altrimenti compare solo dopo aver ridimensionato la finestra (boh)
    }
}



