package com.ing_sw_2022.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UniCTestFrame extends JFrame{
    private JPanel mainPanel;
    private JLabel mainLabel;
    private JButton btnNuovoQuesito;


    public UniCTestFrame(){
        setContentPane(mainPanel);
        setTitle("UniCTest");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnNuovoQuesito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NuovoQuesitoFrame frameQuesito = new NuovoQuesitoFrame();
                btnNuovoQuesito.removeActionListener(this);
            }
        });
    }

    public static void main( String[] args )
    {
        UniCTestFrame mainFrame = new UniCTestFrame();
    }
}
