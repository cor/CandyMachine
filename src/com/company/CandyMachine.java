package com.company;

import java.util.*;

public class CandyMachine {

    public ArrayList<Candy> candyList = new ArrayList<Candy>();
    public int[] coinTypeList = {200, 100, 50, 20, 10, 5, 2, 1};

    int balance = 673;

    public CandyMachine() {

        fillCandyList();

    }

    /**
     * A method that checks if there is enough Candy is left of a Candy
     * @param candy the Candy
     * @return a value that is true when thre is enough candy left, false when there isn't
     */
    boolean enoughCandyLeft(Candy candy) {
        return candy.amountLeft > 0;
    }

    /**
     * A method that checks if the user has enough money for a Candy
     * @param candy the Candy
     * @return a value that is true when the user has enough money, false when the user doesn't
     */
    boolean hasEnoughBalanceToBuyCandy(Candy candy) {
        return balance - candy.price >=0;
    }


    /**
     * Fill the Candy list with Candys
     */
    void fillCandyList() {

        candyList.add(new Candy("Mars", 1, 90, 5));
        candyList.add(new Candy("Twix", 2, 105, 15));
        candyList.add(new Candy("KitKat", 3, 80, 30));
        candyList.add(new Candy("Snickers", 4, 70, 20));
        candyList.add(new Candy("Autodrop", 5, 90, 30));

    }

    /**
     * A method that convert the price from eurocents (int) to euro (Double)
     * @return a double that represents the price in euro
     */
    double balanceInEuro() {

        return (double) balance / 100;
    }

    String formatedBalanceInEuro() {
        return String.format("€%.2f", balanceInEuro());
    }

    /**
     * A method that adds money to the balance
     * @param amount the amount of money
     */
    void addMoney(int amount) {

        balance += amount;
    }

    /**
     * A method that searches for a Candy using the Candy's code
     * @param code the Candy's code
     * @return the found Candy, or null if the method didn't find the Candy.
     */
    Candy findCandy(int code) {

        for (Candy candy : candyList) {

            if (candy.code == code) {
                return candy;
            }
        }

        return null;
    }

    /**
     * Buy a Candy
     * @param code the Candy's code
     * @return the bought Candy
     */
    Candy buyCandy(int code) {

        Candy candy = findCandy(code);

        if (candy != null) {

            balance -= candy.price;
            candy.amountLeft--;
            return candy;
        }

        else {
            return null;
        }

    }

    /**
     * Clear the balance and return a string that shows all coins that the user gets
     * @return the String that shows all coins that the user gets
     */
    String getChangeString() {

        String changeString = "";

        if (balance > 0) {

            changeString += "\nHere's your money:\n";

            while (balance > 0) {

                int grootstMogelijkeMunt = 0;

                for (int i = 0; i < coinTypeList.length; i++) {
                    if (balance >= coinTypeList[i]) {
                        grootstMogelijkeMunt = coinTypeList[i];
                        break;
                    }
                }

                balance -= grootstMogelijkeMunt;
                changeString += String.format("€%.2f\n", (double)grootstMogelijkeMunt / 100 );
            }

        } else {
            changeString = "You do not get any money back";
        }

        return changeString;
    }
}