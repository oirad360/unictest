package com.ing_sw_2022.app;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
//from  w ww.  j  ava2s  . co  m
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame {
    JLabel label;
    JComboBox combo;
    public static void main(String args[]) {
        new Main();
    }

    public Main() {
        label = new JLabel("Select a Customer");
        add(label, BorderLayout.NORTH);

        Customer customers[] = new Customer[6];
        customers[0] = new Customer("A", 1);
        customers[1] = new Customer("B", 6);
        customers[2] = new Customer("C", 2);
        customers[3] = new Customer("D", 3);
        customers[4] = new Customer("E", 4);
        customers[5] = new Customer("F", 5);

        combo = new JComboBox(customers);
        combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Customer c = (Customer) e.getItem();
                label.setText("You selected customer id: " + c.getId());
            }
        });
        JPanel panel = new JPanel();
        panel.add(combo);
        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setVisible(true);
    }
}

class Customer {
    private String name;
    private int id;

    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String toString() {
        return getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}