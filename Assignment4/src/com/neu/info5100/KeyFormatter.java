package com.neu.info5100;

import java.util.Scanner;

public class KeyFormatter {
    static int k;

    public static void main(String[] args) {
        int i = 1;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a String");
        String input = in.next();
        System.out.println("Enter the K value");
        k = in.nextInt();
        input = input.replace("-", "");
        if (validateInput(input)) {
            StringBuilder inputStringBuilder = new StringBuilder(input);
            int length = inputStringBuilder.length();

            while ((length - (i * k)) > 0) {
                inputStringBuilder.insert(length - (i * k), "-");
                i++;
            }

            String output = inputStringBuilder.toString();
            System.out.println(output.toUpperCase());
        } else {
            System.out.println("Cannot generate a key, please check provided inputs and try again.");
        }
    }

    static Boolean validateInput(String input) {
        return ((input.length()) <= 12000 && k > 0
                && k < input.length()
                && input.matches("^[a-zA-Z0-9]*$")) ? true : false;
    }
}
