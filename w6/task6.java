package w6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class task6 {
    public static void main(String[] args) {
        // String result1 = hiddenAnagram(new String[]{"D e b90it->?$ (c)a r...d,,#~", "baD credit"});
        String result1 = hiddenAnagram(new String[]{"My world evolves in a beautiful space called Tesh.","sworn love lived"});
        System.out.println(result1);

        String[] result2 = collect("intercontinentalisationalism", 6);
        for ( String str : result2 ) {
            System.out.print(str + " ");
        }
        System.out.println("  ");

        String result3 = nicoCipher("myworldevolvesinhers", "tesh");
        System.out.println(result3);

        int [] result4 = twoProducts(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45);
        System.out.println(result4[0] + " " + result4[1]);

        int[] result5 = isExact(24);
        if (result5.length == 0) {
            System.out.println(result5);
        } else {
            System.out.println(result5[0] + " " + result5[1]);
        }

        // String result6 = fraction("0.(6)");
        String result6 = fraction("0.19(2367)");
        System.out.println(result6);

        String result7 = pilishString("CANIMAKEAGUESSNOW");
        System.out.println(result7);

        double result8 = generateNonconsecutive("3 + 5 * (2 - 6)");
        System.out.println(result8);

        // String result9 = isValid("aabbcd");
        String result9 = isValid("adcadca");
        System.out.println(result9);

        // String result10 = findLCS("abcd", "bd");
        String result10 = findLCS("aggtab", "gxtxamb");
        System.out.println(result10);

    }


    public static String hiddenAnagram(String[] args) {
        StringBuilder first_string = new StringBuilder();
        for (int i = 0; i < args[0].length(); i++) {
            char c = args[0].charAt(i);
            if (Character.isAlphabetic(c)) {
                first_string.append(Character.toLowerCase(c));
            }
        }
        StringBuilder second_string = new StringBuilder();
        for (int i = 0; i < args[1].length(); i++) {
            char c = args[1].charAt(i);
            if (Character.isAlphabetic(c)) {
                second_string.append(Character.toLowerCase(c));
            }
        }

        for (int i = 0; i <= first_string.length() - second_string.length(); i++) {
            if (
                isAnagram(first_string.toString().substring(i, i + second_string.length()), second_string.toString())
            ) {
                return first_string.toString().substring(i, i + second_string.length());
            }
        }
        return "notfound";
        
    } 

    private static boolean isAnagram(String first, String second) {
        HashMap<Character, Integer> f_map = new HashMap<>();
        HashMap<Character, Integer> s_map = new HashMap<>();
        for (char litter : first.toCharArray()) {
            if (f_map.containsKey(litter)) {
                f_map.put(litter, f_map.get(litter) + 1);
            }
            else {
                f_map.put(litter, 1);
            }
        }

        for (char litter : second.toCharArray()) {
            if (s_map.containsKey(litter)) {
                s_map.put(litter, s_map.get(litter) + 1);
            }
            else {
                s_map.put(litter, 1);
            }
        }

        for (char key : f_map.keySet()) {
            if (f_map.get(key) != s_map.get(key) ) {
                return false;
            }
        }
        return true;  
    }


    public static String[] collect(String str, int num) {
        List<String> result = new ArrayList<String>();
        int i = 0;
        while (i + num < str.length()) {
            result.add(str.substring(i, i + num));
            i += num;
        }
        Collections.sort(result);
        return result.isEmpty() ? null : result.toArray(new String[result.size()]);
    }

    public static String nicoCipher(String message, String key) {
        // Шаг 1: Назначьте числа отсортированным буквам из ключа
        char[] sortedKey = key.toCharArray();
        Arrays.sort(sortedKey);

        HashMap<Character, Integer> key_map = new HashMap<>();
        HashMap<Integer, Character> inverted_key_map = new HashMap<>();
        int index = 0;
        for (char k : sortedKey) {
            key_map.put(k, index); 
            inverted_key_map.put(index, k); 
            index += 1;
        }
        HashMap<Integer, Character> message_map = new HashMap<>();
        StringBuilder result = new StringBuilder();
        for (char l : key.toCharArray()) {
            int l_index = key_map.get(l);
            message_map.put(l_index, message.charAt(l_index));
            while (l_index + key.length() < message.length()) {
                message_map.put(l_index + key.length(), message.charAt(l_index + key.length()));
                l_index += key.length();
            }
        }
        for (int i = 0; i < key.length(); i++) {
            int l_index = key_map.get(inverted_key_map.get(i));

            result.append(message.charAt(l_index));
            while (l_index + key.length() < message.length()) {
                result.append(message.charAt(l_index + key.length()));
                l_index += key.length();
            }
        }

        return result.toString();   
    }


    public static int[] twoProducts(int[] arr, int num) {
        List<Integer> pairs = new ArrayList<>();
        for (int x : arr) {
            if (num % x == 0) {
                if( pairs.contains(num/x)) {
                    return new int[] {num/x, x};
                }
                else {
                    pairs.add(x);
                }
            }
        }
        return new int[]{};
    }


    public static int[] isExact(int num) {
        int result = getFactorial(num, 1, 1);
        return result == - 1 ? new int[] {} : new int[] {num, result};
    }
    public static int getFactorial(int num, int f, int res) {
        if (res * f == num ) {
            return f;
        }
        if (res * f > num) {
            return -1;
        }
        res *= f;
        return getFactorial(num, f+1, res);
    }

    public static String fraction(String infinite_num) {
        if (infinite_num.split("\\.")[1].charAt(0) == '(') {
            return getClearFract(infinite_num);
        } else {
            return getMixedFract(infinite_num);
        }
    }

    private static String getMixedFract(String num) {
        StringBuilder b = new StringBuilder();
        for (char c : num.split("\\.")[1].toCharArray()) {
            if ( Character.isDigit(c)) {
                b.append(c);
            } else {
                break;
            }
        }
        StringBuilder p = new StringBuilder();
        for (char c : num.split("\\(")[1].toCharArray()) {
            if ( Character.isDigit(c)) {
                p.append(c);
            } else {
                break;
            }
        }

        
        int z = Integer.parseInt( num.split("\\.")[0]);
        int numerator = Integer.parseInt(b.toString() + p.toString()) - Integer.parseInt(b.toString());
        StringBuilder denominator = new StringBuilder();
        for (char c : p.toString().toCharArray()) {
            denominator.append("9");
        }
        for (char c : b.toString().toCharArray()) {
            denominator.append("0");
        }
        StringBuilder result = new StringBuilder();
        int first = numerator + Integer.parseInt(denominator.toString()) * z;
        int d = findGCD(first, Integer.parseInt(denominator.toString()));

        result.append(String.valueOf( first / d));
        result.append("/");
        result.append(String.valueOf(Integer.parseInt(denominator.toString())/d));
        return result.toString();
    }

    private static String getClearFract(String num) {

        StringBuilder p = new StringBuilder();
        for (char c : num.split("\\(")[1].toCharArray()) {
            if ( Character.isDigit(c)) {
                p.append(c);
            } else {
                break;
            }
        }
        int z = Integer.parseInt( num.split("\\.")[0]);
        
        StringBuilder denominator = new StringBuilder();
        for (char c : p.toString().toCharArray()) {
            denominator.append("9");
        }
        StringBuilder result = new StringBuilder();
        int first = Integer.parseInt(p.toString()) + Integer.parseInt(denominator.toString()) * z;
        int d = findGCD(first, Integer.parseInt(denominator.toString()));

        result.append(String.valueOf( first / d ));
        result.append("/");
        result.append(String.valueOf(Integer.parseInt(denominator.toString())/d));
        return result.toString();
    }
    private static int findGCD(int num1, int num2) {
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    public static String pilishString(String message) {
        if (message.length() == 0 ) {return message;}
        String separator_mask = "314159265358979";
        int message_index = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < separator_mask.length(); i++) {
            int separator_index = Integer.parseInt(separator_mask.substring(i, i+1));
            if (message_index + separator_index > message.length()) {
                StringBuilder suffix = new StringBuilder();
                for (int j = 0; j < separator_index + message_index - message.length(); j ++) {
                    suffix.append(message.charAt(message.length()-1));
                }
                result.append(message.substring(message_index, message.length()));
                result.append(suffix);
                break;
            }
            else { 
                result.append(message.substring(message_index, message_index + separator_index));
            }
            result.append(" ");
            message_index += separator_index;
        }
        return result.toString();
    }


    public static double generateNonconsecutive(String expression) {
        try {
            Stack<Double> numbers = new Stack<>();
            Stack<Character> operators = new Stack<>();

            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);

                if (ch == ' ')
                    continue;

                if (Character.isDigit(ch) || ch == '.') {
                    StringBuilder numBuilder = new StringBuilder();
                    while (i < expression.length() &&
                            (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                        numBuilder.append(expression.charAt(i));
                        i++;
                    }
                    i--;

                    numbers.push(Double.parseDouble(numBuilder.toString()));
                } else if (ch == '(') {
                    operators.push(ch);
                } else if (ch == ')') {
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        evaluateOperation(numbers, operators);
                    }
                    operators.pop(); // Remove the '('
                } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
                        evaluateOperation(numbers, operators);
                    }
                    operators.push(ch);
                }
            }

            while (!operators.isEmpty()) {
                evaluateOperation(numbers, operators);
            }

            if (numbers.size() == 1 && operators.isEmpty()) {
                return numbers.pop();
            } else {
                throw new IllegalArgumentException("Invalid expression");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN; // Return NaN for error cases
        }
    }

    private static void evaluateOperation(Stack<Double> numbers, Stack<Character> operators) {
        double num2 = numbers.pop();
        double num1 = numbers.pop();
        char operator = operators.pop();

        double result = performOperation(num1, num2, operator);
        numbers.push(result);
    }

    private static boolean hasPrecedence(char op2, char op1) {
        return (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-');
    }

    private static double performOperation(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

        

    public static String isValid(String input) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : input.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c,1);
            }
        }
        Collection<Integer> result = map.values();
        int sum_without_drop = 0;
        for (int i = 0; i < result.size(); i++) {
            sum_without_drop += (int) result.toArray()[i];
        }
        if ( (int) result.toArray()[0] * result.size() == sum_without_drop) {
                return "YES";
        }

        for (int i = 0; i < result.size(); i ++ ) {
            int sum = 0;
            for (int j = 0; j < result.size(); j ++ ) {
                if ( i == j ) {continue;}
                sum += (int) result.toArray()[j];
            }
            if ( (int) result.toArray()[i+1] * (result.size() - 1) == sum) {
                return "YES";
            }
        }
        return "NO";
    }

    public static String findLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Создаем двумерный массив для хранения решения подзадач
        int[][] dp = new int[m + 1][n + 1];

        // Заполняем массив dp с использованием динамического программирования
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Теперь восстановим LCS из массива dp
        int lcsLength = dp[m][n];
        char[] lcsChars = new char[lcsLength];
        int i = m, j = n, index = lcsLength - 1;

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcsChars[index] = s1.charAt(i - 1);
                i--;
                j--;
                index--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return new String(lcsChars);
    }

}
