import java.util.HashMap;
import java.util.Map;

public class lab2_1 {
    public static String longestUniqueSubstring(String s) {
        // Создаем Map для отслеживания последнего появления каждого символа
        Map<Character, Integer> lastSeen = new HashMap<>();
        // Инициализируем переменные для отслеживания начала и конца подстроки
        int start = 0;
        int end = 0;
        // Инициализируем переменные для отслеживания наибольшей подстроки
        int maxLen = 0;
        int maxStart = 0;
        // Проходим по строке
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // Если символ уже встречался и его последнее появление после начала подстроки
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= start) {
                // Обновляем начало подстроки
                start = lastSeen.get(c) + 1;
            }
            // Обновляем конец подстроки
            end = i;
            // Обновляем или сохраняем наибольшую подстроку, если необходимо
            if (end - start + 1 > maxLen) {
                maxLen = end - start + 1;
                maxStart = start;
            }
            // Сохраняем последнее появление символа
            lastSeen.put(c, i);
        }
        // Возвращаем наибольшую подстроку
        return s.substring(maxStart, maxStart + maxLen);
    }

    public static void main(String[] args) {
        String s = "abcabcdbb";
        String longestSubstring = longestUniqueSubstring(s);
        System.out.println("Longest unique substring: " + longestSubstring);
    }
}