package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.*;
import com.ing_sw_2022.app.eccezioni.EmployeeNotAllowedException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;
import com.ing_sw_2022.app.eccezioni.UserNotFoundException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UniCTestFrame extends JFrame{
    private static UniCTestFrame unictestFrame;
    private static final Integer pos = 100;
    private JPanel loginPanel;
    private JTextField cf;
    private JButton accediButton;

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    private UniCTestFrame(){
        setContentPane(loginPanel);
        setTitle("UniCTest");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(pos,pos);

        accediButton.setEnabled(false);
        cf.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                accediButton.setEnabled(!(cf.getText().trim().isEmpty()));
            }
            public void removeUpdate(DocumentEvent e) {
                accediButton.setEnabled(!(cf.getText().trim().isEmpty()));
            }
            public void insertUpdate(DocumentEvent e) {
                accediButton.setEnabled(!(cf.getText().trim().isEmpty()));
            }
        });

        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UniCTest.getInstance().setUtenteAutenticato(cf.getText());
                    unictestFrame.setContentPane(new MainPanel().getMainPanel());
                    unictestFrame.revalidate();
                } catch (UserNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(new JFrame(),
                            ex.getMessage(),
                            "Inane warning",
                            JOptionPane.WARNING_MESSAGE);
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
        UniCTest.getInstance(); //esegue il caso d'uso di avviamento
        /*for(Materia m: unictest.getMappaMaterie().values()) {
            System.out.println("----------------QUESITI "+m.getNome()+"----------------");
            try {
                System.out.println(m.getMappaQuesiti());
            } catch (StudentNotAllowedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("------------------TEMPLATE UFFICIALI-----------------");
        System.out.println(unictest.getMappaTemplateUfficiali());*/
        /*
        RSSMRA80A01C351O --> Tutor
        CTNLCU80A01C351K --> TutorSimulazione
        MSSNDR80A01C351P --> Amministratore
        PPPMRA80A01C351X --> Amministratore, TutorSimulazione
        VRDLGI99R21C351J --> Studente
        */
        /*try {
            unictest.setUtenteAutenticato("CTNLCU80A01C351K");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
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
        System.out.println("/////////////////////////////////////////////////////////");*/
        ///////////////// APERTURA GUI ///////////////
        UniCTestFrame.getInstance();
    }

}
