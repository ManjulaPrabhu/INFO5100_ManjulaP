package com.company;

import java.lang.*;
import java.util.*;

public class IsocelesPrinting {

    static char SPACE = ' ';
    static char STAR = '*';

    public static void main(String[] args) {
        int side;
        int noOfSpaces;
        System.out.println("Enter a side");
        Scanner in = new Scanner(System.in);
        side = in.nextInt();
        printIsoscelesTriangle(side);

    }

    private static void printIsoscelesTriangle(int side) {
        for (int i = 1; i <= side; i++) {
            if (i < 3 || i == side) {
                System.out.println(printSequence(STAR, i));
            } else {
                System.out.println(String.format("*%s*", printSequence(SPACE, i - 2)));
            }

        }
    }

    private static String printSequence(char type, int times) {
        return new String(new char[times]).replace('\0', type);
    }

}