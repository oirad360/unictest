package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;
import com.ing_sw_2022.app.eccezioni.EmployeeNotAllowedException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;

import javax.swing.*;
import java.awt.event.*;

public class AvviaSimulazionePanel implements ActionListener {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JButton btnConsegna;
    private JLabel timeLabel;
    private int totalTime;
    private Timer timer;

    public AvviaSimulazionePanel(Test test){
        for(QuesitoReale qr : test.getMappaQuesiti().values()){
            int index = Integer.parseInt(qr.getId().split("-")[2])+1;
            tabbedPane.addTab(String.valueOf(index),new TabQuesitoPanel(qr).getMainPanel());
        }
        btnConsegna.addActionListener(this);
        Template template= test.getTemplate();
        if(template instanceof TemplatePersonalizzato) totalTime=((TemplatePersonalizzato)template).getTempoMedio()*test.getMappaQuesiti().size()*60;
        else totalTime=((TemplateUfficiale)template).getTempoTotale()*60;
        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //hh:mm:ss
                timeLabel.setText(String.format("%02d",(totalTime/3600) % 24)+":"+String.format("%02d", (totalTime/60) % 60)+":"+String.format("%02d",totalTime%60));
                totalTime--;
                if(totalTime==-1) {
                    timer.stop();
                    btnConsegna.doClick();
                }
            }

        });
        timer.start();
        try {
            AvviaSimulazioneFrame.getInstance().addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    timer.stop();
                }
            });
        } catch (EmployeeNotAllowedException e) {
            e.printStackTrace();
        }
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        try {
            Test t = UniCTest.getInstance().terminaSimulazione();
            UniCTest.getInstance().serialize();
            AvviaSimulazioneFrame.getInstance().setContentPane(new TestCorrettoPanel(t).getMainPanel());
            AvviaSimulazioneFrame.getInstance().revalidate();
        } catch (EmployeeNotAllowedException ex) {
            ex.printStackTrace();
        }

    }
}
