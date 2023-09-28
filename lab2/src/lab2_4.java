public class lab2_4 {
    public static int[][] rotateMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] rotatedMatrix = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedMatrix[j][rows - 1 - i] = matrix[i][j];
            }
        }

        return rotatedMatrix;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 6, 2},
                {107, 5, 7},
                {10, 25, 0}
        };

        int[][] rotated = rotateMatrix(matrix);

        System.out.println("Rotated Matrix:");
        for (int i = 0; i < rotated.length; i++) {
            for (int j = 0; j < rotated[i].length; j++) {
                System.out.print(rotated[i][j] + " ");
            }
            System.out.println();
        }
    }
}