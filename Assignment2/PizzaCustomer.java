package com.company;

import java.lang.*;
import java.util.*;


public class PizzaCustomer {

    private static String custName;
    private static String custOrder;
    private static int noOfPizza;
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        float jalapenoPrice = 5.5f;
        float tomatoPrice = 5.6f;
        float billAmount = 0.0f;

        System.out.println("Enter the Customer Name");
        custName = in.next();

        while (true) {
            System.out.println("1.Jalapeno");
            System.out.println("2.Tomato");
            System.out.println("3.Continue to billing");

            System.out.println("Enter your choice of type");
            custOrder = in.next();


            switch (custOrder) {
                case "1":
                    System.out.println("Enter the Pizza count");
                    noOfPizza = in.nextInt();
                    billAmount = billAmount + jalapenoPrice * noOfPizza;
                    break;

                case "2":

                    System.out.println("Enter the Pizza count");
                    noOfPizza = in.nextInt();
                    billAmount = billAmount + tomatoPrice * noOfPizza;
                    break;

                case "3":
                    System.out.println("The sum of money the Customer spent -- " + billAmount);
                    System.exit(0);

                default:
                    System.out.println("Not on the list");

            }
        }


    }
}
