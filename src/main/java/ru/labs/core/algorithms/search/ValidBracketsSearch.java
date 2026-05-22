package ru.labs.core.algorithms.search;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidBracketsSearch {
    public static boolean isValid(String brackets) {
        if (brackets.length() % 2 != 0) {
            System.out.println(brackets.length());
            return false;
        } else if (
                !brackets.contains("(") &&
                !brackets.contains("{") &&
                !brackets.contains("[") &&
                !brackets.contains(")") &&
                !brackets.contains("}") &&
                !brackets.contains("]")
        ) {
            throw new IllegalArgumentException("Строка не содержит скобок");
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char bracket : brackets.toCharArray()) {
            if (bracket == '(' || bracket == '{' || bracket == '[') {
                stack.push(bracket);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if (bracket == ')' && top != '(') {
                    return false;
                }
                if (bracket == '}' && top != '{') {
                    return false;
                }
                if (bracket == ']' && top != '[') {
                    return false;
                }
            }

        }

        return stack.isEmpty();
    }
}
