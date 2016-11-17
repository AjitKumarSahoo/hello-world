package Practice;

/**
 * Author: Ajit Ku. Sahoo
 * Date: 11/16/2016.
 */
public class MatrixRotation {

    private static void printMatrix(int[][] rotated) {
        for (int i = 0; i < rotated.length; i++) {
            for (int j = 0; j < rotated[0].length; j++) {
                System.out.print(rotated[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int[][] rotate(int[][] rect, boolean rightRotate) {
        int rows = rect.length;
        int cols = rect[0].length;

        int[][] rotated = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rightRotate) {
                    rotated[j][rows - 1 - i] = rect[i][j];
                } else {
                    rotated[cols-1-j][i] = rect[i][j];
                }
            }
        }

        return rotated;
    }

    /**
     *  Taking a horizontal mirror image of the square and then taking a mirror image the resultant matrix along the
     *  diagonal (0,0) to (n-1,n-1) if right rotation is intended otherwise mirror image should be taken across
     *  (0,n-1) to (n-1,0).
     *  Mirror Image across diagonal (Right Rotation): Take (n-1,n-1) as pivot, and swap all
     *  elements at row n-1 with elements at column n-1. Similarly take the next (n-2,n-2) as next pivot and swap
     *  elements at row & column n-2. Keep on doing until you reach 0,0. The mirror image across diagonal can also
     *  be started from pivot (0,0) to pivot (n-1,n-1).
     *  Mirror Image across diagonal (Right Rotation): Follow the above instructions, but pivot along the other diagonal
     * @param square input square
     * @param rightRotate if true, rotate right; otherwise left
     */
    private void inplaceRotate(int[][] square, boolean rightRotate) {
        int rows = square.length;

        //Mirror image along horizontal
        int[] temp;
        for (int i = 0; i < rows/2; i++) {
            temp = square[i];
            square[i] = square[rows-i-1];
            square[rows-i-1] = temp;
        }
            
        if (rightRotate) {
            //mirror image along left-top 2 bottom-right diagonal
            for (int i = rows-1; i > 0; i--) {
                int j = i;
                for (int l = 0; l < i; l++) {
                    j--;
                    int t = square[i][j];
                    square[i][j] = square[j][i];
                    square[j][i] = t;
                }
            }
        } else {
            //mirror image along left-bottom 2 top-right diagonal
            for (int i = 0; i < rows; i++) {
                int j = rows-i-1;
                for (int k = 0; k < j; k++) {
                    int t = square[i][j-k-1];
                    square[i][j-k-1] = square[i+k+1][j];
                    square[i+k+1][j] = t;
                }
            }
        }

    }

    public static void main(String[] args) {

        int[][] rect = {
                {1, 2, 3,  4},
                {5, 6, 7,  8},
                {9,10,11, 12},
                {13,14,15,16},
                {17,18,19,20},
                {21,22,23,24}
        };

//        System.out.println("Right rotated:\n" + Arrays.deepToString(new RectangleRotation().rotate(rect, true)));
//        System.out.println("Left rotated:\n" + Arrays.deepToString(new RectangleRotation().rotate(rect, false)));

        System.out.println("Original");
        printMatrix(rect);
        
        MatrixRotation mr = new MatrixRotation();
        int[][] rotated = mr.rotate(rect, true);
        System.out.println("right rotated:");
        printMatrix(rotated);

        int[][] rotated1 = mr.rotate(rect, false);
        System.out.println("left rotated:");
        printMatrix(rotated1);

        int[][] square = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
        };
        mr.inplaceRotate(square, false);
        System.out.println("left rotation (in place rotated)");
        printMatrix(square);
    }
}
