package com.company;

import java.util.*;

public class CandyMachine {

    public ArrayList<Candy> candyList = new ArrayList<Candy>();
    public int[] coinTypeList = new int[8];

    int balance = 673;

    public CandyMachine() {

        fillCoinTypeList();
        fillCandyList();

    }

    void fillCoinTypeList() {

        coinTypeList[0] = 200;
        coinTypeList[1] = 100;
        coinTypeList[2] = 50;
        coinTypeList[3] = 20;
        coinTypeList[4] = 10;
        coinTypeList[5] = 5;
        coinTypeList[6] = 2;
        coinTypeList[7] = 1;
    }

    void fillCandyList() {

        candyList.add(new Candy("Mars", 1, 90, 5));
        candyList.add(new Candy("Twix", 2, 105, 15));
        candyList.add(new Candy("KitKat", 3, 80, 30));
        candyList.add(new Candy("Snickers", 4, 70, 20));
        candyList.add(new Candy("Autodrop", 5, 90, 30));

    }
    double balanceInEuro() {

        return (double) balance / 100;
    }

    void printInventory() {

        for (int i = 0; i < candyList.size(); i++ ) {
            System.out.println( candyList.get(i).description());
        }

    }

    void addMoney(int hoeveelheid) {

        balance += hoeveelheid;
    }

    Candy findCandy(int code) {

        for (Candy candy : candyList) {

            if (candy.code == code) {
                return candy;
            }
        }

        return null;
    }

    void buyCandy(int code) {

        Candy candy = findCandy(code);

        if (candy != null) {

            balance -= candy.price;
            candy.amountLeft--;
            giveCandy(candy);
        }

        else {
            System.out.println("Deze code klopt niet, kies AUB een ander soort snoep");
        }

    }

    void giveCandy(Candy candy) {
        System.out.println("Hier is uw " + candy.name);
    }

    void giveChange() {

        if (balance > 0) {

            System.out.println("U krijgt nog geld terug:");

            while (balance > 0) {

                int grootstMogelijkeMunt = 0;

                for (int i = 0; i < coinTypeList.length; i++) {
                    if (balance >= coinTypeList[i]) {
                        grootstMogelijkeMunt = coinTypeList[i];
                        break;
                    }
                }

                balance -= grootstMogelijkeMunt;
                System.out.println("€ "+ (double)grootstMogelijkeMunt / 100);
            }

        }


    }
}