package com.company;

import java.util.*;

public class CandyMachine {

    ArrayList<Candy> candyList = new ArrayList<Candy>();   // A list containing all possible candy types
    int[] coinTypeList = new int[8];                       // lijt van alle soorten munten die bestaan
    Scanner inputScanner = new Scanner(System.in);         // De Scanner die wordt gebruikt voor de input

    String status = "WELKOM";                              // Met deze string wordt bijgehouden wat het programma momenteel doet
    int balance = 673;                                      // het aantal geld in de machine, in centen

    public CandyMachine() {

        fillCoinTypeList();
        fillCandyList();

        actieLoop();
    }

    void actieLoop() {

        while (!status.equals("KLAAR") ) {

            if (status.equals("WELKOM")) {
                printWelcomeMessage();
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

        // Voeg een aantal soorten snoep toe aan de snoep lijst
        candyList.add(new Candy("Mars", 1, 90, 5));
        candyList.add(new Candy("Twix", 2, 105, 15));
        candyList.add(new Candy("KitKat", 3, 80, 30));
        candyList.add(new Candy("Snickers", 4, 70, 20));

    }
    double balanceInEuro() {

        // De balance wordt opgeslagen in centen, dus deze functie wordt gebruikt om het te laten zien in euro's
        return (double) balance / 100;
    }

    void printWelcomeMessage() {

        System.out.println("+-----------------------------------------+");
        System.out.println("| Welkom bij deze geweldig snoepautomaat! |");
        System.out.println("+-----------------------------------------+");
    }

    void printBalance() {

        // Laat de balance zien op het scherm
        System.out.println("Balans: € " + balanceInEuro());
    }

    void printInventory() {

        // Ga door de snoep lijst, en laat een description zien van elk soort snoep
        for (int i = 0; i < candyList.size(); i++ ) {
            System.out.println( candyList.get(i).description());
        }

    }

    void printInputInfo() {

        // Laat een klein stukje text zien dat
        System.out.println("+-----------------------------------------------------------------------------------------------------------------------+");
        System.out.println("| Kopen? : KOOP<Code> | Geld toevoegen? : ADD<HoeveelheidInCenten> | Inventaris info? : INFO | Klaar met kopen? : KLAAR |");
        System.out.println("+-----------------------------------------------------------------------------------------------------------------------+");
    }

    void getInput() {
        printBalance();
        printInputInfo();
        String input = inputScanner.next();
        handleInput(input);
    }

    void handleInput(String input) {

        if (input.equals("INFO")) {
            printEmptyLines(10);
            printInventory();
        }

        else if (input.equals("KLAAR")) {
            printEmptyLines(10);
            giveChange();

            status = "KLAAR";

            System.out.println("Eet smakelijk!");
        }

        else if (input.length() >= 4 && input.substring(0, 4).equals("KOOP")) {

            printEmptyLines(10);

            if (input.length() > 4) {

                int code;

                // checken of de input ook daadwerkelijk een int bevat
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

            printEmptyLines(10);
            if (input.length() > 3) {

                int toevoeging;

                // checken of de input ook daadwerkelijk een int bevat
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

        // Ga door de snoeplijst
        for (Candy candy : candyList) {

            // als je het soort snoep vind met de goede code, return die soort snoep dan
            if (candy.code == code) {
                return candy;
            }
        }

        // als er niets is gevonden, return dan null
        return null;
    }

    void buyCandy(int code) {

        Candy candy = findCandy(code);

        // check of het snoep wel is gevonden
        if (candy != null) {

            balance -= candy.price;
            candy.amountLeft--;
            giveCandy(candy);
        }

        // als het snoep niet isw gevonden, dan is de code verkeerd ingevoerd
        else {
            System.out.println("Deze code klopt niet, kies AUB een ander soort snoep");
        }

    }

    void giveCandy(Candy candy) {

        // "Geef" het snoep aan de klant
        System.out.println("Hier is uw " + candy.name);
    }

    void printEmptyLines(int amount) {

        // Print een aantal witregels zodat alles overzichtelijk blijft
        for (int i = 0; i < amount; i++) {
            System.out.println();
        }
    }

    void giveChange() {

        // Geef alleen geld terug als er ook daadwerkelijk geld is om terug te geven
        if (balance > 0) {

            System.out.println("U krijgt nog geld terug:");

            // Blijf munten teruggeven als de balance nog hoger is dan 0
            while (balance > 0) {

                int grootstMogelijkeMunt = 0;

                // Ga door de lijst van munteenheden, en zoek de hoogst mogelijke munt om terug te geven
                for (int i = 0; i < coinTypeList.length; i++) {
                    if (balance >= coinTypeList[i]) {
                        grootstMogelijkeMunt = coinTypeList[i];
                        break;
                    }
                }

                // Haal de grootste mogelijke munt van de balance af, en "geef het geld terug"
                balance -= grootstMogelijkeMunt;
                System.out.println("€ "+ (double)grootstMogelijkeMunt / 100);

                // als de balance nu 0 is dan stopt deze loop, zo niet dan gaat het weer verder
            }

        }


    }
}