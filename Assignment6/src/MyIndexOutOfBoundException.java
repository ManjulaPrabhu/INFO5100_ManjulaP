public class MyIndexOutOfBoundException {
    static int[] input = new int[6];
    static int i = 0;

    public static void main(String[] args) {
        input = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println("The Array Elements are:");
        try {
            while (i <= 6) {
                System.out.println(input[i]);
                i++;
            }
        } catch (ArrayIndexOutOfBoundsException ae) {
            System.out.println(new MyIndexOutOfBoundException().toString());
        }
    }

    public String toString() {
        String errorMessage = "Error Message: Index:" + i + ", but Lower bound: 0,Upper Bound:" + (input.length - 1);
        return errorMessage;
    }
}
