import java.util.Scanner;

public class ArrayEqualizer {
    static int limit;
    static int numberOfMoves = 1;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the array limit");
        limit = in.nextInt();
        System.out.println("Enter the array values");
        int[] input = new int[limit];
        for (int i = 0; i < limit; i++) {
            input[i] = in.nextInt();
        }
        System.out.println(minMoves(input));
    }

    private static int minMoves(int[] input) {
        int largestValue = input[0];
        int largestValueIndex = 0;

        /** Finding the largest number in the input array **/
        for (int j = 0; j < limit; j++) {
            if (largestValue > input[j]) {
                continue;
            } else {
                largestValue = input[j];
                largestValueIndex = j;
            }
        }
        input = equalizeArray(largestValue, input, largestValueIndex);
        if (!check(input)) {
            numberOfMoves++;
            return minMoves(input);
        } else {
            return numberOfMoves;
        }
    }

    public static boolean check(int[] input) {
        for (int l = 0; l < limit; l++) {
            if (input[0] != input[l]) {
                return false;
            }
        }
        return true;
    }

    public static int[] equalizeArray(int largestValue, int[] input, int largestValueIndex) {
        int incrementCounter = 0;
        for (int k = 0; k < limit; k++) {
            if (incrementCounter < limit - 1) {
                if ((input[k] <= largestValue) && k != largestValueIndex) {
                    input[k] += 1;
                    incrementCounter++;
                } else {
                    continue;
                }
            } else {
                break;
            }
        }
        return input;
    }


}


