public class lab2_7 {
    public static int[] findMaxElements(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] maxElements = new int[rows];
        for (int i = 0; i < rows; i++) {
            int maxElement = matrix[i][0];
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] > maxElement) {
                    maxElement = matrix[i][j];
                }
            }
            maxElements[i] = maxElement;
        }

        return maxElements;
    }
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 6, 2},
                {107, 5, 7},
                {10, 25, 0}
        };

        int[] maxElements = findMaxElements(matrix);
        for (int element : maxElements) {
            System.out.println(element);
        }
    }
}