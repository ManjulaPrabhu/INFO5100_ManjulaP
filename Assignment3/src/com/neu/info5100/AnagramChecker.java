package com.neu.info5100;

import java.util.Scanner;

public class AnagramChecker {

    public static void main(String[] args) {
        System.out.println("Enter first String");
        Scanner in = new Scanner(System.in);
        String inputString1 = in.next();
        System.out.println("Enter second String");
        String inputString2 = in.next();
        Boolean checkResult = checkIfTwoStringsAreAnagrams(inputString1, inputString2);
        System.out.println("Result is : " + checkResult);
    }

    private static boolean checkIfTwoStringsAreAnagrams(String inputString1, String inputString2) {
        int stringLength = 0;
        Character c1;
        for (int i = 0; i < inputString2.length(); i++) {
            c1 = inputString2.charAt(i);
            if (inputString1.indexOf(c1) >= 0) {
                stringLength++;
            } else {
                break;
            }
        }
        // Consider test case "aaacc" and "aaccc"
        return (stringLength == inputString1.length()) ? true : false;
    }
}

