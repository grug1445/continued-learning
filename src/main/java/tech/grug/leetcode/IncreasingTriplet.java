package tech.grug.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by feichen on 2018/5/20.
 *
 *
 *给定一个未排序的数组，请判断这个数组中是否存在长度为3的递增的子序列。

 正式的数学表达如下:

 如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
 要求算法时间复杂度为O(n)，空间复杂度为O(1) 。

 示例:
 输入 [1, 2, 3, 4, 5],
 输出 true.

 输入 [5, 4, 3, 2, 1],
 输出 false.
 */
public class IncreasingTriplet {


    public static void main(String[] args) {
        IncreasingTriplet increasingTriplet = new IncreasingTriplet();
        int[] param = {2,5,3,1,2,3,4};
        System.out.println(increasingTriplet.increasingTriplet(param));

    }


    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        List<List<Integer>> listList = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (calcualte(listList, map, num)) {
                return true;
            }
        }
        return false;
    }


    boolean calcualte(List<List<Integer>> origin, Map<Integer, Integer> map, Integer toCompare) {
        if (origin.isEmpty()) {
            List<Integer> newGroup = new ArrayList<>(3);
            newGroup.add(toCompare);
            origin.add(newGroup);
            map.put(toCompare, toCompare);
            return false;
        }

        final int size = origin.size();

        for (int i = 0; i < size; i++) {
            List<Integer> workGroup = origin.get(i);

            if (toCompare < workGroup.get(0) && map.get(toCompare) != null) {
                continue;
            }
            if (toCompare < workGroup.get(0) && map.get(toCompare) == null) {
                List<Integer> newGroup = new ArrayList<>(3);
                newGroup.add(toCompare);
                origin.add(newGroup);
                map.put(toCompare, toCompare);
                continue;
            }

            if (toCompare == workGroup.get(0)) {
                continue;
            }

            if (workGroup.size() == 1) {
                workGroup.add(toCompare);
                continue;
            }

            if (workGroup.size() == 2) {
                int s = workGroup.get(0);
                int m = workGroup.get(1);
                if (toCompare > m) {
                    workGroup.add(toCompare);
//                    System.out.println(JSON.toJSONString(origin));
                    return true;
                }

                if (toCompare - s < m - s) {
                    workGroup.set(1, toCompare);
                }
            }
        }
//        System.out.println(JSON.toJSONString(origin));
        return false;
    }
}
