package tech.grug.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * klsiepww[[kshkkds@#$
 * <p>
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * <p>
 * 示例：
 * <p>
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 * <p>
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 * <p>
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 * Created by feichen on 2018/4/17.
 */
public class Id03FindLongestString {


    public static void main(String[] args) {
        Id03FindLongestString findLongestString = new Id03FindLongestString();
        System.out.print(findLongestString.lengthOfLongestSubstring("klsiepww[[kshkkds@#$"));
    }

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();

        Map<Character, Integer> characterMap = new HashMap<>();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            Integer pos = characterMap.get(c);
            if (pos == null) {
                characterMap.put(c, i);
                if (i == chars.length - 1) {
                    if (characterMap.size() > result) result = characterMap.size();
                }
            } else {
                if (characterMap.size() > result) result = characterMap.size();
                i = pos;
                characterMap = new HashMap<>();
            }
        }
        return result;
    }
}
