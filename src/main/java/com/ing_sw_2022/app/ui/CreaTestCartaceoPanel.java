package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;
import com.ing_sw_2022.app.eccezioni.EmployeeNotAllowedException;
import com.ing_sw_2022.app.eccezioni.NotEnoughQuestionsException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreaTestCartaceoPanel implements ActionListener {
    private JPanel mainPanel;
    private JPanel sezioniContainer;
    private List<Sezione> listaSezioni;

    public CreaTestCartaceoPanel(String idTemplate){
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;

        UniCTest uniCTest = UniCTest.getInstance();

        try {
            listaSezioni = uniCTest.creaTestCartaceo(idTemplate);
        } catch (StudentNotAllowedException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(),
                    e.getMessage(),
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
        } catch (NotAllowedException e) {
            e.printStackTrace();
        }
        for(Sezione s : listaSezioni){
                JButton btnSezione = new JButton(s.getMateria().getNome());
                btnSezione.setName(s.getId()+"//"+s.getNumQuesiti());
                btnSezione.setAlignmentX(Component.CENTER_ALIGNMENT);
                btnSezione.addActionListener(this);
                sezioniContainer.add(btnSezione, c);
            }


    }

    public CreaTestCartaceoPanel(List<Sezione> listaSezioni) {
        this.listaSezioni=listaSezioni;
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;

        for(Sezione s : listaSezioni){
            JButton btnSezione = new JButton(s.getMateria().getNome());
            btnSezione.setName(s.getId()+"//"+s.getNumQuesiti());
            btnSezione.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnSezione.addActionListener(this);
            sezioniContainer.add(btnSezione, c);
        }
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TestCartaceoFrame testCartaceoFrame = null;
        try {
            testCartaceoFrame = TestCartaceoFrame.getInstance();
        } catch (EmployeeNotAllowedException ex) {
            ex.printStackTrace();
        } catch (StudentNotAllowedException ex) {
            ex.printStackTrace();
        } catch (NotAllowedException ex) {
            ex.printStackTrace();
        }
        String idSezione=((JButton)e.getSource()).getName().split("//")[0];
        String numQuesiti=((JButton)e.getSource()).getName().split("//")[1];
        try {
            testCartaceoFrame.setContentPane(new VisualizzaQuesitiPanel(idSezione,Integer.parseInt(numQuesiti),listaSezioni).getMainPanel());
        } catch (StudentNotAllowedException ex) {
            ex.printStackTrace();
        } catch (NotEnoughQuestionsException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(),
                    ex.getMessage(),
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
        } catch (NotAllowedException ex) {
            ex.printStackTrace();
        }
        testCartaceoFrame.revalidate();
    }
}
