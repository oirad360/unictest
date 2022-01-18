package com.ing_sw_2022.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsInfoTemplatePanel extends JPanel implements ActionListener {
    private JPanel mainPanel;
    private JLabel mainLabel;
    private JScrollPane scroller;
    private JPanel panel;
    private JSpinner puntiCorretta;
    private JSpinner puntiErrata;
    private JSpinner puntiNonData;
    private JSpinner numRisposte;
    private JSpinner numRisposteCorrette;
    private JSpinner tempoMedio;
    private SpinnerNumberModel puntiCorrettaModel;
    private SpinnerNumberModel puntiErrataModel;
    private SpinnerNumberModel puntiNonDataModel;
    private SpinnerNumberModel numRisposteModel;
    private SpinnerNumberModel numRisposteCorretteModel;
    private SpinnerNumberModel tempoMedioModel;
    private JButton button;

    public InsInfoTemplatePanel(){
        button.addActionListener(this);
        puntiCorrettaModel = (SpinnerNumberModel) puntiCorretta.getModel();
        puntiErrataModel = (SpinnerNumberModel) puntiErrata.getModel();
        puntiNonDataModel = (SpinnerNumberModel) puntiNonData.getModel();
        numRisposteModel = (SpinnerNumberModel) numRisposte.getModel();
        numRisposteCorretteModel = (SpinnerNumberModel) numRisposteCorrette.getModel();
        tempoMedioModel = (SpinnerNumberModel) tempoMedio.getModel();
        puntiCorrettaModel.setMinimum(0.000000001);
        puntiErrataModel.setMaximum(0);
        puntiNonDataModel.setMaximum(0);
        numRisposteModel.setMinimum(2);
        numRisposteModel.setValue(2);
        numRisposteCorretteModel.setMinimum(1);
        numRisposteCorretteModel.setValue(1);
        tempoMedioModel.setMinimum(1);
        tempoMedioModel.setValue(1);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
