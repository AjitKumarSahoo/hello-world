package Practice.HRUniCodeSprint;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * Author: Ajit Ku. Sahoo
 * Date: 11/11/2016.
 */
public class TSPNearestNeighbour {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int adjMatrix[][] = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int c1 = scanner.nextInt();
            int c2 = scanner.nextInt();
            adjMatrix[c1][c2] = 1;
            adjMatrix[c2][c1] = 1;
        }

        int[] finalPath = new int[n];
        int max = -1;
        for (int n1 = 0; n1 < n; n1++) {
            Stack<Integer> stack = new Stack<>();
            int[] visited = new int[n + 1];
            int[] path = new int[n];
            int count=-1;
            visited[n1+1] = n1+1;
            path[++count] = n1+1;
            stack.push(n1+1);
            int node, dst = 0, i;

            while (!stack.isEmpty()) {
                node = stack.peek();
                i = 1;
                while (i <= n) {
                    if (adjMatrix[node][i] == 1 && visited[i] == 0) {
                        dst = i;
                        break;
                    }
                    i++;
                }
                if (i<=n) {
                    visited[dst] = 1;
                    path[++count] = dst;
                    stack.push(dst);
                    continue;
                }
                stack.pop();
            }

            if (max < count+1) {
                max = count+1;
                System.arraycopy(path, 0, finalPath, 0, max);
            }
        }
        System.out.println(max);
        for (int i=0 ; i < max; i++)
            System.out.print(finalPath[i] + " ");
    }
}
