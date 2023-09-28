public class lab2_6 {
    public static int findArraySum(int[][] array) {
        int sum = 0;
        for (int[] row : array) {
            for (int element : row) {
                sum += element;
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 6, 2},
                {107, 5, 7},
                {10, 25, 0}
        };

        int sum = findArraySum(matrix);
        System.out.println("The sum of all elements in a two-dimensional array: " + sum);
    }
}