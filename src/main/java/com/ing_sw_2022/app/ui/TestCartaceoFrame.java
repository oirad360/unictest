package com.ing_sw_2022.app.ui;

import javax.swing.*;

public class TestCartaceoFrame extends JFrame{
    private static TestCartaceoFrame testCartaceoFrame;

    private TestCartaceoFrame() throws Exception {
        setContentPane(new VisualizzaTemplatePanel().getMainPanel()); ///////TAG
        setTitle("Crea test per simulazione cartacea");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+30,UniCTestFrame.getPos()+30);

    }
    public static TestCartaceoFrame getInstance() {
        if (testCartaceoFrame == null) {
            try {
                testCartaceoFrame = new TestCartaceoFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return testCartaceoFrame;
    }

    public static void destroyInstance() {
        testCartaceoFrame = null;
    }
}
