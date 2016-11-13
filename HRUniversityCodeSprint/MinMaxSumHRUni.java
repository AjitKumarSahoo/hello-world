package Practice.HRUniCodeSprint;

import java.util.Scanner;

/**
 * Author: Ajit Ku. Sahoo
 * Date: 11/11/2016.
 */
public class MinMaxSumHRUni {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long[] nums = new long[5];

        for (int i = 0; i < 5; i++) {
            nums[i] = in.nextLong();
        }

        long min = nums[0];
        long max = nums[0];
        long sum = nums[0];

        for (int i = 1; i < 5; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }
            if (max < nums[i]) {
                max = nums[i];
            }
            sum += nums[i];
        }
        System.out.print((sum - max) + " " + (sum - min));

    }


}
