package com.neu.info5100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SimpleVowelRemover {

    static final Set<Character> vowelSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static void main(String[] args) {

        System.out.println("Enter a string to remove vowels : ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();

        for (char singleCharacter : userInput.toCharArray()) {
            if (vowelSet.contains(singleCharacter)) {
                userInput = userInput.replace(singleCharacter, '\u0000');
            }
        }
        System.out.println("Output: " + userInput);
    }

}
