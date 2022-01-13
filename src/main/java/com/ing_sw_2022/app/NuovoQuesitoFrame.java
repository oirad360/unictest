package com.ing_sw_2022.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NuovoQuesitoFrame extends JFrame{
    private static NuovoQuesitoFrame nuovoQuesitoFrame;

    private NuovoQuesitoFrame(){
        setContentPane(new VisualizzaMateriePanel());
        setTitle("Nuovo quesito");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+30,UniCTestFrame.getPos()+30);

    }
    public static NuovoQuesitoFrame getInstance() {
        if (nuovoQuesitoFrame == null) nuovoQuesitoFrame = new NuovoQuesitoFrame();
        return nuovoQuesitoFrame;
    }

    public static void destroyInstance() {
        nuovoQuesitoFrame = null;
    }

}



