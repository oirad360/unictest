package com.ing_sw_2022.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NuovoQuesitoFrame extends JFrame{
    public NuovoQuesitoFrame(final JButton btn){
        setContentPane(new NuovoQuesitoPanel());
        setTitle("Nuovo quesito");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                btn.setEnabled(true);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NuovoQuesitoFrame frameQuesito = new NuovoQuesitoFrame(btn);
                        btn.removeActionListener(this);
                        btn.setEnabled(false);
                    }
                });
                e.getWindow().dispose();
            }
        });
    }
}
