package com.ing_sw_2022.app.ui;

import javax.swing.*;

public class AvviaSimulazioneFrame extends JFrame{
    private static AvviaSimulazioneFrame avviaSimulazioneFrame;

    private AvviaSimulazioneFrame() throws Exception {
        setContentPane(new VisualizzaTemplatePanel().getMainPanel());
        setTitle("Nuova simulazione");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+30,UniCTestFrame.getPos()+30);

    }
    public static AvviaSimulazioneFrame getInstance() {
        if (avviaSimulazioneFrame == null) {
            try {
                avviaSimulazioneFrame = new AvviaSimulazioneFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return avviaSimulazioneFrame;
    }

    public static void destroyInstance() {
        avviaSimulazioneFrame = null;
    }
}
