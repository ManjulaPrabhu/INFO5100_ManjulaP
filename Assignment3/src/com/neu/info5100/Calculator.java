
package com.neu.info5100;

import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        int operation, operand1, operand2, operand3;

        BasicArithmeticOperations arithmeticOperations = new BasicArithmeticOperations();
        MetricConverter converter = new MetricConverter();
        QuadraticSolution quadratic = new QuadraticSolution();

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("================= Simple Calculator =============");
            System.out.println("1.Addition");
            System.out.println("2.Subtraction");
            System.out.println("3.Multiplication");
            System.out.println("4.Division");
            System.out.println("5.SquareRoot");
            System.out.println("6.Square");
            System.out.println("7.Cube");
            System.out.println("8.Fahrenheit-Celsius");
            System.out.println("9.Feet-Inches");
            System.out.println("10.Quadratic Equation Solution");
            System.out.println("11.Exit");
            System.out.println("Enter an operation");
            operation = in.nextInt();

            switch (operation) {
                case 1:
                    System.out.println("Enter two integers");
                    operand1 = in.nextInt();
                    operand2 = in.nextInt();
                    System.out.println(arithmeticOperations.add(operand1, operand2));

                    break;
                case 2:
                    System.out.println("Enter two integers");
                    operand1 = in.nextInt();
                    operand2 = in.nextInt();
                    System.out.println(arithmeticOperations.subtract(operand1, operand2));
                    break;
                case 3:
                    System.out.println("Enter two integers");
                    operand1 = in.nextInt();
                    operand2 = in.nextInt();
                    System.out.println(arithmeticOperations.multiply(operand1, operand2));
                    break;
                case 4:
                    System.out.println("Enter two integers");
                    operand1 = in.nextInt();
                    operand2 = in.nextInt();
                    System.out.println(arithmeticOperations.divide(operand1, operand2));
                    break;
                case 5:
                    System.out.println("Enter an integer");
                    operand1 = in.nextInt();
                    System.out.println(arithmeticOperations.squareRoot(operand1));
                    break;
                case 6:
                    System.out.println("Enter an integer");
                    operand1 = in.nextInt();
                    System.out.println(arithmeticOperations.square(operand1));
                    break;
                case 7:
                    System.out.println("Enter an integer");
                    operand1 = in.nextInt();
                    System.out.println(arithmeticOperations.cube(operand1));
                    break;
                case 8:
                    System.out.println("Enter the Fahrenheit value");
                    operand1 = in.nextInt();
                    System.out.println(operand1 +" Fahrenheit equals " + converter.fahrenheitToCelsius(operand1) + " Celsius.");
                    break;
                case 9:
                    System.out.println("Enter the value to convert");
                    operand1 = in.nextInt();
                    System.out.println(operand1 +" feet equals " + converter.feetToInches(operand1) + " inches.");
                    break;
                case 10:
                    System.out.println("Enter A");
                    System.out.println("Enter B");
                    System.out.println("Enter C");
                    operand1 = in.nextInt();
                    operand2 = in.nextInt();
                    operand3 = in.nextInt();
                    System.out.println(Arrays.toString(quadratic.findQuadraticSolution(operand1, operand2, operand3)));
                    break;
                case 11:
                    System.exit(0);
                default:
                    System.out.println("Invalid Option");
                    break;

            }
        }

    }
}

class BasicArithmeticOperations {
    int add(int operand1, int operand2) {
        return (operand1 + operand2);
    }
    double add(double operand1, double operand2) { return (operand1 + operand2); }
    int subtract(int operand1, int operand2) { return (operand1 - operand2); }
    double subtract(double operand1, double operand2) { return (operand1 - operand2); }
    int multiply(int operand1, int operand2) { return (operand1 * operand2); }
    double divide(int operand1, int operand2) { return (operand2 == 0) ? 0.0 : (float) operand1/operand2; }
    double divide(double operand1, double operand2) { return (operand2 == 0) ? 0.0 : (float) operand1/operand2; }
    double squareRoot(int operand1) { return (Math.pow(operand1, 0.5)); }
    double squareRoot(double operand1) { return (Math.pow(operand1, 0.5));}
    double square(int operand1) {
        return (Math.pow(operand1, 2));
    }
    double cube(int operand1) {
        return (Math.pow(operand1, 3));
    }

}

class MetricConverter {
    private BasicArithmeticOperations arithmeticForConversion = new BasicArithmeticOperations();

    double fahrenheitToCelsius(int operand1) {
        int value1 = arithmeticForConversion.subtract(operand1, 32);
        int value2 = arithmeticForConversion.multiply(value1, 5);
        return arithmeticForConversion.divide(value2, 9);
    }

    int feetToInches(int operand1) {
        return arithmeticForConversion.multiply(operand1, 12);
    }
}

class QuadraticSolution {
    // Maybe it's better to name operand1/2/3 as a, b, c for readability
    double[] findQuadraticSolution(int operand1, int operand2, int operand3) {

        BasicArithmeticOperations simpleCalculator = new BasicArithmeticOperations();
        double[] quadraticSolution = new double[2];
        double numeratorResult = 0;
        // No need to have a for loop here, simplify the logic for this part.
        for (int i = 0; i < 2; i++) {
            //b^2
            double squareResult = simpleCalculator.square(operand2);
            //4ac
            int multiplyProduct1 = simpleCalculator.multiply(4, operand1);
            int multiplyResult = simpleCalculator.multiply(multiplyProduct1, operand3);

            //b^2 -4ac
            // What if b^2 - 4ac < 0?
            double subtractResult = simpleCalculator.subtract(squareResult, multiplyResult);
            //sqrt(b^2 -4ac)
            double squareRootResult = simpleCalculator.squareRoot(subtractResult);
            if (i == 0) {
                numeratorResult = simpleCalculator.subtract(squareRootResult,(double) operand2);
            } else {
                numeratorResult = simpleCalculator.add(squareRootResult, (double) operand2);
                numeratorResult *= -1;
            }
            // What if a == 0?
            int denominatorResult = simpleCalculator.multiply(2, operand1);
    
            double factor = simpleCalculator.divide(numeratorResult, denominatorResult);
            quadraticSolution[i] = factor;
        }
        return quadraticSolution;
    }
}









