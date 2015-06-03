package com.company;

public class Candy {

    String name;
    int price;
    int code;
    int amountLeft;

    /**
     * The Candy Initializer
     * @param name the name of the Candy
     * @param code the code of the Candy
     * @param price the Candy's price, in eurocents
     * @param amountLeft the amount of Candy left
     */
    public Candy(String name, int code, int price, int amountLeft) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.amountLeft = amountLeft;
    }

    /**
     * Converts the price from eurocents to euro (a double)
     * @return a double containging the price in Euro's
     */
    double priceInEuro() {
        return (double) price / 100;
    }

    String formattedPriceInEuro() {
        return String.format("€%.2f", priceInEuro());
    }

    void addCandy(int amount) {
        amountLeft += amount;
    }

    /**
     * Generates a string with a description of the candy, usefull for debuging
     * @return the generated description string
     */
    String description() {
        return name + "\n"  + "Code:\t" + code  + "\n" + "Price:\t€ " + priceInEuro() + "\n" + "Amount Left:\t" + amountLeft + "\n";
    }

}