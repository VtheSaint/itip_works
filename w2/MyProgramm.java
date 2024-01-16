public class MyProgramm {
public static void main(String[] args) {
    //проверка задания 1
    System.out.println(task1_converter(8));
    //проверка задания 2
    System.out.println(task2_count_callories(24, 2));
    //проверка задания 3
    System.out.println(task3_count_products(5, 0, 2));
    //проверка задания 4
    System.out.println(task4_which_type(5, 7, 8));
    //проверка задания 5
    System.out.println(task5_max(5, 32));
    //проверка задания 6
    System.out.println(task6_counter(45, 2, 3));
    //проверка задания 7
    System.out.println(task7_factorial(6));
    //проверка задания 8
    System.out.println(task8_find_NOD(52, 8));
    //проверка задания 9
    System.out.println(task9_count_sum(24, 950));
    //проверка задания 10
    System.out.println(task10_count_tables(31, 20));
}

public static float task1_converter(int galoons) {
    float answer = (float) galoons;
    answer *= 3.785;
    return answer;
}

public static int task2_count_callories(int time, int intens) {
    return time * intens;
}

public static int task3_count_products(int a20, int a50, int a100) {
    return a20 * 20 + a50 * 50 + a100 * 100;
}

public static String task4_which_type(int a, int b, int c) {
    int max = a > b ? a : b;
    max = max > c ? max : c;

    if (a == b && a == c){
    return "isosceles";
    }
    if (max >= a + b + c - max) {
    return "not a triangle";
    }
    if (a == b || a == c || b == c) {
    return "equilateral";
    }
    return "different-sided";
}

public static int task5_max(int a, int b) {
    return a > b ? a : b;
}

public static int task6_counter(float n2, float w, float h) {
    return (int) (n2 / w / h / 2.0);
}

public static int task7_factorial(int a) {
    if (a == 1) {
    return 1;
    }
    return a * task7_factorial(a - 1);
}

public static int task8_find_NOD(int a, int b) {
    while (a > 0 && b > 0) {
    if (a > b) {
    a %= b;
    }
    else {
    b %= a;
    }
    }
    return a = b;
}

public static float task9_count_sum(int n, int cost) {
    float answer = (float) n * cost;
    answer *= 0.85;
    return answer;
}

public static int task10_count_tables(int stud, int tab) {
    stud += stud % 2;
    if (stud > tab * 2){
    return stud / 2 - tab;
    }
    return 0;
    }
}
