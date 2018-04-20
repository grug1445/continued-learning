package tech.grug.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feichen on 2018/4/19.
 * <p>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * <p>
 * kshdkkdsleillllskllls
 */
public class Id05LongestPalindromicSubstring {

    public static void main(String[] args) {
//        System.out.println("kshdkkdsleillllskllls".substring(4, 6));
        Id05LongestPalindromicSubstring longestPalindromicSubstring = new Id05LongestPalindromicSubstring();
        System.out.println(longestPalindromicSubstring.longestPalindrome("aabb"));
    }

    public String longestPalindrome(String s) {

        char[] sChar = s.toCharArray();

        String result = "";
        for (int i = 0; i < sChar.length; i++) {
            String findResult = find(sChar, i, s);
            if (findResult.length() > result.length()) {
                result = findResult;
                if (result.length() == sChar.length) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 查找以index为中心的回文子串
     * <p>
     * 分两种aba  aa
     *
     * @param str
     * @param index
     * @return
     */
    public String find(final char[] strArray, int index, final String str) {


        List<Character> aba = new ArrayList<>();
        List<Character> aabb = new ArrayList<>();

        int length = str.length();
        boolean abaFlag = false, aabbFlag = false;
        for (int i = 0; i < length; i++) {
            if (abaFlag && aabbFlag) {
                break;
            }
            if (index == 0) {
                return String.valueOf(strArray[index]);
            }
            if (!abaFlag
                    && index - i >= 0
                    && index + i < length
                    && strArray[index - i] == strArray[index + i]) {
                if (i == 0) {
                    aba.add(strArray[index]);
                } else {
                    aba.add(strArray[index + i]);
                    aba.add(0, strArray[index - i]);
                }
            } else {
                abaFlag = true;
            }

            if (!aabbFlag
                    && index - i >= 0
                    && index + i + 1 < length
                    && strArray[index - i] == strArray[index + i + 1]) {
                aabb.add(0, strArray[index - i]);
                aabb.add(strArray[index + i + 1]);
            } else {
                aabbFlag = true;
            }

        }
        return aba.size() > aabb.size() ? convertList2Array(aba) : convertList2Array(aabb);
    }


    private String convertList2Array(List<Character> characters) {
        char[] chars = new char[characters.size()];
        for (int i = 0; i < characters.size(); i++) {
            chars[i] = characters.get(i);
        }
        return new String(chars);
    }


}
