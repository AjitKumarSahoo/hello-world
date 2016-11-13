package Practice.HackerRankPractice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Author: Ajit Ku. Sahoo
 * Date: 11/11/2016.
 *
 * NCR CodeSprint #SpiralMessage
 */
public class SpiralMatrixOfEncodedMsg {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        char[][] matrix = new char[n][];
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }

        int row = n-1, col =0;
        int top = 0, bottom = n-1, right = m-1, left = 0;
        String dir = "up";
        int count = 0;
        boolean wasLastCharHash = true;

        while(true) {

            if (isVerticalDirection(dir)) {
                if (dir.equals("down")) { //down
                    while (row < bottom) {
                        if (matrix[row][col] >= 97 && matrix[row][col] <= 122) {
                            wasLastCharHash = false;
                        } else {
                            if (!wasLastCharHash) {
                                count++;
                                wasLastCharHash = true;
                            }
                        }
                        row++;
                    }
                    right--;
                    if (right < left)
                        break;
                    dir = "left";
                } else { //up
                    while (row > top) {
                        if (matrix[row][col] >= 97 && matrix[row][col] <= 122) {
                            wasLastCharHash = false;
                        } else {
                            if (!wasLastCharHash) {
                                count++;
                                wasLastCharHash = true;
                            }
                        }
                        row--;
                    }
                    left++;
                    if (left > right)
                        break;
                    dir = "right";
                }
            } else {
                if (dir.equals("right")) {
                    while (col < right) {
                        if (matrix[row][col] >= 97 && matrix[row][col] <= 122) {
                            wasLastCharHash = false;
                        } else {
                            if (!wasLastCharHash) {
                                count++;
                                wasLastCharHash = true;
                            }
                        }
                        col++;
                    }
                    top++;
                    if (top > bottom)
                        break;
                    dir = "down";
                } else {
                    while (col > left) {
                        if (matrix[row][col] >= 97 && matrix[row][col] <= 122) {
                            wasLastCharHash = false;
                        } else {
                            if (!wasLastCharHash) {
                                count++;
                                wasLastCharHash = true;
                            }
                        }
                        col--;
                    }
                    bottom--;
                    if (top > bottom)
                        break;
                    dir = "up";
                }

            }
        }

        if (!(wasLastCharHash && matrix[row][col] == '#')) {
            count++;
        }

        System.out.println(count);
    }

    private static boolean isVerticalDirection(String dir) {
        return dir.equals("up") || dir.equals("down");
    }


}
