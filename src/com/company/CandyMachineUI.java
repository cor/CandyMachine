package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class CandyMachineUI extends JFrame implements ActionListener {

    private JLabel titleLabel;
    private JLabel balanceLabel;
    private JTextField balanceAddField;
    private JButton balanceAddButton;
    private JTextArea logTextArea;
    private JScrollPane logScrollPane;

    // colors
    public Color backgroundColor = new Color(242, 213, 187);
    public Color logBackgroundColor = new Color(217, 188, 163);
    public Color titleBackgroundColor = new Color(89, 75, 71);
    public Color balanceBackgroundColor = new Color(217, 188, 163);

    public Color borderColor = new Color(89, 75, 71);

    public Color textColor = new Color(89, 75, 71);
    public Color titleForegroundColor = new Color(21, 26, 38);
    public Color balanceForegroundColor = textColor;


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


    /**
     * Set up the window
     */
    private void setupWindow() {

        getContentPane().setBackground(backgroundColor);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500); setResizable(false);
        setTitle("CandyMachine");
    }


    /**
     * Add all the UI elements to the window
     */
    private void addUIElements() {

        addButtons();
        addTitleLabel();
        addBalanceLabel();
        addBalanceAddField();
        addBalanceAddButton();
        addLogTextArea();
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

            } catch (IndexOutOfBoundsException e) { /* do nothing */ }

            JButton button = new JButton();

            // If there isn't a candy available, show EMPTY on that slot
            if (candyForButton != null) {
                button.setText(getTextForButton(candyForButton));
            } else {
                button.setText("EMPTY");
            }

            Point buttonPosition = getPositionForButton(i);
            button.setBounds(buttonPosition.x, buttonPosition.y, buttonSize.width, buttonSize.height);

            // This is a little bit hacky but we use the name property of the button to store the associated candy's code
            button.setName( candyForButton != null ? "" + candyForButton.code : "" );

            // button styling
//            button.setBorderPainted(false);
            button.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 2, borderColor));
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);

            button.setForeground(textColor);

            button.addActionListener(this);
            button.setMargin(new Insets(0, 0, 0, 0));
            add(button);
            buttonList.add(button);
        }

    }

    /**
     * Add the titleLabel
     */
    private void addTitleLabel() {
        titleLabel = new JLabel();

        //set colors
        titleLabel.setForeground(titleForegroundColor);
        titleLabel.setBackground(titleBackgroundColor);

        titleLabel.setText("CandyMachine");
        titleLabel.setFont(titleLabel.getFont().deriveFont(16.0f));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(3 * buttonSize.width, 0, getSize().width - 3 * buttonSize.width, 100);
        titleLabel.setOpaque(true);
        add(titleLabel);
    }

    /**
     * Add balanceLabel
     */
    private void addBalanceLabel() {
        balanceLabel = new JLabel();

        //colors
        balanceLabel.setForeground(balanceForegroundColor);
        balanceLabel.setBackground(balanceBackgroundColor);

        balanceLabel.setText(getTextForBalanceLabel());
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel.setOpaque(true);
        balanceLabel.setBounds(
                3 * buttonSize.width,
                titleLabel.getHeight(),
                getSize().width - 3 * buttonSize.width,
                50);
        add(balanceLabel);
    }

    /**
     * Add the balanceAddField
     */
    private void addBalanceAddField() {
        balanceAddField = new  JTextField();
        balanceAddField.setBounds(
                3 * buttonSize.width,
                titleLabel.getHeight() + balanceLabel.getHeight(),
                getSize().width - 3 * buttonSize.width,
                40);
        add(balanceAddField);

    }

    /**
     * Adds the button that adds the balance from the balance add field to the balance
     */
    private void addBalanceAddButton() {
        balanceAddButton = new JButton();
        balanceAddButton.setText("Add to balance");
        balanceAddButton.setBounds(
                3 * buttonSize.width,
                titleLabel.getHeight() + balanceLabel.getHeight() + balanceAddField.getHeight(),
                getSize().width - 3 * buttonSize.width,
                40
        );

        balanceAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int amountToAdd;
                try {
                    amountToAdd = Integer.parseInt(balanceAddField.getText());
                } catch (NumberFormatException exception) {
                    amountToAdd = 0;
                }

                candyMachine.addMoney(amountToAdd);
                balanceAddField.setText("");

                updateUI();
            }
        });
        add(balanceAddButton);
    }

    /**
     * Adds the log area to the ui that shows all events
     * (for example, receiving a candy)
     */
    private void addLogTextArea() {
        logTextArea = new JTextArea();

        //colors
        logTextArea.setForeground(textColor);
        logTextArea.setBackground(logBackgroundColor);

        logTextArea.setEditable(false);

        logScrollPane = new JScrollPane(logTextArea);
        logScrollPane.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, borderColor));
        logScrollPane.setBounds(
                0,
                3 * buttonSize.height,
                getWidth(),
                getHeight() - 3 * buttonSize.height - 20
        );


        add(logScrollPane);

    }



    /**
     * update the UI to reflect changes made to the model
     */
    public void updateUI() {
        updateButtons();
        updateBalanceLabel();
    }

    /**
     * Update the button's text, which contains info about the candy like it's stock
     */
    private void updateButtons() {

        for (int i = 0; i < 9; i++) {

            JButton button;

            try  {
                button = buttonList.get(i);


            } catch (IndexOutOfBoundsException e) {
                button = null;
            }

            if (button != null) {


                Candy candy;
                try {
                    candy = candyMachine.candyList.get(i);
                } catch (IndexOutOfBoundsException e) {
                    candy = null;
                }

                if (candy != null) {
                    button.setText(getTextForButton(candy));
                }
            }


        }
    }

    /**
     * Update the balance label
     */
    private void updateBalanceLabel() {
        balanceLabel.setText(getTextForBalanceLabel());
    }

    /**
     * Handle user input (typically buttons being pressed)
     * @param e the event
     */
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        JButton button = (JButton) object;

        Candy candy;

        try {
            candy = candyMachine.buyCandy(Integer.parseInt(button.getName()));
        } catch(NumberFormatException event) {
            // TODO
            candy = null;

        }


        if (candy != null) {
            logTextArea.append("Here's your " + candy.name + "\n");
        }
        updateUI();
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
                    "price: &euro;" + String.format("%10.2f", candy.priceInEuro()) + "<br>" +
                    "stock: " + candy.amountLeft +
                "</html>";
    }

    /**
     * Generate the text for the balance label
     *
     * @return a string containing the text for the balance label
     */
    private String getTextForBalanceLabel() {
        return "<html>" +
                    "Balance: <b>&euro;" + String.format("%10.2f", candyMachine.balanceInEuro()) + "</b>" +
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