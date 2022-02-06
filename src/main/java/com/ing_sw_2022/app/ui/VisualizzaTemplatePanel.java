package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;
import com.ing_sw_2022.app.eccezioni.EmployeeNotAllowedException;
import com.ing_sw_2022.app.eccezioni.NotEnoughQuestionsException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisualizzaTemplatePanel implements ActionListener {
    private JPanel mainPanel;
    private JPanel buttonsContainer;

    public VisualizzaTemplatePanel() throws EmployeeNotAllowedException, StudentNotAllowedException, NotAllowedException {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;

        UniCTest uniCTest = UniCTest.getInstance();
        ArrayList<Template> listaTemplate=null;
        if(UniCTest.getInstance().getUtenteAutenticato() instanceof Studente)
            listaTemplate= uniCTest.visualizzaTemplate();
        else listaTemplate= uniCTest.visualizzaTemplateTutor();
        for(Template t : listaTemplate){
            JButton btnTemplate = new JButton(t.getNome());
            btnTemplate.setName(String.valueOf(t.getId()));
            btnTemplate.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnTemplate.addActionListener(this);
            buttonsContainer.add(btnTemplate, c);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UniCTest unictest = UniCTest.getInstance();
        if(unictest.getUtenteAutenticato() instanceof Studente){
            AvviaSimulazioneFrame avviaSimulazioneFrame = null;
            try {
                avviaSimulazioneFrame = AvviaSimulazioneFrame.getInstance();
            } catch (StudentNotAllowedException ex) {
                ex.printStackTrace();
            } catch (NotAllowedException ex) {
                ex.printStackTrace();
            } catch (EmployeeNotAllowedException ex) {
                ex.printStackTrace();
            }
            String idTemplate=((JButton)e.getSource()).getName();
            Test t= null;
            try {
                t = unictest.avviaSimulazione(idTemplate);
            } catch (NotEnoughQuestionsException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(new JFrame(),
                        ex.getMessage(),
                        "Inane warning",
                        JOptionPane.WARNING_MESSAGE);
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(new JFrame(),
                        ex.getMessage(),
                        "Inane warning",
                        JOptionPane.WARNING_MESSAGE);
            } catch (EmployeeNotAllowedException ex) {
                ex.printStackTrace();
            }
            if(t!=null) {
                avviaSimulazioneFrame.setContentPane(new AvviaSimulazionePanel(t).getMainPanel());
                avviaSimulazioneFrame.revalidate();
            }
        } else if(unictest.getUtenteAutenticato() instanceof Impiegato){
            TestCartaceoFrame testCartaceoFrame = null;
            try {
                testCartaceoFrame = TestCartaceoFrame.getInstance();
            } catch (EmployeeNotAllowedException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(new JFrame(),
                        ex.getMessage(),
                        "Inane warning",
                        JOptionPane.WARNING_MESSAGE);
            } catch (StudentNotAllowedException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(new JFrame(),
                        ex.getMessage(),
                        "Inane warning",
                        JOptionPane.WARNING_MESSAGE);
            } catch (NotAllowedException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(new JFrame(),
                        ex.getMessage(),
                        "Inane warning",
                        JOptionPane.WARNING_MESSAGE);
            }
            String idTemplate=((JButton)e.getSource()).getName();
            testCartaceoFrame.setContentPane(new CreaTestCartaceoPanel(idTemplate).getMainPanel());
            testCartaceoFrame.revalidate();
        }


    }
}
