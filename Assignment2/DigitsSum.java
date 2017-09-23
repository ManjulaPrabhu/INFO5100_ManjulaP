import java.util.*;
import java.lang.*;


public class DigitsSum {

    public static int sum = 0;
    public static String str1 = "";
    public static int len;
    public static int[] a;


    public static void main(String[] args) {



        System.out.println("Enter a number");
        Scanner s = new Scanner(System.in);
        str1 = s.next();
        len = str1.length();
        int[] a = new int[len];

        String[] str = str1.split("");

        for (int i = 0; i < len; i++) {

            a[i] = Integer.parseInt(str[i]);

        }

        addDigits(a, len);
        System.out.println("The Sum of the digits is" + sum);
    }


    public static int addDigits(int[] a, int len) {


        sum = 0;
        for (int j = 0; j < len; j++) {
            sum = sum + a[j];
        }

        String tempstr = Integer.toString(sum);
        String[] str2 = tempstr.split("");

        for (int k = 0; k < str2.length; k++) {
            a[k] = Integer.parseInt(str2[k]);

        }

        if (sum > 9) {
            addDigits(a, str2.length);
        }



        return sum;
    }
}

