package com.company;

public class Candy {

    String name;
    int price;
    int code;
    int amountLeft;

    public Candy(String name, int code, int price, int amountLeft) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.amountLeft = amountLeft;
    }

    double priceInEuro() {
        return (double) price / 100;
    }

    void addCandy(int hoeveelheid) {
        amountLeft += hoeveelheid;
    }

    String description() {
        return name + "\n"  + "Code:\t" + code  + "\n" + "Price:\t€ " + priceInEuro() + "\n" + "Amount Left:\t" + amountLeft + "\n";
    }

}