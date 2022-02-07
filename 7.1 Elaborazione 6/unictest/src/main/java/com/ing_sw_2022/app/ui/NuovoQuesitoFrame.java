package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;

import javax.swing.*;

public class NuovoQuesitoFrame extends JFrame{
    private static NuovoQuesitoFrame nuovoQuesitoFrame;

    private NuovoQuesitoFrame() throws StudentNotAllowedException {
        setContentPane(new VisualizzaMateriePanel());
        setTitle("Nuovo quesito");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+30,UniCTestFrame.getPos()+30);

    }
    public static NuovoQuesitoFrame getInstance() throws StudentNotAllowedException {
        if (nuovoQuesitoFrame == null) nuovoQuesitoFrame = new NuovoQuesitoFrame();
        return nuovoQuesitoFrame;
    }

    public static void destroyInstance() {
        nuovoQuesitoFrame = null;
    }

}



