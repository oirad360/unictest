package com.ing_sw_2022.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UniCTestFrame extends JFrame implements ActionListener{
    private JPanel mainPanel;
    private JLabel mainLabel;
    private JButton btnNuovoQuesito;
    private static UniCTestFrame unictestFrame;

    private UniCTestFrame(){
        unictestFrame=this;
        setContentPane(mainPanel);
        setTitle("UniCTest");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btnNuovoQuesito.addActionListener(this);
    }
    public static UniCTestFrame getInstance() {
        if (unictestFrame == null)
            unictestFrame = new UniCTestFrame();
        else
            System.out.println("Istanza già creata");

        return unictestFrame;
    }
    public void actionPerformed(ActionEvent e) {
        JFrame quesitoFrame = new NuovoQuesitoFrame(new VisualizzaMateriePanel());
        btnNuovoQuesito.removeActionListener(this);
        btnNuovoQuesito.setEnabled(false);

        quesitoFrame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                btnNuovoQuesito.setEnabled(true);
                btnNuovoQuesito.addActionListener(unictestFrame);
                e.getWindow().dispose();
            }
        });
    }
    public JButton getBtnNuovoQuesito() {
        return btnNuovoQuesito;
    }

    public static void main(String[] args )
    {
        //////////// CASO D'USO DI AVVIAMENTO /////////
        UniCTest unictest = UniCTest.getInstance();
        Tutor t = new Tutor("Mario", "Rossi", "CR7");
        unictest.setTutorAutenticato(t);
        Materia m = new Materia("Matematica", "MAT01");
        Materia n = new Materia("Italiano", "ITA02");
        unictest.addMateria(m.getCodice(), m);
        unictest.addMateria(n.getCodice(), n);
        t.addMateriaInsegnata(m);
        t.addMateriaInsegnata(n);
        Visibilità v1 = new Visibilità("Personale", "P1");
        Visibilità v2 = new Visibilità("Privato", "P2");
        Visibilità v3 = new Visibilità("Pubblico", "P3");
        unictest.addVisibilità(v1.getCodice(),v1);
        unictest.addVisibilità(v2.getCodice(),v2);
        unictest.addVisibilità(v3.getCodice(),v3);

        ////////// APERTURA GUI ////////////
        UniCTestFrame mainFrame = new UniCTestFrame();

    }
}
