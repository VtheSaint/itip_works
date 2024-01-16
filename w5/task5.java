package w5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Date;

public class task5 {
    public static void main(String[] args) {
        
        boolean result1 = sameLetterPattern("ABAB", "CDCD");
        System.out.println(result1);        
        //spiderVsFly("H3", "E2") ➞ "H3-H2-H1-A0-E1-E2"
//spiderVsFly("A4", "B2") ➞ "A4-A3-A2-B2"
//spiderVsFly("A4", "C2")
        //
        String result2 = spiderVsFly("A4", "C2");
        System.out.println(result2);        
        String resul = spiderVsFly("H3", "E2");
        System.out.println(resul);        
        String res = spiderVsFly("A4", "B2");
        System.out.println(res);

        int result3 = digitsCount(6666);
        System.out.println(result3); 

        int result4 = totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed");
        System.out.println(result4);

        String result5 = takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"});
        System.out.println(result5);

        List<int[]> result6 = sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7});
        for (int i = 0; i < result6.size(); i++) {
            int[] pair = result6.get(i);
            System.out.print("[" + pair[0] + "," + pair[1] + "]");
        }
        System.out.print("\n");

        String result7 = caesarCipher("encode", "hello world", 3);
        System.out.println(result7);


        int result8 = setSetup(5, 3);
        System.out.println(result8);
        

        boolean result10 = isNew(321);
        System.out.println(result10);
        System.out.println(isNew(30));
        System.out.println(isNew(3));
        System.out.println(isNew(123));

    }

    public static boolean sameLetterPattern(String pattern, String pattern2) {
        int [] pattern_mask = createPattern(pattern);
        int [] pattern_mask2 = createPattern(pattern2);
        if (pattern_mask.length != pattern_mask2.length) {
            return false;
        }
    
        // Сравнение элементов по индексу
        for (int i = 0; i < pattern_mask.length; i++) {
            if (pattern_mask[i] != pattern_mask2[i]) {
                return false;
            }
        }
    
        return true;
    }
    public static int[] createPattern(String pattern) {
        HashMap<Character, Integer> map = new HashMap<>();
        int current_index = 0;
        int[] pattern_mask = new int[pattern.length()];
        for (int i = 0; i < pattern.length(); i ++) {
            if (map.containsKey(pattern.charAt(i))) {
                pattern_mask[i] = map.get(pattern.charAt(i));
            }
            else {
                pattern_mask[i] = current_index;
                map.put(pattern.charAt(i), current_index);
                current_index += 1;
            }

        }
        return pattern_mask;
    }

    
    public static String spiderVsFly(String start, String end) {
        StringBuilder result = new StringBuilder();
    
        int start_num = Integer.parseInt(start.substring(1));
        char start_char = start.charAt(0);
        char end_char = end.charAt(0);
        int end_num = Integer.parseInt(end.substring(1));
    
        while (start_num != end_num ) {
            result.append(start_char).append(start_num).append('-');
    
            if (start_num > end_num) {
                start_num--;
            } else if (start_num < end_num) {
                start_num++;
            }
    

        }

        int check = end_char - start_char;
        check = check > 0 ? check : -check;
        if ( check + 1 ==  2 * end_num) {
            for (int i = end_num; i >= 0; i--) {
                result.append(start_char).append(i).append('-');
            }
            for (int i = 1; i < end_num; i++) {
                 result.append(end_char).append(i).append('-');
            }
        }
        else {
            while (start_char != end_char) {
                result.append(start_char).append(start_num).append('-');
                if (start_char > end_char) {
                    start_char--;
                } else if (start_char < end_char) {
                    start_char++;
                }
            }
        }

    
        result.append(end_char).append(end_num);


        return result.toString();
    }


    public static int digitsCount(int num) {
        if (num < 10) {
            return 1;
        }
        else {
            return 1 + digitsCount(num/10);
        }
    }

    public static int totalPoints(String[] guessedWords, String scrambledWord) {
        Map<Character, Integer> scrambledWordCount = new HashMap<>();
        for (char letter : scrambledWord.toCharArray()) {
            scrambledWordCount.put(letter, scrambledWordCount.getOrDefault(letter, 0) + 1);
        }

        int totalPoints = 0;

        for (String word : guessedWords) {
            Map<Character, Integer> wordCount = new HashMap<>();
            for (char letter : word.toCharArray()) {
                wordCount.put(letter, wordCount.getOrDefault(letter, 0) + 1);
            }

            boolean validWord = true;
            int points = 0;

            for (Map.Entry<Character, Integer> entry : wordCount.entrySet()) {
                char letter = entry.getKey();
                int count = entry.getValue();

                if (!scrambledWordCount.containsKey(letter) || wordCount.get(letter) > scrambledWordCount.get(letter)) {
                    validWord = false;
                    break;
                } else {
                    scrambledWordCount.put(letter, scrambledWordCount.get(letter) - count);
                }
            }

            if (validWord) {
                int wordLength = word.length();
                if (wordLength == 3) {
                    points = 1;
                } else if (wordLength == 4) {
                    points = 2;
                } else if (wordLength == 5) {
                    points = 3;
                } else if (wordLength == 6) {
                    points = 54;  // 4 очка за слово и 50 бонусных очков за расшифровку
                }
                totalPoints += points;
            }
        }

        return totalPoints;
    }


    public static String takeDownAverage(String[] grades) {
        int totalPoints = 0;
        for (String grade : grades) {
            totalPoints += Integer.parseInt(grade.replaceAll("%", ""));
        }
        
        int classSize = grades.length;
        int currentAverage = (totalPoints) / classSize; // Вычисляем текущий средний балл
        int targetAverage = currentAverage - 5; // Целевой средний балл с учетом снижения на 5%
        int decrease = targetAverage*(classSize + 1) - totalPoints; // На сколько нужно снизить средний балл
        
        return decrease + "%";
    }



    public static List<int[]> sumsUp(int[] numbers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();
        for (int number : numbers) {
            if (map.containsKey(8 - number)) {
                int [] pair = {number, 8 - number};
                result.add(pair);
            } else {
                map.put(number, 0);
            }
        }
        return result;
    }


    public static String caesarCipher(String mode, String message, int shift) {
        shift = mode == "encode" ? shift : -shift;
        StringBuilder result = new StringBuilder();

        for (char character : message.toCharArray()) {
            if (Character.isUpperCase(character)) {
                char shiftedChar = (char) ('A' + (character - 'A' + shift) % 26);
                result.append(shiftedChar);
            } else if (Character.isLowerCase(character)) {
                char shiftedChar = (char) ('a' + (character - 'a' + shift) % 26);
                result.append(shiftedChar);
            } else {
                result.append(character);
            }
        }

        return result.toString();

    }


    public static int setSetup(int n, int k) {
        if (n < k) {
            // Недопустимые входные параметры
            return 0;
        }

        if (k == 0) {
            // Базовый случай: 0 элементов размещены
            return 1;
        }

        // Рекурсивно вычисляем количество размещений
        return n * setSetup(n - 1, k - 1);
    }

        public static String timeDifference(String cityA, String timestampA, String cityB) {
        // Словарь со смещениями для каждого города
        HashMap<String, Integer> cityOffsets = new HashMap<>();
        cityOffsets.put("Los Angeles", -8);
        cityOffsets.put("New York", -5);
        cityOffsets.put("Caracas", -4);
        cityOffsets.put("Buenos Aires", -3);
        cityOffsets.put("London", 0);
        cityOffsets.put("Rome", 1);
        cityOffsets.put("Moscow", 3);
        cityOffsets.put("Tehran", 3);
        cityOffsets.put("New Delhi", 5);
        cityOffsets.put("Beijing", 8);
        cityOffsets.put("Canberra", 10);

        // Парсинг времени в cityA
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date dateA;
        try {
            dateA = dateFormat.parse(timestampA);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        // Вычисление смещения для города cityA
        int offsetA = cityOffsets.get(cityA);

        // Вычисление смещения для города cityB
        int offsetB = cityOffsets.get(cityB);

        // Применение смещений и получение нового времени в cityB
        long timeInCityB = dateA.getTime() + (offsetB - offsetA) * 3600 * 1000;
        Date dateB = new Date(timeInCityB);

        // Форматирование результата
        SimpleDateFormat resultFormat = new SimpleDateFormat("yyyy-M-d HH:mm");
        resultFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return resultFormat.format(dateB);
    }

    public static boolean isNew(int num2) {
        int num = reverse(num2);
        int max_value = 0;
        while (num > 0) {
            if (num % 10 == 0) {
                return false;
            }
            if (num % 10 < max_value) {
                return false;
            }
            max_value = num % 10;
            num /= 10;
        }
        return true;
    }

    public static int reverse(int num) {
        int d = 1;
        while (d < num)  {
            d*=10;
        }

        int result = 0;
        while (num > 0) {
            d /= 10;
            result += (num % 10) * d;
            num /= 10;
        }
        return result;
    }
}