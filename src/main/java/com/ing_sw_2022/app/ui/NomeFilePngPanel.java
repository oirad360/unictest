package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.NotAllowedException;
import com.ing_sw_2022.app.eccezioni.StudentNotAllowedException;
import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class NomeFilePngPanel implements ActionListener {
    private JPanel mainPanel;
    private JTextField textField;
    private JButton confermaButton;

    public NomeFilePngPanel(){
        confermaButton.addActionListener(this);
        confermaButton.setEnabled(false);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!textField.getText().trim().isEmpty());
            }
            public void removeUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!textField.getText().trim().isEmpty());
            }
            public void insertUpdate(DocumentEvent e) {
                confermaButton.setEnabled(!textField.getText().trim().isEmpty());
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Map<String,String> mappaInfo= null;
        try {
            mappaInfo = UniCTest.getInstance().recuperaInfoTestCartaceo(textField.getText());
        } catch (NotAllowedException ex) {
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
        }
        CorreggiTestCartaceoFrame.getInstance().setContentPane(new ConfermaInfoTestPanel(mappaInfo.get("cfStudente"),mappaInfo.get("cfTutor"),mappaInfo.get("idTest")).getMainPanel());
        CorreggiTestCartaceoFrame.getInstance().revalidate();
    }
}
