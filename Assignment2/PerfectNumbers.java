package com.company;

import java.io.*;
import java.lang.*;
import java.util.*;

public class PerfectNumbers {
    public static void main(String[] args) {
        int limit;
        System.out.println("Enter a limit");
        Scanner in = new Scanner(System.in);
        limit = in.nextInt();

        for (int i = 2; i < limit; i++) {
            printPerfectNumber(i);
        }
    }

    private static void printPerfectNumber(int number) {
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1);
        for (int i = 2; i < number; i++) {
            if ((number % i) == 0) {
                numbers.add(i);
            }
        }
        if (sumList(numbers) == number) {
            System.out.println(number);
        }
    }

    private static int sumList(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
