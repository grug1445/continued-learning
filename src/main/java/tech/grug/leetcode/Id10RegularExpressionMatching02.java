package tech.grug.leetcode;

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
public class Id10RegularExpressionMatching02 {


    public static void main(String[] args) {
        Id10RegularExpressionMatching02 regularExpressionMatching = new Id10RegularExpressionMatching02();
        System.out.println(regularExpressionMatching.isMatch("aaaa", "ba*a"));
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

        int patternPosition = pChars.length - 1;

        for (int i = sChars.length - 1; i >= 0; i--) {
            if (patternPosition - 1 >= 0 && pChars[patternPosition] == '*') {
                char match = pChars[patternPosition - 1];

                //最少匹配个数
                int minMatchSize = 0;

                // aaaa aa*a
                if (patternPosition - 2 >= 0) {
                    for (int t = patternPosition - 2; t >= 0; t--) {
                        if (pChars[t] == match) {
                            minMatchSize++;
                        } else {
                            break;
                        }
                        patternPosition = t;
                    }
                }

                int accurateMatchSize = 0;
                for (int j = i; j >= 0; j--) {
                    if (match != '.' && sChars[j] != match) {
                        if (j == i) {
                            i++;
                        }
                        break;
                    } else {
                        accurateMatchSize++;
                    }
                    i = j;
                }

                if (accurateMatchSize < minMatchSize) {
                    return false;
                }
                if (patternPosition - 2 >= 0) {
                    patternPosition -= 2;
                }
                if (patternPosition == 1) {
                    patternPosition = 0;
                }

                continue;
            }
            if (patternPosition >= 0) {
                if (pChars[patternPosition] != '.' && sChars[i] != pChars[patternPosition]) {
                    return false;
                } else {
                    patternPosition--;
                }
            } else {
                return false;
            }

            //aaaa    a*aaaa
            if (i == 0 && patternPosition != 0) {
                //只可以是 x*
                if (patternPosition % 2 != 1) {
                    return false;
                } else {
                    for (int j = patternPosition; j > 0; j--) {
                        if (j % 2 == 1 && pChars[j] != '*') {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return patternPosition == 0;
    }


}
