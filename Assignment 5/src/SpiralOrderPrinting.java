import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpiralOrderPrinting {


    static int m, n;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] matrix;
        System.out.println("Enter the value for number of rows m:");
        m = in.nextInt();
        System.out.println("Enter the value for number of columns n:");
        n = in.nextInt();

        matrix = new int[m][n];
        System.out.println("Enter the elements of the matrix");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        System.out.println(spiralOrder(matrix));
    }


    private static List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> spiralorderOutput = new ArrayList<Integer>();
        int rowCounter = 0;
        int columnCounter = 0;
        int i;
        while ((rowCounter < m) && (columnCounter < n)) {
            for (i = columnCounter; i < n; ++i) {
                spiralorderOutput.add(matrix[rowCounter][i]);
            }
            rowCounter++;

            for (i = rowCounter; i < m; ++i) {
                spiralorderOutput.add(matrix[i][n - 1]);
            }
            n--;

            if (rowCounter < m) {
                for (i = (n - 1); i > columnCounter; --i) {
                    spiralorderOutput.add(matrix[m - 1][i]);
                }
                m--;
            }

            if (columnCounter < n && (rowCounter != columnCounter)) {
                for (i = m; i >= rowCounter; --i) {
                    spiralorderOutput.add(matrix[i][columnCounter]);
                }
                columnCounter++;

            }
        }
        return spiralorderOutput;
    }

}



