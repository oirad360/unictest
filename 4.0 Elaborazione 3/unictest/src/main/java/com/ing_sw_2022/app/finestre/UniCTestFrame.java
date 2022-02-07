package com.ing_sw_2022.app.finestre;

import com.ing_sw_2022.app.Studente;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UniCTestFrame extends JFrame{
    private JPanel mainPanel;
    private JLabel mainLabel;
    private JButton btnNuovoQuesito;
    private JPanel panel;
    private JButton btnNuovoTemplate;
    private JButton btnAvviaSimulazione;
    private static UniCTestFrame unictestFrame;
    private static final Integer pos = 100;
    private UniCTestFrame(){
        setContentPane(mainPanel);
        setTitle("UniCTest");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(pos,pos);
        btnNuovoQuesito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NuovoQuesitoFrame quesitoFrame = NuovoQuesitoFrame.getInstance();
                btnNuovoQuesito.setEnabled(false);

                quesitoFrame.addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        btnNuovoQuesito.setEnabled(true);
                        NuovoQuesitoFrame.destroyInstance();
                    }
                    @Override
                    public void windowClosed(WindowEvent e)
                    {
                        btnNuovoQuesito.setEnabled(true);
                        NuovoQuesitoFrame.destroyInstance();
                    }
                });
            }
        });

        btnNuovoTemplate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NuovoTemplatePersFrame nuovoTemplatePersFrame = NuovoTemplatePersFrame.getInstance();
                btnNuovoTemplate.setEnabled(false);

                nuovoTemplatePersFrame.addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        btnNuovoTemplate.setEnabled(true);
                        NuovoTemplatePersFrame.destroyInstance();
                    }
                    @Override
                    public void windowClosed(WindowEvent e)
                    {
                        btnNuovoTemplate.setEnabled(true);
                        NuovoTemplatePersFrame.destroyInstance();
                    }
                });
            }
        });

        btnAvviaSimulazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AvviaSimulazioneFrame avviaSimulazioneFrame = AvviaSimulazioneFrame.getInstance();
                btnAvviaSimulazione.setEnabled(false);

                avviaSimulazioneFrame.addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        btnAvviaSimulazione.setEnabled(true);
                        AvviaSimulazioneFrame.destroyInstance();
                    }
                    @Override
                    public void windowClosed(WindowEvent e)
                    {
                        btnAvviaSimulazione.setEnabled(true);
                        AvviaSimulazioneFrame.destroyInstance();
                    }
                });
            }
        });
    }

    public static UniCTestFrame getInstance() {
        if (unictestFrame == null)  unictestFrame = new UniCTestFrame();
        return unictestFrame;
    }

    public static void main(String[] args )
    {
        UniCTest unictest = UniCTest.getInstance(); //esegue il caso d'uso di avviamento
        System.out.println(unictest.getMappaMaterie().get("MAT01").getMappaQuesiti());
        System.out.println(unictest.getMappaMaterie().get("ITA02").getMappaQuesiti());
        System.out.println(((Studente)unictest.getMappaUtenti().get("VRDLGI99R21C351J")).getMappaTemplatePersonalizzati());
        System.out.println("/////////////////////////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////////////");
        ///////////////// APERTURA GUI ///////////////
        UniCTestFrame.getInstance();

    }

    public static int getPos(){
        return pos;
    }
}
