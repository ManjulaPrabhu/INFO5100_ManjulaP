package com.neu.info5100;

import java.util.ArrayList;
import java.util.Scanner;


public class Median {
    static int m, n;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter m and n values");
        m = in.nextInt();
        n = in.nextInt();

        int[] nums1 = new int[m + 1];
        int[] nums2 = new int[n + 1];


        System.out.println("Enter the first array of numbers");
        for (int i = 0; i < m; i++) {
            nums1[i] = in.nextInt();
        }
        nums1[m] = Integer.MAX_VALUE;
        nums2[n] = Integer.MAX_VALUE;
        System.out.println("Enter the second array of numbers");
        for (int j = 0; j < n; j++) {
            nums2[j] = in.nextInt();
        }

        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        ArrayList<Integer> completeArray = new ArrayList<Integer>();

        int l = 0;
        int k = 0;
        int size = m + n - 1;
        while (size >= 0) {
            if (nums1[l] < nums2[k]) {
                completeArray.add(nums1[l]);
                l = (l < m) ? ++l : l;
            } else {
                completeArray.add(nums2[k]);
                k = (k < n) ? ++k : k;
            }
            size--;
        }

        return calculateMedian(completeArray);
    }

    private static double calculateMedian(ArrayList<Integer> completeArray) {
        if ((m + n) % 2 == 0) {
            double sum = completeArray.get((m + n) / 2) + completeArray.get(((m + n) / 2) - 1);
            return (sum / 2);
        } else {
            int index = (m + n) / 2;
            return (completeArray.get(index));

        }
    }
}