public class lab2_3 {
    public static int maxSubarraySum(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        // Проходим по массиву
        for (int i = 0; i < nums.length; i++) {
            // Добавляем текущий элемент к текущей сумме
            currentSum += nums[i];

            // Если текущая сумма больше максимальной суммы, обновляем максимальную сумму
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }

            // Если текущая сумма стала отрицательной, сбрасываем ее до 0
            if (currentSum < 0) {
                currentSum = 0;
            }
        }

        // Возвращаем максимальную сумму подмассива
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = maxSubarraySum(nums);
        System.out.println("Maximum amount of a subarray: " + maxSum);
    }
}