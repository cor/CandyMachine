package com.company;

/**
 *
 * Created by cor on 20-05-15.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class CandyMachineUI extends JFrame {

    private JLabel label;
    private JButton button;
    private ArrayList<JButton> buttonList = new ArrayList<JButton>();
    public CandyMachine candyMachine;

    Dimension buttonSize = new Dimension(120, 120);

    public CandyMachineUI(CandyMachine candyMachine) {

        this.candyMachine = candyMachine;

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

//        button = new JButton("Snickers");
//        button.setBounds(0, 0, 200, 200);
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Hier is jouw Snickers");
//            }
//        });
//        add(button);


//        label = new JLabel("sup bro");
//        add(label);


        addButtons();
    }


    /**
     * Add all nine Candy buttons
     */
    private void addButtons() {

        // Create nine buttons
        for (int i = 0; i < 9; i++ ) {

            Candy candyForButton = null;

            try {
                candyForButton = candyMachine.candyList.get(i);

            } catch (IndexOutOfBoundsException e) {
                // candy remains null
            }

            if (candyForButton != null) {

                button = new JButton();
                button.setText(getTextForButton(candyForButton));

                Point buttonPosition = getPositionForButton(i);

                button.setBounds(buttonPosition.x, buttonPosition.y, buttonSize.width, buttonSize.height);
                add(button);



            } else {

                button = new JButton("EMPTY");

                Point buttonPosition = getPositionForButton(i);

                button.setBounds(buttonPosition.x, buttonPosition.y, buttonSize.width, buttonSize.height);
                add(button);
            }


        }

    }

    /**
     * Generate the text for each candy button
     *
     * @param candy the Candy for which the text will be generated
     * @return a string containing the text for the button
     */
    private String getTextForButton(Candy candy) {
        return "<html>" +
                    "<b>" + candy.name + "</b> <br>" +
                    "price: &euro;" + candy.priceInEuro() + "<br>" +
                    "stock: " + candy.amountLeft +
                "</html>";
    }

    /**
     * A method that calculates the position for each button based on the index (0-8)
     * this method is used in the addButtons() method
     *
     * @param i the index of the button
     * @return the position of the button
     */
    private Point getPositionForButton(int i) {

         //determine button position;
        int xPosition = 0;
        int yPosition = 0;

        if (i <= 2) {

            // first row, spots 0-2
            xPosition = i * buttonSize.width;

        } else if (i >= 3 && i <= 5) {

            // second row, spots 3-5
            yPosition = buttonSize.height;
            xPosition = (i - 3) * buttonSize.width;

        } else if (i >= 6 && i <= 8 ) {

            // third row, spots 6-8
            yPosition = 2 * buttonSize.height;
            xPosition = (i - 6) * buttonSize.width;
        }

        return new Point(xPosition, yPosition);

    }



}
