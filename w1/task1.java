import java.security.SecureRandom;
import java.util.Arrays;

public class task1 {
    public static void main(String[] args) {
        System.out.println(getInitials("Ryan Gosling"));
        System.out.println(hasDuplicateCharacters("buff"));
        int [] initial = {44, 32, 86, 19};
        System.out.println(differenceEvenOdd(initial));
        int [] initial2 = {1, 2, 3, 4, 5};
        System.out.println(equaltoAvg(initial2));
        int [] initial3 = {1,2,3};
        int[] result = indexMult(initial3);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }        
        System.out.println("");
        System.out.println(calculateTribonacci(11));
        System.out.println(generateQuasiHash(40));
        System.out.println(findHelp("Hello, i'm under the water, please Help me!"));
        System.out.println(areAnagrams("listen", "silent"));
    }

    public static boolean hasDuplicateCharacters(String str) {
        // Создаем массив для хранения флагов, соответствующих символам ASCII
        boolean[] charSet = new boolean[256];

        for (int i = 0; i < str.length(); i++) {
            int charValue = str.charAt(i);
            // Если флаг уже установлен, значит, символ встречается второй раз
            if (charSet[charValue]) {
                return true; // Есть повторяющиеся символы
            }

            // Устанавливаем флаг для текущего символа
            charSet[charValue] = true;
        }

        return false; // Нет повторяющихся символов
    }

    public static String getInitials(String fullName) {
        StringBuilder initials = new StringBuilder();

        String[] nameParts = fullName.split(" ");
        for (String namePart : nameParts) {
            if (!namePart.isEmpty()) {
                initials.append(namePart.charAt(0));
            }
        }

        return initials.toString();
    }

    public static int differenceEvenOdd(int[] arr) {
        int evenSum = 0;
        int oddSum = 0;

        for (int num : arr) {
            if (num % 2 == 0) {
                evenSum += num; // Если число четное, добавляем к сумме четных
            } else {
                oddSum += num; // Если число нечетное, добавляем к сумме нечетных
            }
        }

        return evenSum - oddSum; 
    }


    public static boolean equaltoAvg(int[] arr) {
        if (arr.length == 0) {
            return false; // Если массив пуст, вернуть false
        }

        int sum = 0;
        for (int num : arr) {
            sum += num; // Находим сумму всех элементов массива
        }

        double average = (double) sum / arr.length; // Находим среднее арифметическое

        for (int num : arr) {
            if (num == average) {
                return true; // Если есть элемент, равный среднему, вернуть true
            }
        }

        return false; // В противном случае вернуть false
    }


    public static int[] indexMult(int[] arr) {
        int[] result = new int[arr.length]; // Создаем новый массив той же длины
        
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i] * i; // Умножаем число на его индекс и сохраняем в новом массиве
        }
        
        return result;
    }


    public static int calculateTribonacci(int n) {
        if (n == 0 || n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        }
        
        int[] tribonacci = new int[n + 1];
        tribonacci[0] = 0;
        tribonacci[1] = 0;
        tribonacci[2] = 1;
        
        for (int i = 3; i <= n; i++) {
            tribonacci[i] = tribonacci[i - 1] + tribonacci[i - 2] + tribonacci[i - 3];
        }
        
        return tribonacci[n-1];
    }


    public static String generateQuasiHash(int length) {
        String characters = "abcdef0123456789"; // Символы для квази-хэша
        SecureRandom random = new SecureRandom();
        StringBuilder quasiHash = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            quasiHash.append(characters.charAt(randomIndex));
        }

        return quasiHash.toString();
    }
    
    
    public static String findHelp(String transcript) {
        String keyword = "help";

        // Преобразуем строку и ключевое слово в нижний регистр для сравнения
        String transcriptLower = transcript.toLowerCase();
        keyword = keyword.toLowerCase();

        if (transcriptLower.contains(keyword)) {
            return "Вызов сотрудника";
        } else {
            return "Продолжайте ожидание";
        }
    }


    public static boolean areAnagrams(String str1, String str2) {
        // Удаляем пробелы и переводим в нижний регистр
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // Если длины строк разные, они не могут быть анаграммами
        if (str1.length() != str2.length()) {
            return false;
        }

        // Преобразуем строки в массивы символов
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();

        // Сортируем массивы символов
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        // Сравниваем отсортированные массивы
        return Arrays.equals(charArray1, charArray2);
    }
}
