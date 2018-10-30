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
 */
public class RegularExpressionMatching {


    public static void main(String[] args) {
        RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();
        System.out.println(regularExpressionMatching.isMatch("aa", "a"));
    }


    public boolean isMatch(String s, String p) {

        char[] sChars = s.toCharArray();

        char[] pChars = p.toCharArray();

        int patternPosition = 0;
        int sPosition = 0;

        for (int i = 0; i < pChars.length; i++) {
            if (pChars[i] == '.') { //'.'表示任何字符
                patternPosition++;
                sPosition++;
                if (sPosition == sChars.length - 1) {
                    break;
                }
                continue;
            }

            if (i + 1 < pChars.length && pChars[i + 1] == '*') { // 'x*' 表示可以x可以重复0-n次
                final int start = sPosition;
                for (int j = start; j < sChars.length; j++) {
                    if (sChars[j] != pChars[i]) {
                        patternPosition += 2;
                        i = patternPosition - 1;
                        break;
                    }
                    if (sPosition + 1 > sChars.length - 1) {
                        sPosition = sChars.length - 1;
                    } else {
                        sPosition++;
                    }
                }
                if (sPosition == sChars.length - 1) {
                    break;
                }
                continue;
            }

            if (sChars[sPosition] != pChars[patternPosition]) {
                return false;
            } else {
                if (sPosition + 1 > sChars.length - 1) {
                    sPosition = sChars.length - 1;
                } else {
                    sPosition++;
                }
            }
        }

        return sPosition == sChars.length - 1;
    }


}
