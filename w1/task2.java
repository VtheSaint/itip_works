public class task2 {
    public static void main(String[] args) {
        System.out.println(replaceVowelsWithAsterisk("Hello world"));
        System.out.println(replaceDoubleLetters("Hello world"));

    }


    public static String replaceVowelsWithAsterisk(String str) {
        // Заменяем все гласные буквы на символ "*"
        return str.replaceAll("[aeiouAEIOU]", "*");
    }

    
    public static String replaceDoubleLetters(String str) {
        // Заменяем две идущие подряд буквы по шаблону "Double*"
        return str.replaceAll("([a-zA-Z])\\1", "Double$1");
    }
}
