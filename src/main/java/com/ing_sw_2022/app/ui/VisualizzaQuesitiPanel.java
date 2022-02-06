package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;
import com.ing_sw_2022.app.eccezioni.NotEnoughQuestionsException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VisualizzaQuesitiPanel implements ActionListener {
    private JPanel mainPanel;
    private JLabel quesitiRichiesti;
    private JButton btnConferma;
    private JPanel quesitiContainer;
    private int numQuesiti;
    private ArrayList<String> listaIdQuesiti;
    private List<Sezione> listaSezioni;

    public VisualizzaQuesitiPanel(String idSezione,int numQuesiti,List<Sezione> listaSezioni) throws StudentNotAllowedException, NotEnoughQuestionsException, NotAllowedException {
        this.numQuesiti=numQuesiti;
        this.listaSezioni=listaSezioni;
        listaIdQuesiti = new ArrayList<>();
        btnConferma.setEnabled(false);
        quesitiRichiesti.setText(String.valueOf(numQuesiti));
        quesitiContainer.setLayout(new GridLayout(0,1));
        List<QuesitoDescrizione> listaQuesiti= UniCTest.getInstance().visualizzaQuesiti(idSezione);
        for(QuesitoDescrizione qd : listaQuesiti) {
            JCheckBox checkBox = new JCheckBox(qd.getTesto());
            checkBox.setName(qd.getId());
            checkBox.addActionListener(this);
            quesitiContainer.add(checkBox);
        }
        btnConferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UniCTest.getInstance().inserisciQuesiti(listaIdQuesiti);
                    int i=0;
                    for(Sezione s:listaSezioni){
                        if(s.getId().equals(idSezione)){
                            listaSezioni.remove(i);
                            break;
                        }
                        i++;
                    }
                    if(listaSezioni.isEmpty()){
                        TestCartaceoFrame.getInstance().setContentPane(new NomeFilePdfPanel().getMainPanel());
                        TestCartaceoFrame.getInstance().revalidate();
                    }else{
                        TestCartaceoFrame.getInstance().setContentPane(new CreaTestCartaceoPanel(listaSezioni).getMainPanel());
                        TestCartaceoFrame.getInstance().revalidate();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();//può essere un'eccezione che riguarda il numero di quesiti inseriti non valido
                    //oppure un'eccezione che riguarda il permesso di esecuzione del metodo (nel caso in cui non è un TutorSimulazione a eseguirlo)
                }
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String idQuesito=((JCheckBox)e.getSource()).getName();
            boolean found=false;
            int i=0;
            for(String s:listaIdQuesiti){
                if(s==idQuesito){
                    listaIdQuesiti.remove(i);
                    found=true;
                    break;
                }
                i++;
            }
            if(!found) listaIdQuesiti.add(idQuesito);
            if(listaIdQuesiti.size()==numQuesiti) btnConferma.setEnabled(true);
            else btnConferma.setEnabled(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
