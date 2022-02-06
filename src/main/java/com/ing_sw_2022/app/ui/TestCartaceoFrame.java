package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.UniCTest;
import com.ing_sw_2022.app.eccezioni.EmployeeNotAllowedException;
import com.ing_sw_2022.app.NotAllowedException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;

import javax.swing.*;

public class TestCartaceoFrame extends JFrame{
    private static TestCartaceoFrame testCartaceoFrame;

    private TestCartaceoFrame() throws StudentNotAllowedException, NotAllowedException {
        setContentPane(new VisualizzaTemplatePanel(UniCTest.getInstance().visualizzaTemplateTutor()).getMainPanel());
        setTitle("Crea test per simulazione cartacea");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+30,UniCTestFrame.getPos()+30);

    }
    public static TestCartaceoFrame getInstance() throws StudentNotAllowedException, NotAllowedException {
        if (testCartaceoFrame == null) testCartaceoFrame = new TestCartaceoFrame();
        return testCartaceoFrame;
    }

    public static void destroyInstance() {
        testCartaceoFrame = null;
    }
}
