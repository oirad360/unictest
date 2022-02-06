package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VisualizzaTestPanel {
    private JPanel mainPanel;
    private JPanel testContainer;

    public VisualizzaTestPanel() throws EmployeeNotAllowedException {
        testContainer.setLayout(new GridLayout(0,1));
        if(UniCTest.getInstance().getUtenteAutenticato() instanceof Impiegato) throw new EmployeeNotAllowedException("Gli impiegati non hanno test svolti.");
        Map<String, Template> mappaTemplate=mappaTemplate=((Studente)UniCTest.getInstance().getUtenteAutenticato()).getMappaTemplateTestSvolti();

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
