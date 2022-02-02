package com.ing_sw_2022.app.ui;

import com.ing_sw_2022.app.UniCTest;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NuovoTemplateUffPanel extends JPanel implements ActionListener {
    private JPanel mainPanel;
    private JLabel mainLabel;
    private JScrollPane scroller;
    private JPanel panel;
    private JTextField textField;
    private JButton button;

    public NuovoTemplateUffPanel(){
        button.addActionListener(this);
        button.setEnabled(false);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                button.setEnabled(!textField.getText().trim().isEmpty());
            }
            public void removeUpdate(DocumentEvent e) {
                button.setEnabled(!textField.getText().trim().isEmpty());
            }
            public void insertUpdate(DocumentEvent e) {
                button.setEnabled(!textField.getText().trim().isEmpty());
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UniCTest.getInstance().nuovoTemplateU(textField.getText());
        NuovoTemplatePersFrame.getInstance().setContentPane(new InsInfoTemplateUffPanel().getMainPanel());
        NuovoTemplatePersFrame.getInstance().revalidate();
    }
}
