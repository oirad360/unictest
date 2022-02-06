package com.ing_sw_2022.app.ui;

import javax.swing.*;

public class CorreggiTestCartaceoFrame extends JFrame {
    private static CorreggiTestCartaceoFrame correggiTestCartaceoFrame;

    private CorreggiTestCartaceoFrame()  {
        setContentPane(new NomeFilePngPanel().getMainPanel());
        setTitle("Nuova simulazione");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+30,UniCTestFrame.getPos()+30);

    }
    public static CorreggiTestCartaceoFrame getInstance() {
        if (correggiTestCartaceoFrame == null) correggiTestCartaceoFrame = new CorreggiTestCartaceoFrame();
        return correggiTestCartaceoFrame;
    }

    public static void destroyInstance() {
        correggiTestCartaceoFrame = null;
    }
}
