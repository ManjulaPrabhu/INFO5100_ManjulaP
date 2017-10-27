import java.util.Scanner;

public class ReverseIndices {
    static int n;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the limit of the array input");
        n = in.nextInt();
        int[] nums = new int[n];
        System.out.println("Enter the elements of the array");
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        reverseEvenIndices(nums);

    }

    private static int[] reverseEvenIndices(int[] nums) {
        int limit;
        if ((n % 2) == 0) {
            limit = n - 2;
        } else {
            limit = n - 1;
        }
        for (int j = 0; j < n; j++) {
            if (j != limit && j < limit) {
                if (((j % 2) == 0)) {
                    nums[limit] = nums[j] + nums[limit];
                    nums[j] = nums[limit] - nums[j];
                    nums[limit] = nums[limit] - nums[j];
                    limit -= 2;
                } else {
                    continue;
                }
            } else {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(nums[i]);
        }
        return nums;
    }


}
