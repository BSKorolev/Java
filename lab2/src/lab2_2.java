public class lab2_2 {
    public static int[] mergeArrays(int[] arr1, int[] arr2) {
        // Создаем новый массив для объединения
        int[] merged = new int[arr1.length + arr2.length];
        // Инициализируем индексы для массивов arr1 и arr2
        int i = 0; // Индекс для arr1
        int j = 0; // Индекс для arr2
        int k = 0; // Индекс для merged
        // Перебираем элементы массивов arr1 и arr2
        while (i < arr1.length && j < arr2.length) {
            // Если текущий элемент из arr1 меньше или равен текущему элементу из arr2
            if (arr1[i] <= arr2[j]) {
                // Добавляем элемент из arr1 в merged
                merged[k] = arr1[i];
                i++; // Увеличиваем индекс для arr1
            } else {
                // Иначе, добавляем элемент из arr2 в merged
                merged[k] = arr2[j];
                j++; // Увеличиваем индекс для arr2
            }
            k++; // Увеличиваем индекс для merged
        }
        // Добавляем оставшиеся элементы из arr1 и arr2, если такие есть
        while (i < arr1.length) {
            merged[k] = arr1[i];
            i++; // Увеличиваем индекс для arr1 и merged
            k++;
        }
        while (j < arr2.length) {
            merged[k] = arr2[j];
            j++; // Увеличиваем индекс для arr2 и merged
            k++;
        }
        // Возвращаем объединенный массив
        return merged;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 6};
        int[] arr2 = {2, 4, 7, 8};
        int[] merged = mergeArrays(arr1, arr2);
        System.out.print("Merged array: ");
        for (int element : merged) {
            System.out.print(element + " ");
        }
    }
}