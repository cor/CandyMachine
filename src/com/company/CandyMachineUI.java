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

public class CandyMachineUI extends JFrame implements ActionListener {

    private JLabel titleLabel;
    private JLabel balanceLabel;
    private JTextField balanceAddField;


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

        addButtons();
        addTitleLabel();
        addBalanceLabel();
        addBalanceAddField();
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
                // do nothing
            }

            JButton button = new JButton();

            // If there isn't a candy available, show EMPTY on that slot
            if (candyForButton != null) {
                button.setText(getTextForButton(candyForButton));
            } else {
                button.setText("EMPTY");
            }

            Point buttonPosition = getPositionForButton(i);
            button.setBounds(buttonPosition.x, buttonPosition.y, buttonSize.width, buttonSize.height);
            button.addActionListener(this);
            add(button);
        }

    }

    /**
     * Handle user input (typically buttons being pressed)
     * @param e the event
     */
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        JButton button = (JButton) object;
        balanceAddField.setText(button.getText());
    }


    /**
     * Add the balanceAddField
     */
    private void addBalanceAddField() {
        balanceAddField = new  JTextField();
        balanceAddField.setBounds(3 * buttonSize.width, titleLabel.getHeight() + balanceLabel.getHeight(), getSize().width - 3 * buttonSize.width, 40);
        add(balanceAddField);

    }


    /**
     * Add the titleLabel
     */
    private void addTitleLabel() {
        titleLabel = new JLabel();
        titleLabel.setText("CandyMachine");
        titleLabel.setFont(titleLabel.getFont().deriveFont(16.0f));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(3 * buttonSize.width, 0, getSize().width - 3 * buttonSize.width, 100);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.green);
        add(titleLabel);
    }

    /**
     * Add balanceLabel
     */
    private void addBalanceLabel() {
        balanceLabel = new JLabel();
        balanceLabel.setText("<html>Balance: <b>&euro;" + candyMachine.balanceInEuro() + "</b> </html>");
        balanceLabel.setBounds(3 * buttonSize.width, titleLabel.getHeight(), getSize().width - 3 * buttonSize.width, 50);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel.setOpaque(true);
        balanceLabel.setBackground(Color.red);
        add(balanceLabel);
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