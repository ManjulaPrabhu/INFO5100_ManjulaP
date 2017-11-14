import java.util.Scanner;

public class PascalTriangle {
    public static void main(String[] args) {
        int[][] input;
        int rows;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the no of Rows");
        rows = in.nextInt();
        input = new int[rows + 1][rows + 1];
        printPascal(input, rows);
    }

    private static void printPascal(int[][] input, int rows) {
        for (int line = 0; line <= rows; line++) {
            for (int column = 0; column < line; column++) {
                if (column == 0 || column == line) {
                    input[line][column] = 1;
                } else {
                    input[line][column] = input[line - 1][column - 1] + input[line - 1][column];
                }
                System.out.print(input[line][column]);
            }
            System.out.println("\n");
        }
    }
}
