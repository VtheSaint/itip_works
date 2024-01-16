import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class task4 {
    
    public static void main(String[] args) {
        // Task 1
        String result1 = nonRepeatable("abracadabra");
        System.out.println(result1);

        // Task 2
        List<String> result2 = generateBrackets(2);
        System.out.println(result2);

        // Task 3
        List<String> result3 = binarySystem(4);
        System.out.println(result3);

        // Task 4
        String result4 = alphabeticRow("edccb");
        System.out.println(result4);

        // Task 5
        String result5 = countAndSort("vvvvaajaaaaa");
        System.out.println(result5);

        // Task 6
        int result6 = convertToNum("five hundred sixty seven");
        System.out.println(result6);

        // Task 7
        String result7 = uniqueSubstring("77897898");
        System.out.println(result7);

        // Task 8
        int[][] grid1 = {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        };
        int result8 = shortestWay(grid1);
        System.out.println(result8);

        // Task 9
         String result9 = numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat");
        System.out.println(result9);

        // Task 10
        int result10 = switchNums(519, 723);
        System.out.println(result10);

    }




    public static String nonRepeatable(String str) {
        return nonRepeatableHelper(str, 0, "");
    }

    public static String nonRepeatableHelper(String str, int index, String result) {
        if (index == str.length()) {
            return result;
        }

        char currentChar = str.charAt(index);

        if (!result.contains(String.valueOf(currentChar))) {
            result += currentChar;
        }

        return nonRepeatableHelper(str, index + 1, result);
    }

    public static List<String> generateBrackets(int n) {
        List<String> result = new ArrayList<>();
        generateBracketsHelper(n, n, "", result);
        return result;
    }

    public static void generateBracketsHelper(int left, int right, String current, List<String> result) {
        // Базовый случай: если нет открывающих и закрывающих скобок, добавляем текущую комбинацию к результату
        if (left == 0 && right == 0) {
            result.add(current);
            return;
        }

        // Рекурсивно добавляем открывающие и закрывающие скобки, если это возможно
        if (left > 0) {
            generateBracketsHelper(left - 1, right, current + "(", result);
        }
        if (right > left) {
            generateBracketsHelper(left, right - 1, current + ")", result);
        }
    }


    public static List<String> binarySystem(int n) {
        List<String> arr = generateBinaryNumbers(n);
        List<String> result = new ArrayList<>();
        for (String number : arr) {
            int prev_bit = number.charAt(0);
            boolean flag = true;
            for (int i = 1; i < number.length(); i++) {
                if (number.charAt(i) == prev_bit) {
                    flag = false;
                    break;
                }
                prev_bit = number.charAt(i);
            }
            if (flag) {
                result.add(number);
            }
        }
        return result;
    }

    public static List<String> generateBinaryNumbers(int n) {
        List<String> result = new ArrayList<>();
        generateBinaryNumbersHelper("", n, result);
        return result;
    }

    private static void generateBinaryNumbersHelper(String current, int n, List<String> result) {
        if (n == 0) {
            result.add(current);
        } else {
            generateBinaryNumbersHelper(current + "0", n - 1, result);
            generateBinaryNumbersHelper(current + "1", n - 1, result);
        }
    }


    public static String alphabeticRow(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        String currentRow = Character.toString(str.charAt(0)); // Начальный символ
        String longestRow = currentRow; // Начальное значение самой длинной последовательности

        for (int i = 1; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            char previousChar = str.charAt(i - 1);

            if (currentChar == previousChar + 1 ) {
                currentRow += currentChar;
            } else {
                if (currentRow.length() > longestRow.length()) {
                    longestRow = currentRow;
                }
                currentRow = Character.toString(currentChar); // Начать новую последовательность
            }
        }

        currentRow = Character.toString(str.charAt(0)); // Начальный символ
        if (currentRow.length() > longestRow.length()) {
            longestRow = currentRow; // Проверить последнюю последовательность
        }
        for (int i = 1; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            char previousChar = str.charAt(i - 1);

            if (currentChar == previousChar - 1 ) {
                currentRow += currentChar;
            } else {
                if (currentRow.length() > longestRow.length()) {
                    longestRow = currentRow;
                }
                currentRow = Character.toString(currentChar); // Начать новую последовательность
            }
        }

        if (currentRow.length() > longestRow.length()) {
            longestRow = currentRow; // Проверить последнюю последовательность
        }

        return longestRow;
    }


    public static String countAndSort(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        List<String> patterns = new ArrayList<>();
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            char previousChar = str.charAt(i - 1);

            if (currentChar == previousChar) {
                count++;
            } else {
                patterns.add(previousChar + Integer.toString(count));
                count = 1;
            }
        }

        // Добавляем последний символ и его количество
        patterns.add(str.charAt(str.length() - 1) + Integer.toString(count));

        // Сортируем по длине буквенного паттерна
        Collections.sort(patterns, (a, b) -> Integer.compare(Integer.parseInt(a.substring(1)), Integer.parseInt(b.substring(1))));

        // Собираем отсортированную строку
        StringBuilder sortedStr = new StringBuilder();
        for (String pattern : patterns) {
            sortedStr.append(pattern);
        }

        return sortedStr.toString();
    }


        public static int convertToNum(String str) {
        // Создаем словарь для сопоставления строковых представлений чисел с их значениями
        Map<String, Integer> numDict = new HashMap<>();
        numDict.put("zero", 0);
        numDict.put("one", 1);
        numDict.put("two", 2);
        numDict.put("three", 3);
        numDict.put("four", 4);
        numDict.put("five", 5);
        numDict.put("six", 6);
        numDict.put("seven", 7);
        numDict.put("eight", 8);
        numDict.put("nine", 9);
        numDict.put("ten", 10);
        numDict.put("eleven", 11);
        numDict.put("twelve", 12);
        numDict.put("thirteen", 13);
        numDict.put("fourteen", 14);
        numDict.put("fifteen", 15);
        numDict.put("sixteen", 16);
        numDict.put("seventeen", 17);
        numDict.put("eighteen", 18);
        numDict.put("nineteen", 19);
        numDict.put("twenty", 20);
        numDict.put("thirty", 30);
        numDict.put("forty", 40);
        numDict.put("fifty", 50);
        numDict.put("sixty", 60);
        numDict.put("seventy", 70);
        numDict.put("eighty", 80);
        numDict.put("ninety", 90);
        numDict.put("hundred", 100);

        String[] words = str.split(" ");
        int result = 0;
        int currentNumber = 0;

        for (String word : words) {
            if (numDict.containsKey(word)) {
                currentNumber += numDict.get(word);
            } else if (word.equals("hundred")) {
                currentNumber *= 100;
            } else {
                result += currentNumber;
                currentNumber = 0;
            }
        }

        return result + currentNumber;
    }


    public static String uniqueSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        int maxLength = 0; // Максимальная длина найденной подстроки
        int startIndex = 0; // Начальный индекс текущей подстроки
        int endIndex = 0; // Конечный индекс текущей подстроки

        int currentLength = 0; // Текущая длина текущей подстроки
        int currentStartIndex = 0; // Начальный индекс текущей подстроки

        // Используем HashSet для отслеживания уникальных символов
        java.util.HashSet<Character> uniqueSet = new java.util.HashSet<>();

        while (endIndex < str.length()) {
            char currentChar = str.charAt(endIndex);

            if (!uniqueSet.contains(currentChar)) {
                uniqueSet.add(currentChar);
                currentLength++;
                endIndex++;
            } else {
                // Найден дубликат, обновляем начало подстроки
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    startIndex = currentStartIndex;
                }

                // Сбрасываем текущую подстроку и начинаем с нового символа
                currentStartIndex = endIndex;
                currentLength = 0;
                uniqueSet.clear();
            }
        }

        // Проверяем длину последней подстроки
        if (currentLength > maxLength) {
            startIndex = currentStartIndex;
        }

        // Возвращаем найденную подстроку
        return str.substring(startIndex, startIndex + maxLength);
    }


    public static int shortestWay(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];

        // Инициализация верхней левой ячейки
        dp[0][0] = grid[0][0];

        // Заполнение первой строки
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        // Заполнение первого столбца
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // Заполнение остальных ячеек
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        // Возвращаем минимальную сумму пути до правой нижней ячейки
        return dp[n - 1][n - 1];
    }


    public static String numericOrder(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Разделяем входную строку на слова
        String[] words = input.split(" ");

        // Создаем массив для хранения слов в правильном порядке
        String[] orderedWords = new String[words.length];

        for (String word : words) {
            // Извлекаем число из слова
            int order = extractNumberFromWord(word);

            // Помещаем слово в соответствующую позицию в массиве
            orderedWords[order-1] = word.replaceAll("\\d", ""); // Убираем число из слова
        }

        // Собираем отсортированную строку
        StringBuilder result = new StringBuilder();
        for (String orderedWord : orderedWords) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(orderedWord);
        }

        return result.toString();
    }

    public static int extractNumberFromWord(String word) {
        int number = 0;
        StringBuilder numStr = new StringBuilder();

        for (char c : word.toCharArray()) {
            if (Character.isDigit(c)) {
                numStr.append(c);
            } else {
                continue;
            }
        }

        if (numStr.length() > 0) {
            number = Integer.parseInt(numStr.toString());
        }

        return number;
    }


    public static int switchNums(int num1, int num2) {
        // Разбиваем числа на массивы цифр
        Integer[] digits1 = getDigits(num1);
        Integer[] digits2 = getDigits(num2);

        // Сортируем массивы в порядке убывания
        Arrays.sort(digits1, Collections.reverseOrder());
        Arrays.sort(digits2, Collections.reverseOrder());

        int first_index = 0;
        int second_index = 0;
        int d = (int) Math.pow(10, digits2.length);
        int result = 0;
        // Собираем число из измененных цифр во втором числе
        for (int i = 0; i < digits2.length; i++) {
            d /= 10;
            if (digits1[first_index] >= digits2[second_index]) {
                result += digits1[first_index] * d;
                first_index += 1;
            }
            else {
                result += digits2[second_index] * d;
                second_index += 1;
            }
        }

        return result;
    }

    public static Integer[] getDigits(int num) {
        // Разбиваем число на массив цифр
        String numStr = Integer.toString(num);
        Integer[] digits = new Integer[numStr.length()];

        for (int i = 0; i < numStr.length(); i++) {
            digits[i] = Character.getNumericValue(numStr.charAt(i));
        }

        return digits;
    }
}





