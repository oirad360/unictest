package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VisualizzaQuesitiCompletiFrame extends JFrame{
    private JPanel mainPanel;
    private JPanel containerQuesiti;
    private static VisualizzaQuesitiCompletiFrame visualizzaQuesitiCompletiFrame;
    private VisualizzaQuesitiCompletiFrame() throws StudentNotAllowedException {
        setContentPane(mainPanel);
        setTitle("Visualizza quesiti");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+50,UniCTestFrame.getPos()+30);
        containerQuesiti.setLayout(new GridLayout(0,1));
        Map<String, Materia> mappaMaterie=UniCTest.getInstance().getMappaMaterie();
        for(Materia m: mappaMaterie.values()){
            for(QuesitoDescrizione qd: m.getMappaQuesiti().values()){
                String risposte="";
                for(Risposta r:qd.getRisposte().values()){
                    String val=r.isValore()?"[CORRETTA]":"[ERRATA]";
                    risposte+=r.getId()+" "+val+": "+r.getTesto()+"\n";
                }
                containerQuesiti.add(new QuesitoPanel(risposte,qd.getTesto(),qd.getId(),qd.getFonte(),m.getNome(),String.valueOf(qd.getDifficoltà()),qd.getTutor().getCf()+" "+qd.getTutor().getNome()+" "+qd.getTutor().getCognome(),qd.getVisibilità().getCodice()+"="+qd.getVisibilità().getNome()).getMainPanel());
            }
        }
    }

    public static VisualizzaQuesitiCompletiFrame getInstance() throws StudentNotAllowedException {
        if(visualizzaQuesitiCompletiFrame==null) visualizzaQuesitiCompletiFrame=new VisualizzaQuesitiCompletiFrame();
        return visualizzaQuesitiCompletiFrame;
    }
    public static void destroyInstance() {
        visualizzaQuesitiCompletiFrame = null;
    }
}
