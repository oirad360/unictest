package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.Studente;
import com.ing_sw_2022.app.Template;
import com.ing_sw_2022.app.Test;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VisualizzaTestPanel {
    private JPanel mainPanel;
    private JPanel testContainer;

    public VisualizzaTestPanel() {
        testContainer.setLayout(new GridLayout(0,1));
        Map<String, Template> mappaTemplate=((Studente)UniCTest.getInstance().getUtenteAutenticato()).getMappaTemplateTestSvolti();
        for(Template te: mappaTemplate.values()){
            for(Test t: te.getMappaTest().values()){
                testContainer.add(new BottoneTestPanel(t).getMainPanel());
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}