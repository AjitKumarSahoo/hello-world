package Practice.HRUniCodeSprint;

import java.util.Scanner;

/**
 * Author: Ajit Ku. Sahoo
 * Date: 11/11/2016.
 */
public class KinderGardenRoundTable {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] time = new int[n];
        for (int i =0; i < n; i++) {
            time[i] = scanner.nextInt();
        }

        int max = -1;
        int startIndex = 0;

        for (int i =0; i < n; i++) {

            int count = 0;
            int j = i;

            do {
                int exTimeJGet;
                if (j == i) {
                    exTimeJGet = 0;
                } else if (j > i) {
                    exTimeJGet = j - i;
                } else {
                    exTimeJGet = n - i + j;
                }

                if (exTimeJGet >= time[j]) {
                    count++;
                }

                j = (++j % n);

            } while (j != i);

            if (max < count) {
                max = count;
                startIndex = i;
            }
        }

        System.out.println(startIndex+1);
    }
}
