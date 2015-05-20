package com.company;

/**
 *
 * Created by cor on 20-05-15.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CandyMachineUI extends JFrame {

    private JLabel label;
    private JButton button;

    public CandyMachineUI() {
        setupWindow();
        addUIElements();
        setLayout(null);

        setVisible(true);

    }


    private void setupWindow() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setTitle("CandyMachine");
    }

    private void addUIElements() {

        button = new JButton("Snickers");
        button.setBounds(0, 0, 200, 200);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hier is jouw Snickers");
            }
        });
        add(button);


        label = new JLabel("sup bro");
        add(label);
    }

}
