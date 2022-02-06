package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.eccezioni.EmployeeNotAllowedException;
import com.ing_sw_2022.app.NotAllowedException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;

import javax.swing.*;

public class AvviaSimulazioneFrame extends JFrame{
    private static AvviaSimulazioneFrame avviaSimulazioneFrame;

    private AvviaSimulazioneFrame() throws StudentNotAllowedException, NotAllowedException, EmployeeNotAllowedException {
        VisualizzaTemplatePanel panel=new VisualizzaTemplatePanel();
        setContentPane(panel.getMainPanel());
        setTitle("Nuova simulazione");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+30,UniCTestFrame.getPos()+30);

    }
    public static AvviaSimulazioneFrame getInstance() throws StudentNotAllowedException, NotAllowedException, EmployeeNotAllowedException {
        if (avviaSimulazioneFrame == null) avviaSimulazioneFrame = new AvviaSimulazioneFrame();
        return avviaSimulazioneFrame;
    }

    public static void destroyInstance() {
        avviaSimulazioneFrame = null;
    }
}
