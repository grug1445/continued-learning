package com.grug.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by feichen on 2018/6/29.
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * <p>
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * <p>
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * <p>
 * <p>
 * 分析:
 * n<= 1+2+...+9=45
 * n必须大于等于  1到9的前k个数之和.如 k=3,则 n>=1+2+3=6 ,如k=5 ,则n>=1+2+3+4+5=15
 * k<=9
 */
public class CombinationSum3 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> listList = new ArrayList<>();
        if (k == 1) {
            if (n <= 9) {
                List<Integer> integers = new ArrayList<>();
                integers.add(n);
                listList.add(integers);
                return listList;
            } else {
                return listList;
            }
        }
        if (k == 9) {
            if (n == 45) {
                List<Integer> integers = new ArrayList<>();
                for (int i = 1; i <= 9; i++) {
                    integers.add(i);
                }
                listList.add(integers);
                return listList;
            } else {
                return listList;
            }

        }

        if (k > 9 || n < addPreN(k) || n > getPostN(k)) {
            return new ArrayList<>();
        }

        return execute(k, n, 1, null);
    }

    private static int addPreN(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }

    private static int getPostN(int n) {
        int result = 0;
        for (int i = 9; i > (9 - n); i--) {
            result += i;
        }
        return result;
    }

    private static List<List<Integer>> execute(int k, int n, int start, List<List<Integer>> result) {
        if (result == null) {
            result = new ArrayList<>();
        }
        if (9 == start || start > n) {
            return result;
        }
        List<Integer> integers = new ArrayList<>();
        Map<Integer, Integer> maxMap = new TreeMap<>((a, b) -> b.compareTo(a));
        integers.add(start);
        for (int i = 2; i <= k; i++) {
            for (int j = integers.get(integers.size() - 1) + 1; j <= 9; j++) {
                if (integers.contains(j)) {
                    continue;
                }
                if (integers.size() == k - 1) {
                    int lastOne = n - combine(integers);
                    if (lastOne <= 0 || integers.contains(lastOne) || lastOne > 9) {
                        integers.set(integers.size() - 1, j);
                        maxMap.put(integers.size(), j);
                        if (j == 9) {
                            //[1,2,9,13]  ->[1,3]
                            i = checkMap(maxMap, integers, i);
                            break;
                        }
                        continue;
                    }
                    integers.add(lastOne);
                    add(k, integers, result);

                    if (k > 2) {
                        i = checkMap(maxMap, integers, i);
                    }
                    break;
                }
                integers.add(j);
                maxMap.put(i, j);
                if (j == 9) {
                    i = checkMap(maxMap, integers, i);
                }
                break;
            }
        }
        return execute(k, n, start + 1, result);
    }

    private static int checkMap(Map<Integer, Integer> map, List<Integer> integers, int i) {
        for (Integer pos : map.keySet()) {
            int value2 = map.get(pos);
            if (value2 < 8) {
                int size = integers.size();
                for (int i2 = size - 1; i2 >= pos - 1; i2--) {
                    integers.remove(i2);
                }
                integers.add(value2 + 1);
                map.put(pos, value2 + 1);
                return pos;
            }
        }
        return i;
    }

    private static void add(int k, List<Integer> list, List<List<Integer>> lists) {
        System.out.println(JSON.toJSON(list));
        List<Integer> resultList = sortList(list);
        if (resultList.size() == k && !lists.contains(resultList)) {
            lists.add(resultList);
        }
    }

    private static int combine(List<Integer> integers) {
        return integers.stream().reduce(((integer, integer2) -> integer + integer2)).orElse(0);
    }


    private static List<Integer> sortList(List<Integer> list) {
        return list.stream().sorted((i, j) -> i - j).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        CombinationSum3 combinationSum3 = new CombinationSum3();
        List<List<Integer>> list = combinationSum3.combinationSum3(3, 9);
        System.out.println(JSON.toJSONString(list));
    }

}
