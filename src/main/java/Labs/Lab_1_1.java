package Labs;

import java.util.ArrayDeque;
import java.util.Deque;

public class Lab_1_1 {
    public static void start() {
        Task1_1 solver = new Task1_1();

        System.out.println("Test 1 ([]): " + solver.isValid("[]"));         // true
        System.out.println("Test 2 ([](){}): " + solver.isValid("[](){}")); // true
        System.out.println("Test 3 ([({})]): " + solver.isValid("[({})]")); // true
        System.out.println("Test 4 ({]): " + solver.isValid("{]"));         // false
        System.out.println("Test 5 (({)}): " + solver.isValid("({)}"));     // false
        System.out.println("Test 6 ( ): " + solver.isValid(""));            // true
        System.out.println("Test 7 ((: " + solver.isValid("(("));           // false
    }

    public static class Task1_1 {
        public boolean isValid(String s) {
            // Если длина нечетная, баланса быть не может
            if (s.length() % 2 != 0) {
                return false;
            }

            // Используем ArrayDeque как стек
            Deque<Character> stack = new ArrayDeque<>();

            for (char c : s.toCharArray()) {
                // Если скобка открывающая - кладем в стек
                if (c == '(' || c == '{' || c == '[') {
                    stack.push(c);
                }
                // Если закрывающая
                else {
                    // Если стек пуст, значит закрывающая пришла раньше открывающей -> false
                    if (stack.isEmpty()) {
                        return false;
                    }

                    char top = stack.pop(); // Достаем последнюю открытую

                    // Проверяем соответствие пар
                    if (c == ')' && top != '(') return false;
                    if (c == '}' && top != '{') return false;
                    if (c == ']' && top != '[') return false;
                }
            }

            // В конце стек должен быть пуст (все открытые закрылись)
            return stack.isEmpty();
        }
    }
}