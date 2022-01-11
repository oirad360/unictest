package com.ing_sw_2022.app;

import javax.swing.*;
import java.awt.*;

public class InserisciFontePanel extends JPanel {
    private UniCTest unictest;
    private UniCTestFrame uniCTestFrame;
    public InserisciFontePanel(){
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Inserisci fonte", SwingConstants.CENTER);
        label.setFont(new Font("Verdana", Font.PLAIN, 28));
    }
}
