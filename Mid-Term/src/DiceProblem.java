import java.util.Scanner;

public class DiceProblem {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of faces the dice will have 'm'");
        int faces = in.nextInt();
        System.out.println("Enter the number of dices that is going to be used");
        int dices = in.nextInt();
        System.out.println("Enter the sum that you wish to find out");
        int sum = in.nextInt();
        System.out.println(countNumberOfPossibleWays(faces, dices, sum));
    }

    private static int countNumberOfPossibleWays(int faces, int dices, int sum) {
        int combinations[][] = new int[dices + 1][sum + 1];
        combinations[0][0] = 1;
        for (int i = 1; i <= dices; i++) {
            for (int x = 1; x <= sum; x++) {
                for (int k = 1; k <= faces && k <= x; k++) {
                    combinations[i][x] += combinations[i - 1][x - k];
                }
            }
        }
        return combinations[dices][sum];
    }
}
