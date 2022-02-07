package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;
import com.ing_sw_2022.app.eccezioni.EmployeeNotAllowedException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;

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
    private JButton btnNuovoTemplateU;
    private JButton btnTestCartaceo;
    private JButton btnCorreggiSimulazione;
    private JButton btnQuesiti;
    private JButton btnTest;
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
                NuovoQuesitoFrame quesitoFrame = null;
                try {
                    quesitoFrame = NuovoQuesitoFrame.getInstance();
                } catch (StudentNotAllowedException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(new JFrame(),
                            ex.getMessage(),
                            "Inane warning",
                            JOptionPane.WARNING_MESSAGE);
                }
                if(quesitoFrame!=null){
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
                AvviaSimulazioneFrame avviaSimulazioneFrame = null;
                try {
                    avviaSimulazioneFrame = AvviaSimulazioneFrame.getInstance();
                } catch (EmployeeNotAllowedException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(new JFrame(),
                            ex.getMessage(),
                            "Inane warning",
                            JOptionPane.WARNING_MESSAGE);
                }
                if(avviaSimulazioneFrame!=null){
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
            }
        });

        btnNuovoTemplateU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NuovoTemplateUffFrame nuovoTemplateUffFrame = NuovoTemplateUffFrame.getInstance();
                btnNuovoTemplateU.setEnabled(false);

                nuovoTemplateUffFrame.addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        btnNuovoTemplateU.setEnabled(true);
                        NuovoTemplateUffFrame.destroyInstance();
                    }
                    @Override
                    public void windowClosed(WindowEvent e)
                    {
                        btnNuovoTemplateU.setEnabled(true);
                        NuovoTemplateUffFrame.destroyInstance();
                    }
                });
            }
        });

        btnTestCartaceo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestCartaceoFrame testCartaceoFrame = null;
                try {
                    testCartaceoFrame = TestCartaceoFrame.getInstance();
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
                if(testCartaceoFrame!=null){
                    btnTestCartaceo.setEnabled(false);
                    testCartaceoFrame.addWindowListener(new WindowAdapter()
                    {
                        @Override
                        public void windowClosing(WindowEvent e)
                        {
                            btnTestCartaceo.setEnabled(true);
                            TestCartaceoFrame.destroyInstance();
                        }
                        @Override
                        public void windowClosed(WindowEvent e)
                        {
                            btnTestCartaceo.setEnabled(true);
                            TestCartaceoFrame.destroyInstance();
                        }
                    });
                }

            }
        });

        btnCorreggiSimulazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CorreggiTestCartaceoFrame correggiTestCartaceoFrame = CorreggiTestCartaceoFrame.getInstance();
                btnCorreggiSimulazione.setEnabled(false);
                correggiTestCartaceoFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        btnCorreggiSimulazione.setEnabled(true);
                        CorreggiTestCartaceoFrame.destroyInstance();
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        btnCorreggiSimulazione.setEnabled(true);
                        CorreggiTestCartaceoFrame.destroyInstance();
                    }
                });
            }
        });

        btnQuesiti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VisualizzaQuesitiCompletiFrame visualizzaQuesitiCompletiFrame = null;
                try {
                    visualizzaQuesitiCompletiFrame = VisualizzaQuesitiCompletiFrame.getInstance();
                } catch (StudentNotAllowedException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(new JFrame(),
                            ex.getMessage(),
                            "Inane warning",
                            JOptionPane.WARNING_MESSAGE);
                }
                if(visualizzaQuesitiCompletiFrame!=null){
                    btnQuesiti.setEnabled(false);

                    visualizzaQuesitiCompletiFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            btnQuesiti.setEnabled(true);
                            VisualizzaQuesitiCompletiFrame.destroyInstance();
                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            btnQuesiti.setEnabled(true);
                            VisualizzaQuesitiCompletiFrame.destroyInstance();
                        }
                    });
                }

            }
        });

        btnTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaTestFrame visualizzaTestFrame = null;
                try {
                    visualizzaTestFrame = VisualizzaTestFrame.getInstance();
                } catch (EmployeeNotAllowedException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(new JFrame(),
                            ex.getMessage(),
                            "Inane warning",
                            JOptionPane.WARNING_MESSAGE);
                }
                if(visualizzaTestFrame!=null){
                    btnTest.setEnabled(false);
                    visualizzaTestFrame.addWindowListener(new WindowAdapter()
                    {
                        @Override
                        public void windowClosing(WindowEvent e)
                        {
                            btnTest.setEnabled(true);
                            VisualizzaTestFrame.destroyInstance();
                        }
                        @Override
                        public void windowClosed(WindowEvent e)
                        {
                            btnTest.setEnabled(true);
                            VisualizzaTestFrame.destroyInstance();
                        }
                    });
                }

            }
        });
    }

    public static UniCTestFrame getInstance() {
        if (unictestFrame == null)  unictestFrame = new UniCTestFrame();
        return unictestFrame;
    }

    public static int getPos(){
        return pos;
    }

    public static void main(String[] args )
    {
        UniCTest unictest = UniCTest.getInstance(); //esegue il caso d'uso di avviamento
        for(Materia m: unictest.getMappaMaterie().values()) {
            System.out.println("----------------QUESITI "+m.getNome()+"----------------");
            try {
                System.out.println(m.getMappaQuesiti());
            } catch (StudentNotAllowedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("------------------TEMPLATE UFFICIALI-----------------");
        System.out.println(unictest.getMappaTemplateUfficiali());
        /*
        RSSMRA80A01C351O --> Tutor
        CTNLCU80A01C351K --> TutorSimulazione
        MSSNDR80A01C351P --> Amministratore
        PPPMRA80A01C351X --> Amministratore, TutorSimulazione
        VRDLGI99R21C351J --> Studente
        */
        unictest.setUtenteAutenticato("PPPMRA80A01C351X");
        System.out.println("-------------------UTENTE AUTENTICATO----------------");
        System.out.println(unictest.getUtenteAutenticato());
        System.out.println("--------------TEMPLATE PERSONALIZZATI DELL'UTENTE------------");
        if(unictest.getUtenteAutenticato() instanceof Impiegato)
        try {
            System.out.println(((Impiegato)unictest.getUtenteAutenticato()).getMappaTemplatePersonalizzati());
        } catch (Exception e) {
            System.out.println("l'utente non è un tutor di simulazione");
        }
        else if(unictest.getUtenteAutenticato() instanceof Studente){
            System.out.println(((Studente)unictest.getUtenteAutenticato()).getMappaTemplatePersonalizzati());
        }
        System.out.println("--------------TEST SVOLTI DALL'UTENTE------------");
        if(unictest.getUtenteAutenticato() instanceof Impiegato)
                System.out.println("l'utente non è uno studente");
        else if(unictest.getUtenteAutenticato() instanceof Studente){
            for(Template te:((Studente)unictest.getUtenteAutenticato()).getMappaTemplateTestSvolti().values()){
                System.out.println(te.getMappaTest());
            }
        }
        System.out.println("/////////////////////////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////////////");
        ///////////////// APERTURA GUI ///////////////
        UniCTestFrame.getInstance();
    }
}
