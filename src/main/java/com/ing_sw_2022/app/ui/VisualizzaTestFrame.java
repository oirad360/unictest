package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.eccezioni.EmployeeNotAllowedException;

import javax.swing.*;

public class VisualizzaTestFrame extends JFrame{
    private static VisualizzaTestFrame visualizzaTestFrame;

    private VisualizzaTestFrame() throws EmployeeNotAllowedException {
        setContentPane(new VisualizzaTestPanel().getMainPanel());
        setTitle("Visualizza test");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+25,UniCTestFrame.getPos()+35);

    }
    public static VisualizzaTestFrame getInstance() throws EmployeeNotAllowedException {
        if (visualizzaTestFrame == null) visualizzaTestFrame = new VisualizzaTestFrame();
        return visualizzaTestFrame;
    }

    public static void destroyInstance() {
        visualizzaTestFrame = null;
    }
}
