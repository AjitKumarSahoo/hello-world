package Practice.HRUniCodeSprint;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Author: Ajit Ku. Sahoo
 * Date: 11/11/2016.
 * <p>
 * Greedy problem: just like Tasos's umbrella problem
 */
public class RadioTransmitters {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] x = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
        }

        Arrays.sort(x);

        int i = 0, j, range, count = 0;

        while (i < n) {
            count++;

            range = x[i] + k;
            for (j = i + 1; j < n && x[j] <= range; j++);

            if (j == n) {
                break;
            }

            range = x[j-1] + k;
            for (i = j-1; i < n && x[i] <= range; i++) ;

            if (i == n) {
                break;
            }
        }

        System.out.println(count);
    }

}
