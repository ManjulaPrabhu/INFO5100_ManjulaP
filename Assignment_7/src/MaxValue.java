import java.util.Random;

public class MaxValue extends Thread {
    int value1;
    int value2;
    int largest;


    MaxValue(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public void run() {
        if (value1 > value2) {
            largest = this.value1;
        } else {
            largest = this.value2;
        }
    }

    private static int findMaxValue(int[] input) throws InterruptedException {
        MaxValue[] maxValueThread = new MaxValue[4];
        int threadInput = input[0];
        for (int j = 1; j < 4; j++) {
            maxValueThread[j] = new MaxValue(threadInput, input[j]);
            maxValueThread[j].start();
            Thread.sleep(100);
            threadInput = maxValueThread[j].largest;

        }

        for (int k = 1; k < 4; k++) {
            maxValueThread[k].join();
            threadInput = maxValueThread[k].largest;

        }
        return threadInput;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] input = new int[4];
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            input[i] = rand.nextInt(100);
            System.out.println(input[i]);
        }

        System.out.println("The maximum value in the array is " + findMaxValue(input));
    }


}
