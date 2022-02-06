package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.EmployeeNotAllowedException;
import com.ing_sw_2022.app.NotAllowedException;

import javax.swing.*;

public class AvviaSimulazioneFrame extends JFrame{
    private static AvviaSimulazioneFrame avviaSimulazioneFrame;

    private AvviaSimulazioneFrame() throws EmployeeNotAllowedException {
        VisualizzaTemplatePanel panel=new VisualizzaTemplatePanel();
        setContentPane(panel.getMainPanel());
        setTitle("Nuova simulazione");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+30,UniCTestFrame.getPos()+30);

    }
    public static AvviaSimulazioneFrame getInstance() throws EmployeeNotAllowedException {
        if (avviaSimulazioneFrame == null) avviaSimulazioneFrame = new AvviaSimulazioneFrame();
        return avviaSimulazioneFrame;
    }

    public static void destroyInstance() {
        avviaSimulazioneFrame = null;
    }
}
