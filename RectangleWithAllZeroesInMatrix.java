package Practice;

import java.awt.*;

/**
 * Author: Ajit Ku. Sahoo
 * Date: 10/8/2016.
 */
public class RectangleWithAllZeroesInMatrix {

    private static class RectangleData {
        private int left;
        private int right;
        private int bottom;
        private int top;
        private int area;
    }

    /**
     * Given a 2D matrix as input, it finds the biggest rectangle with all zeroes
     * @param input 2D input matrix
     * @return rectangle boundary with all zeroes, or null if no zero is found in the matrix
     */
    private Rectangle getRectangleWithMaxZeroes(int[][] input) {
        int row = input.length;
        int column = input[0].length;

        System.out.println("Input:");
        printMatrix(input);

        int[][] matrix = getNoOfZeroesMatrix(input, row, column);

        System.out.println("\nIntermediate matrix:");
        printMatrix(matrix, row + 1, column);

        //analyze the histograms and get the rectangleData from the histogram with highest underneath area
        RectangleData finalRect = null;
        int max = 0;
        for (int i = 1; i <= row; i++) {
            RectangleData temp = getMaxAreaUnderHist(matrix[i], i, column); //each bottom is a histogram with bins of diff top
            if (temp.area > max) {
                finalRect = temp;
            }
        }

        if (finalRect != null) {
            int topX = finalRect.top;
            int topY = finalRect.left;
            int width = finalRect.bottom - finalRect.top + 1;
            int height = finalRect.right - finalRect.left + 1;
            return new Rectangle(topX, topY, width, height);
        }

        return null;
    }

    /**
     * Given a 2D matrix, it creates a matrix where each cell represents the no. of continuous zeros found starting from
     * the current cell and moving upwards in that column. If the cell has non-zero entry in the input matrix, it will
     * be marked as Zero.
     *
     * @param input 2D matrix
     * @param row
     * @param column
     * @return 2D matrix with count of zeroes
     */
    private int[][] getNoOfZeroesMatrix(int[][] input, int row, int column) {

        int[][] matrix = new int[row + 1][column];

        //first bottom will be all zeroes to help us with our generic formula. otherwise need to handle first bottom explicitly
        for (int i = 1; i < row + 1; i++) {
            for (int j = 0; j < column; j++) {
                if (input[i - 1][j] != 0) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }
        return matrix;
    }

    /**
     * Prints a 2D matrix
     * @param matrix
     */
    private void printMatrix(int[][] matrix) {
        printMatrix(matrix, matrix.length, matrix[0].length);
    }

    /**
     * Prints a 2D matrix
     * @param matrix
     * @param row
     * @param column
     */
    private void printMatrix(int[][] matrix, int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    /*private RectangleData getRectBoundary(int row, int column, int[][] matrix) {
        RectangleData finalRect = null;
        int max = 0;
        for (int i = 1; i <= row; i++) {
            RectangleData rectData = getMaxAreaUnderHist(matrix[i], i, column); //each bottom is a histogram with bins of diff top
            if (rectData.area > max) {
                finalRect = rectData;
            }
        }
        return finalRect;
    }*/

    /**
     * Given a histogram, it computes the max area & the contributing bins covered by the histogram with Dynamic Programming concept
     * @param hist
     * @param row
     * @param col
     * @return return RectangleData containing boundary of bins contributing towards the max area
     */
    private RectangleData getMaxAreaUnderHist(int[] hist, int row, int col) { //need to solve it using Dynamic Programming
        int max = 0;
        RectangleData rb = new RectangleData();
        int[][] dp = new int[col][col];

        for (int width = 1; width <= col; width++) {
            for (int left = 0; left < col - width + 1; left++) {

                int right = left + width - 1;

                if (width == 1) {
                    dp[left][right] = hist[left];
                    if (max < hist[left]) {
                        max = hist[left];
                        rb.left = left;
                        rb.right = right;
                        rb.top = row - 1;
                        rb.bottom = row;
                        rb.area = max;
                    }
                } else {
                    dp[left][right] = Math.min(dp[left][right - 1], hist[right]);
                    if (max < dp[left][right] * width) {
                        max = dp[left][right] * width;
                        rb.left = left;
                        rb.right = right;
                        rb.top = row - dp[left][right];
                        rb.bottom = row;
                        rb.area = max;
                    }
                }
            }
        }

//      System.out.println("Row: " + bottom);
//      System.out.println("Rect.left = " + rb.left + ", Rect.right = " + rb.right + ", Rect.top = " + rb.top + ", Rect.area = " + rb.area);

        return rb;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0}
        };

        Rectangle rect = new RectangleWithAllZeroesInMatrix().getRectangleWithMaxZeroes(matrix);

        if (rect == null) {
            System.out.println("\nNo zero found in the Input Matrix");
        } else {
            System.out.println("\nOutput: (Largest Rectangle with all zeroes is printed in *)");
            double minX = rect.getMinX();
            double maxX = rect.getMaxX();
            double minY = rect.getMinY();
            double maxY = rect.getMaxY();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if ((i >= minX && i <= maxX) && (j >= minY && j <= maxY)) {
                        System.out.print("* ");
                    } else {
                        System.out.print(matrix[i][j] + " ");
                    }
                }
                System.out.print("\n");
            }
        }
    }
}
