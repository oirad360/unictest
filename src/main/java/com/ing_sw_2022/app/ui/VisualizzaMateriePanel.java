package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.Materia;
import com.ing_sw_2022.app.UniCTest;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;

public class VisualizzaMateriePanel extends JPanel implements ActionListener{

    public VisualizzaMateriePanel() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel label = new JLabel("Scegli la materia", SwingConstants.CENTER);
        label.setFont(new Font("Verdana", Font.PLAIN, 28));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;

        UniCTest unictest = UniCTest.getInstance();
        List<Materia> materieInsegnate = unictest.visualizzaMaterieInsegnate();

        for (Iterator<Materia> i = materieInsegnate.iterator(); i.hasNext();) {
            Materia materia = i.next();

            JButton btnMateria = new JButton(materia.getNome());
            btnMateria.setName(materia.getCodice());
            btnMateria.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnMateria.addActionListener(this);

            panel.add(btnMateria,c);
        }

        JScrollPane scroller = new JScrollPane(panel);
        scroller.setPreferredSize(new Dimension(450,300));

        add(label, BorderLayout.PAGE_START);
        add(scroller, BorderLayout.CENTER);
    }


    @Override
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