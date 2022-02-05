package com.ing_sw_2022.app.ui;

import javax.swing.*;

public class VisualizzaTestFrame extends JFrame{
    private static VisualizzaTestFrame visualizzaTestFrame;

    private VisualizzaTestFrame(){
        setContentPane(new VisualizzaTestPanel().getMainPanel());
        setTitle("Visualizza test");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+25,UniCTestFrame.getPos()+35);

    }
    public static VisualizzaTestFrame getInstance() {
        if (visualizzaTestFrame == null) visualizzaTestFrame = new VisualizzaTestFrame();
        return visualizzaTestFrame;
    }

    public static void destroyInstance() {
        visualizzaTestFrame = null;
    }
}
