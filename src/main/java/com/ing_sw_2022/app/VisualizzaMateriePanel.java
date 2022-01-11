package com.ing_sw_2022.app;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;

public class VisualizzaMateriePanel extends JPanel implements ActionListener {

    private JPanel panel;
    private UniCTest unictest;

    public VisualizzaMateriePanel(ArrayList<JButton> buttons) {
        unictest = UniCTest.getInstance();

        List<Materia> materieInsegnate = unictest.visualizzaMaterieInsegnate();

        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        //c.weightx = 1;
        //c.weighty = .25;
        c.insets = new Insets(5, 0, 5, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;
        //c.fill = GridBagConstraints.BOTH;

        JLabel label = new JLabel("Scegli la materia", SwingConstants.CENTER);
        label.setFont(new Font("Verdana", Font.PLAIN, 28));

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

    /** Listens to the radio buttons. */
    public void actionPerformed(ActionEvent e) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.dispose();
        String codiceMateria=((JButton)e.getSource()).getName();
        unictest.nuovoQuesito(codiceMateria);
        JFrame quesitoFrame = new NuovoQuesitoFrame(new NuovoQuesitoPanel());
        quesitoFrame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                UniCTestFrame unictestFrame=UniCTestFrame.getInstance();
                unictestFrame.getBtnNuovoQuesito().setEnabled(true);
                unictestFrame.getBtnNuovoQuesito().addActionListener(unictestFrame);
                e.getWindow().dispose();
            }
        });

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = VisualizzaMateriePanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("VisualizzaMateriePanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new VisualizzaMateriePanel();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}