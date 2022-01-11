package com.ing_sw_2022.app;

import javax.swing.*;
import java.awt.*;

public class InserisciFontePanel extends JPanel {

    public InserisciFontePanel(){
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Inserisci fonte", SwingConstants.CENTER);
        label.setFont(new Font("Verdana", Font.PLAIN, 28));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));

        JTextField textField = new JTextField();
        textField.setBounds(50,50,150,20);
        JButton btn = new JButton("OK");

        panel.add(textField);
        panel.add(btn);

        add(label, BorderLayout.PAGE_START);
        add(panel, BorderLayout.CENTER);
    }
}
