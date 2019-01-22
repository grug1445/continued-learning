package com.grug.leetcode;

/**
 * Created by feichen on 2018/12/26.
 */
public class ValidParentheses {

    public static void main(String[] args) {

        System.out.println(isValid(""));

    }

    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        int size = chars.length;
        if ((size & 1) != 0) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (chars[i] == '0') {
                continue;
            }
            char c = chars[i];
            if (c != '(' && c != ')' && c != '{' && c != '}' && c != '[' && c != ']' && c != '0') {
                return false;
            }
            char C = chars[size - 1 - i];
            char c1 = chars[i + 1];
            if (c == '(') {
                if (C != ')' && c1 != ')') {
                    return false;
                }
            }
            if (c == '{') {
                if (C != '}' && c1 != '}') {
                    return false;
                }
            }
            if (c == '[') {
                if (C != ']' && c1 != ']') {
                    return false;
                }
            }
            chars[i] = '0';
            chars[size - 1 - i] = '0';
        }
        return true;
    }
}
