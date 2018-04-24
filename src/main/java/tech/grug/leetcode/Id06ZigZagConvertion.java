package tech.grug.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后从左往右，逐行读取字符："PAHNAPLSIIGYIR"
 * <p>
 * 实现一个将字符串进行指定行数变换的函数:
 * <p>
 * eg1.
 * 输入: s = "PAYPALISHIRING", numRows = 3
 * 输出: "PAHNAPLSIIGYIR"
 * <p>
 * eg2.
 * 输入: s = "PAYPALISHIRING", numRows = 4
 * 输出: "PINALSIGYAHRPI"
 * 解释:
 * <p>
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
public class Id06ZigZagConvertion {


    public static void main(String[] args) {

        Id06ZigZagConvertion id06ZigZagConvertion = new Id06ZigZagConvertion();
        System.out.println(id06ZigZagConvertion.convert("PAYPALISHIRING", 10));
    }

    public String convert(String s, int numRows) {
        char[] chars = s.toCharArray();

        if (chars.length <= numRows || numRows <= 1) {
            return s;
        }
        List<List<Character>> chLists = new ArrayList<>();
        List<Character> characters = null;
        List<Character> maxList = new ArrayList<>();
        List<Character> minList = new ArrayList<>();

        int factorLimit = numRows - 1;
        int factor = numRows * 2 - 2;
        int listSizeLimit = factorLimit - 1;
        for (int i = 0; i < chars.length; i++) {
            if (characters == null) {
                characters = new ArrayList<>();
            }
            if (i % factor == 0) {
                minList.add(chars[i]);
            } else if (i % factor < factorLimit) {
                characters.add(chars[i]);
            } else if (i % factor > factorLimit) {
                characters.add(0, chars[i]);
            } else {
                maxList.add(chars[i]);
            }
            if (characters.size() == listSizeLimit) {
                if (listSizeLimit == 0) {
                    continue;
                }
                chLists.add(characters);
                characters = null;
                continue;
            }

            //最后一个数组
            if (i == (chars.length - 1) && characters.size() < listSizeLimit) {
                int limit = listSizeLimit - characters.size();
                for (int j = 0; j < limit; j++) {
                    if (chLists.size() % 2 == 1) {
                        characters.add(0, null);
                    } else {
                        characters.add(null);
                    }
                }
                chLists.add(characters);
            }
        }

        //把数组列表转换为字符列表
        List<Character> characterList = new ArrayList<>();
        for (int i = 0; i < listSizeLimit; i++) {
            final int pos = i;
            chLists.forEach(characters1 -> {
                if (characters1.size() - 1 >= pos && characters1.get(pos) != null) {
                    characterList.add(characters1.get(pos));
                }
            });
        }
        characterList.addAll(maxList);
        characterList.addAll(0, minList);

        return convert(characterList);
    }

    private String convert(List<Character> characters) {
        char[] chars = new char[characters.size()];
        for (int i = 0; i < characters.size(); i++) {
            chars[i] = characters.get(i);
        }
        return new String(chars);
    }

}
