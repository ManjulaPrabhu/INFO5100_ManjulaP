package com.neu.info5100;

import java.util.*;

public class IntToRoman {
    private static HashMap<Integer, String> lookupTable = new HashMap<>();

    public static void main(String[] args) {
        int number;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a number between 0 to 3999");
        number = in.nextInt();
        if (number < 0 || number > 3999) {
            System.out.println("Invalid input detected. Exiting.");
            System.exit(0);
        }
        initializeHashMap();
        int multiplier = 10;
        List<String> outputList = new ArrayList<String>();
        while (number != 0) {
            outputList.add(calculateRoman(multiplier / 10, number % multiplier));
            number = number / multiplier * multiplier;
            multiplier *= 10;
        }
        String output = "";
        Collections.reverse(outputList);
        for (String roman : outputList) {
            output = output.concat(roman);
        }
        System.out.println(output);
    }


    /**
     * Converts the input given by position and returns the roman numerals.
     * @param base - one, tenth ... positions.
     * @param number - the number to convert.
     * @return - roman number as String.
     */
    private static String calculateRoman(int base, int number) {
        String output = "";
        int initialValue = number;
        if (lookupTable.containsKey(number)) {
            output = lookupTable.get(number);
        } else {
            while (initialValue != 0) {
                output = output + lookupTable.get(base);
                initialValue -= base;
            }
            output = (lookupTable.get(initialValue) + output);
        }
        return output;
    }

    private static void initializeHashMap() {
        lookupTable.put(0, "");
        lookupTable.put(1, "I");
        lookupTable.put(4, "IV");
        lookupTable.put(5, "V");
        lookupTable.put(9, "IX");
        lookupTable.put(10, "X");
        lookupTable.put(40, "XL");
        lookupTable.put(50, "L");
        lookupTable.put(90, "XC");
        lookupTable.put(100, "C");
        lookupTable.put(500, "D");
        lookupTable.put(400, "CD");
        lookupTable.put(900, "CM");
        lookupTable.put(1000, "M");
    }
}
