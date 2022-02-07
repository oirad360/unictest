package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BottoneTestPanel implements ActionListener {
    private JPanel mainPanel;
    private JLabel id;
    private JLabel templateID;
    private JLabel template;
    private JButton visualizzaButton;
    private Test test;

    public BottoneTestPanel(Test t) {
        test=t;
        this.id.setText(t.getId());
        this.template.setText(t.getTemplate().getNome());
        this.templateID.setText(t.getTemplate().getId());
        visualizzaButton.addActionListener(this);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        visualizzaButton.setEnabled(true);
        VisualizzaTestCompletoFrame newFrame =new VisualizzaTestCompletoFrame(test);
        newFrame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                visualizzaButton.setEnabled(true);
            }
            @Override
            public void windowClosed(WindowEvent e)
            {
                visualizzaButton.setEnabled(true);
            }
        });
    }
}
