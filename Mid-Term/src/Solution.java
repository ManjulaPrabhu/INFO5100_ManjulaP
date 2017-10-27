import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    static int n;
    static int k = 0;
    static int l = 0;
    static ArrayList<Cell> mazeResult = new ArrayList<Cell>();
    static int rowCounter = 0;
    static int columnCounter = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the limit value");
        n = in.nextInt();
        int[][] maze = new int[n][n];
        System.out.println("Enter the values for the N*N matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = in.nextInt();
            }
        }
        System.out.println(findPath(maze));

    }

    public static ArrayList<Cell> findPath(int[][] maze) {
        while ((rowCounter <= n - 1) || (columnCounter <= n - 1)) {
            if ((maze[k][l]) == 1) {
                mazeResult.add(new Cell(k, l));

                if (k < (n - 1) && (l < (n - 1))) {
                    if (checkDownward(maze[k + 1][l])) {
                        k = k + 1;
                        rowCounter++;
                        findPath(maze);
                    } else if (checkForward(maze[k][l + 1])) {
                        l = l + 1;
                        columnCounter++;
                        findPath(maze);
                    }
                } else if (k < (n - 1)) {
                    if (checkDownward(maze[k + 1][l])) {
                        k = k + 1;
                        rowCounter++;
                        findPath(maze);
                    }
                } else if (l < (n - 1)) {
                    if (checkForward(maze[k][l + 1])) {
                        l = l + 1;
                        columnCounter++;
                        findPath(maze);
                    }
                }
                return mazeResult;

            } else {
                return mazeResult;
            }
        }

        return mazeResult;
    }


    private static boolean checkDownward(int i) {
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    private static Boolean checkForward(int i) {
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }
}
