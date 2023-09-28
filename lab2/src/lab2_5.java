import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class lab2_5 {
    public static int[] findPair(int[] nums, int target) {
        // Создаем Map для отслеживания элементов и их индексов
        Map<Integer, Integer> map = new HashMap<>();

        // Проходим по массиву
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // Если есть элемент, сумма которого с текущим равна target, возвращаем пару элементов
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }

            // Сохраняем текущий элемент и его индекс
            map.put(nums[i], i);
        }

        // Если пара элементов не найдена, возвращаем null
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {4, 7, 6, 15};
        int target = 10;

        int[] pair = findPair(nums, target);

        if (pair != null) {
            System.out.println("Array: " + Arrays.toString(nums));
            System.out.println("Target amount: " + target);
            System.out.println("A pair of elements whose sum is equal to " + target + ": " + nums[pair[0]] + ", " + nums[pair[1]]);
        } else {
            System.out.println("Array: " + Arrays.toString(nums));
            System.out.println("Target amount: " + target);
            System.out.println("A pair of elements whose sum is equal to " + target + " not found.");
        }
    }
}