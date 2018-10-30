package com.grug.leetcode;

/**
 * Created by feichen on 2018/5/18.
 * <p>
 * https://leetcode-cn.com/problems/regular-expression-matching/description/
 * <p>
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的元素。
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 * 未完成
 */
public class Id10RegularExpressionMatching {


    public static void main(String[] args) {
        Id10RegularExpressionMatching regularExpressionMatching = new Id10RegularExpressionMatching();

//        System.out.println(regularExpressionMatching.isMatch("aa", "a"));
//        System.out.println(regularExpressionMatching.isMatch("aa", "a*"));
//        System.out.println(regularExpressionMatching.isMatch("ab", ".*"));
//        System.out.println(regularExpressionMatching.isMatch("aab", "c*a*b"));
//        System.out.println(regularExpressionMatching.isMatch("mississippi", "mis*is*p*."));
        System.out.println(regularExpressionMatching.isMatch("bbbba", ".*a*a"));

    }


    public boolean isMatch(String s, String p) {

        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        if (sChars.length == 0 && pChars.length == 0) {
            return true;
        }
        if (sChars.length == 0 || pChars.length == 0) {
            return false;
        }

        int patternPosition = -1;

        for (int i = 0; i < sChars.length; i++) {

            patternPosition++;
            if (patternPosition > pChars.length - 1) {
                //pattern已经完成,而p不没有完
                return false;
            }

            //x*匹配0-n个x
            if (patternPosition + 1 < pChars.length && pChars[patternPosition + 1] == '*') {
                int temPosStart = patternPosition;
                patternPosition = temPosStart + 1;
                char match = pChars[temPosStart];

                int minMatchSize = 0;

                //xxxa*aa  xxxa*c*a
                if (temPosStart + 2 < pChars.length) {
                    int tempPos = temPosStart + 2;
                    for (int t = tempPos; t < pChars.length; t++) {
                        if (pChars[t] == match) {
                            minMatchSize++;
                            patternPosition = t;
                        } else {
                            break;
                        }
                    }
                }

                int accurateMatchSize = 0;


                for (int j = i; j < sChars.length; j++) {
                    //.*
                    if (match != '.' && sChars[j] != match) {
                        if (j == i) {
                            i--;
                        }
                        break;
                    } else {
                        accurateMatchSize++;
                    }
                    i = j;
                }

                if (i == sChars.length - 1 && patternPosition != pChars.length - 1) {
                    // aaa ab*a*c*a
                    for (int q = patternPosition; q < pChars.length; q++) {
                        if (patternPosition + 2 <= pChars.length - 1 && pChars[patternPosition + 2] == '*') {
                            patternPosition = patternPosition + 2;
                            q = patternPosition;
                            continue;
                        }

                        if (patternPosition + 1 <= pChars.length - 1 && pChars[patternPosition + 1] == match) {
                            minMatchSize++;
                            patternPosition++;
                            continue;
                        }
                        if (patternPosition + 1 <= pChars.length - 1 && pChars[patternPosition + 1] != match) {
                            return false;
                        }
                    }

                }
                if (accurateMatchSize < minMatchSize) {
                    return false;
                }

                continue;
            }
            if (patternPosition < pChars.length) {
                if (pChars[patternPosition] != '.' && sChars[i] != pChars[patternPosition]) {
                    return false;
                }
                if (i == sChars.length - 1 && patternPosition != pChars.length - 1) {
                    // a ab*
                    for (int q = patternPosition; q < pChars.length; q++) {
                        if (patternPosition + 2 <= pChars.length - 1 && pChars[patternPosition + 2] == '*') {
                            patternPosition = patternPosition + 2;
                            q = patternPosition;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }

        return patternPosition == pChars.length - 1;
    }


}
