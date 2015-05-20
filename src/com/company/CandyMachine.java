package com.company;

import java.util.*;

public class CandyMachine {

    ArrayList<Candy> candyList = new ArrayList<Candy>();
    int[] coinTypeList = new int[8];
    Scanner inputScanner = new Scanner(System.in);

    String status = "WELKOM";
    int balance = 673;

    public CandyMachine() {

        fillCoinTypeList();
        fillCandyList();

        actieLoop();
    }

    void actieLoop() {

        while (!status.equals("KLAAR") ) {

            if (status.equals("WELKOM")) {
                printInventory();
                status = "INPUT";
            }

            if (status.equals("INPUT")) {
                getInput();
            }

        }
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

    }
    double balanceInEuro() {

        return (double) balance / 100;
    }

    void printBalance() {

        System.out.println("Balans: € " + balanceInEuro());
    }

    void printInventory() {

        for (int i = 0; i < candyList.size(); i++ ) {
            System.out.println( candyList.get(i).description());
        }

    }

    void getInput() {
        printBalance();
        String input = inputScanner.next();
        handleInput(input);
    }

    void handleInput(String input) {

        if (input.equals("INFO")) {
            printInventory();
        }

        else if (input.equals("KLAAR")) {
            giveChange();

            status = "KLAAR";

            System.out.println("Eet smakelijk!");
        }

        else if (input.length() >= 4 && input.substring(0, 4).equals("KOOP")) {


            if (input.length() > 4) {

                int code;

                try {
                    code = Integer.parseInt(input.substring(4));
                } catch (NumberFormatException e) {
                    code = -987;
                }

                if (code == -987) {
                   System.out.println("Syntaxerror, er moet acht het woord KOOP DIRECT een cijfer staan, bijvoorbeeld KOOP3 om nummer 3 te kopen");
                } else {
                    buyCandy(code);
                }

            }
        }

        else if (input.length() >= 3 && input.substring(0, 3).equals("ADD") ) {

            if (input.length() > 3) {

                int toevoeging;

                try {
                    toevoeging = Integer.parseInt(input.substring(3));
                } catch (NumberFormatException e) {
                    toevoeging = -987;
                }

                if (toevoeging == -987) {
                    System.out.println("Syntaxerror, er moet achter het woord ADD DIRECT een cijfer staan, bijvoorbeeld ADD120 om 1,20 toe te voegen");
                    System.out.println("Het getal mag ook niet groter zijn dan de maximum waarde van een Int");
                }

                else if (toevoeging > 0) {
                    addMoney(toevoeging);
                }

                else if (toevoeging == 0) {
                    System.out.println("Graag meer dan €0 toevoegen.");

                }

                else if (toevoeging < 0) {
                    System.out.println("U kan niet een negatieve hoeveelheid geld toevoegen");
                    System.out.println("Als u uw geld terug wilt, gebruik dan KLAAR");
                }

            } else {
                System.out.println("Zet het nummer DIRECT achter ADD, bijvoorbeeld ADD50 om 50 cent toe te voegen");
            }
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