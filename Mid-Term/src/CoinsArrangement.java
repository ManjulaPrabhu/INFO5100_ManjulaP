import java.util.Scanner;

public class CoinsArrangement {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of coins to be printed");
        int n = in.nextInt();
        if (n > 0) {
            try {
                System.out.println(arrangeCoins(n));
            } catch (Exception e) {
                System.out.println("Please enter a smaller value");
            }
        }

    }

    private static int arrangeCoins(int n) {
        for (int i = 1; i < n; i++) {
            int sumOfI = (i * (i + 1)) / 2;
            if (sumOfI > n) {
                return i - 1;
            }
        }
        return 0;
    }
}
