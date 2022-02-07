package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.Test;

import javax.swing.*;

public class VisualizzaTestCompletoFrame extends JFrame {
    public VisualizzaTestCompletoFrame(Test t){
        setContentPane(new TestCorrettoPanel(t).getMainPanel());
        setTitle("Test "+t.getId());
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+30,UniCTestFrame.getPos()+30);
    }
}
