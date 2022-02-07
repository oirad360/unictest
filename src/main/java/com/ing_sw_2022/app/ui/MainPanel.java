package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;
import com.ing_sw_2022.app.eccezioni.EmployeeNotAllowedException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainPanel {
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
    private JButton btnTestSvolti;
    private JButton btnNuovoUtente;
    private JButton btnMateria;
    private JButton btnResponsabilità;
    private JButton logoutButton;
    private boolean studente;

    public MainPanel(){
        if(UniCTest.getInstance().getUtenteAutenticato() instanceof Studente){
            studente=true;
        } else studente=false;

        btnNuovoQuesito.setVisible(!studente);
        btnAvviaSimulazione.setVisible(studente);
        btnNuovoTemplateU.setVisible(!studente);
        btnTestCartaceo.setVisible(!studente);
        btnCorreggiSimulazione.setVisible(!studente);
        btnQuesiti.setVisible(!studente);
        btnTestSvolti.setVisible(studente);
        btnNuovoUtente.setVisible(!studente);
        btnMateria.setVisible(!studente);
        btnResponsabilità.setVisible(!studente);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UniCTest.getInstance().logout();
                UniCTestFrame.getInstance().setContentPane(UniCTestFrame.getInstance().getLoginPanel());
            }
        });

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

        btnTestSvolti.addActionListener(new ActionListener() {
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
                    btnTestSvolti.setEnabled(false);
                    visualizzaTestFrame.addWindowListener(new WindowAdapter()
                    {
                        @Override
                        public void windowClosing(WindowEvent e)
                        {
                            btnTestSvolti.setEnabled(true);
                            VisualizzaTestFrame.destroyInstance();
                        }
                        @Override
                        public void windowClosed(WindowEvent e)
                        {
                            btnTestSvolti.setEnabled(true);
                            VisualizzaTestFrame.destroyInstance();
                        }
                    });
                }

            }
        });
        btnNuovoUtente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NuovoUtenteFrame nuovoUtenteFrame = NuovoUtenteFrame.getInstance();
                btnNuovoUtente.setEnabled(false);
                nuovoUtenteFrame.addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        btnNuovoUtente.setEnabled(true);
                        NuovoUtenteFrame.destroyInstance();
                    }
                    @Override
                    public void windowClosed(WindowEvent e)
                    {
                        btnNuovoUtente.setEnabled(true);
                        NuovoUtenteFrame.destroyInstance();
                    }
                });
            }

        });
        btnMateria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AggiungiMateriaInsegnataFrame aggiungiMateriaInsegnataFrame = AggiungiMateriaInsegnataFrame.getInstance();
                btnMateria.setEnabled(false);
                aggiungiMateriaInsegnataFrame.addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        btnMateria.setEnabled(true);
                        AggiungiMateriaInsegnataFrame.destroyInstance();
                    }
                    @Override
                    public void windowClosed(WindowEvent e)
                    {
                        btnMateria.setEnabled(true);
                        AggiungiMateriaInsegnataFrame.destroyInstance();
                    }
                });
            }
        });

        btnResponsabilità.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AggiungiResponsabilitàFrame aggiungiResponsabilitàFrame = AggiungiResponsabilitàFrame.getInstance();
                btnResponsabilità.setEnabled(false);
                aggiungiResponsabilitàFrame.addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        btnResponsabilità.setEnabled(true);
                        AggiungiResponsabilitàFrame.destroyInstance();
                    }
                    @Override
                    public void windowClosed(WindowEvent e)
                    {
                        btnResponsabilità.setEnabled(true);
                        AggiungiResponsabilitàFrame.destroyInstance();
                    }
                });
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
