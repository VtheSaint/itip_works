import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class task3 {
    public static void main(String[] args) {
        // Task 1 
        String result1 = replaceVowels("Example string with vowels");
        System.out.println(result1);
        
        // Task 2 
        String result2 = stringTransform("Mississippi");
        System.out.println(result2);

        // Task 3
        boolean result3 =doesBlockFit(1, 3, 5, 4, 5);
        System.out.println(result3);

        // Task 4
        boolean result4 = numCheck(12345);
        System.out.println(result4);

        // Task 5
        int[] k = {1, -3, 2};
        int result5 = countRoots(k);
        System.out.println(result5);
        
        // Task 6
        String[][] salesData1 = {
            {"Apple", "Shop1", "Shop2", "Shop3", "Shop4"},
            {"Banana", "Shop2", "Shop3", "Shop4"},
            {"Orange", "Shop1", "Shop3", "Shop4"},
            {"Pear", "Shop2", "Shop4"}
        };
        List<String> result6 = findCommonProducts(salesData1);
        System.out.println(result6); 

        // Task 7
        boolean result7 = validSplit("apple eagle egg goat");
        System.out.println(result7);

        // Task 8
        int[] input8 = {1, 2, 3, 4, 5};
        boolean result8 = waweForm(input8);
        System.out.println(result8);

        // Task 9
        int result9 = commonVovel("Actions speak louder than words.");
        System.out.println(result9);

        // Task 10
        int[][] input10 = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {5, 5, 5, 5, 5},
            {7, 4, 3, 14, 2},
            {1, 0, 11, 10, 1}
        };
        dataScience(input10);
        for (int[] row : input10) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static String stringTransform(String input) {
        StringBuilder replaced = new StringBuilder();
        char prevChar = '\0'; // Инициализируем prevChar пустым символом.

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            // Проверяем, является ли текущий символ таким же, как предыдущий символ.
            if (currentChar == prevChar) {
                replaced.append("Double").append(currentChar);
            } else {
                replaced.append(currentChar);
            }

            // Обновляем prevChar на текущий символ для следующей итерации.
            prevChar = currentChar;
        }

        return replaced.toString();
    }
    
    
    public static String replaceVowels(String input) {
        String vowels = "aeiouAEIOU"; // List of vowels
        StringBuilder replaced = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (vowels.contains(String.valueOf(character))) {
                replaced.append('*');
            } else {
                replaced.append(character);
            }
        }

        return replaced.toString();
    }

    public static boolean doesBlockFit(int a, int b, int c, int w, int h) {
        // Check if the block can fit in either orientation.
        if ((a <= w && b <= h) || (a <= h && b <= w)) {
            return true;
        }
        return false;
    }


    public static boolean numCheck(int number) {
        int sumOfSquares = 0;
        int digit;

        // Вычисляем сумму квадратов цифр числа
        while (number != 0) {
            digit = number % 10;
            sumOfSquares += digit * digit;
            number /= 10;
        }

        // Проверяем четность числа и суммы квадратов
        return (sumOfSquares % 2 == 0) == (number % 2 == 0);
    }


    public static int countRoots(int[] coefficients) {
        int a = coefficients[0];
        int b = coefficients[1];
        int c = coefficients[2];

        int discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double sqrt = Math.sqrt(discriminant);
            if (sqrt % 1 == 0) {
                int count = 0;
                if (((-b+sqrt)/2/a)%1 == 0) {
                    count += 1;
                }
                if (((-b-sqrt)/2*a)%1 == 0) {
                    count += 1;
                }
                return count;
            }
            return 0;
        } else if (discriminant == 0) {
            if (-b/2/a % 1 == 0) {
            return 1;
            }
            return 0;
        } else {
            return 0;
        }
    }



    public static List<String> findCommonProducts(String[][] salesData) {
        // Создаем множество для каждого магазина
        Map<String, Set<String>> storeProducts = new HashMap<>();

        for (int i = 0; i < salesData.length; i++) {
            String[] sale = salesData[i];
            String productName = sale[0];

            for (int j = 1; j < sale.length; j++) {
                String storeName = sale[j];
                
                // Добавляем товар в множество магазина
                storeProducts.computeIfAbsent(storeName, k -> new HashSet<>()).add(productName);
            }
        }

        // Инициализируем результат множеством первого магазина
        Set<String> commonProducts = new HashSet<>(storeProducts.get(salesData[0][1]));

        // Находим пересечение товаров во всех магазинах
        for (Set<String> products : storeProducts.values()) {
            commonProducts.retainAll(products);
        }

        return new ArrayList<>(commonProducts);
    }


    public static boolean validSplit(String sentence) {
        return validSplitHelper(sentence.split(" "), 0);
    }

    // Рекурсивная функция для проверки разбиения предложения
    public static boolean validSplitHelper(String[] words, int index) {
        if (index == words.length - 1) {
            // Достигнут конец предложения
            return true;
        }

        String currentWord = words[index];
        String nextWordStart = words[index + 1].substring(0, 1);

        if (currentWord.endsWith(nextWordStart)) {
            // Проверяем следующее слово
            return validSplitHelper(words, index + 1);
        } else {
            // Не можем продолжить разбиение
            return false;
        }
    }

    public static boolean waweForm(int[] arr) {
        int n = arr.length;

        // Проверяем условие чередования
        for (int i = 0; i < n - 1; i++) {
            if (i % 2 == 0) {
                // Текущий индекс четный, ожидаем убывание
                if (arr[i] < arr[i + 1]) {
                    return false;
                }
            } else {
                // Текущий индекс нечетный, ожидаем возрастание
                if (arr[i] > arr[i + 1]) {
                    return false;
                }
            }
        }

        return true;
    }


    public static char commonVovel(String sentence) {
        // Приводим предложение к нижнему регистру
        sentence = sentence.toLowerCase();

        // Множество гласных
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        // Карта для подсчета количества встреч гласных
        Map<Character, Integer> vowelCount = new HashMap<>();

        // Проходимся по каждому символу в предложении
        for (char c : sentence.toCharArray()) {
            // Проверяем, является ли символ гласной
            if (vowels.contains(c)) {
                vowelCount.put(c, vowelCount.getOrDefault(c, 0) + 1);
            }
        }

        // Находим гласную с наибольшим количеством встреч
        char mostFrequentVowel = ' ';
        int maxCount = 0;

        for (Map.Entry<Character, Integer> entry : vowelCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentVowel = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostFrequentVowel;
    }


    public static void dataScience(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        // Массив для хранения суммы элементов каждого столбца
        int[] columnSums = new int[m];
        
        // Суммируем элементы каждого столбца
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    columnSums[i] += matrix[j][i];
                }
            }
        }
        
        // Вычисляем среднее арифметическое для каждого n-го элемента
        for (int i = 0; i < n; i++) {
            int avg = columnSums[i] / (n-1);
            matrix[i][i] = avg;
        }
    }
}