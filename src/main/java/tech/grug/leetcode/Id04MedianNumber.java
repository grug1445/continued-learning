package tech.grug.leetcode;

import java.util.ArrayList;
import java.util.List;

/**

 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。

 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。

 示例 1:

 nums1 = [1, 3]
 nums2 = [2]

 中位数是 2.0
 示例 2:

 nums1 = [1, 2]
 nums2 = [3, 4]

 中位数是 (2 + 3)/2 = 2.5
 * Created by feichen on 2018/4/18.
 * <p>
 * [2,3,8,10,22,39]
 * <p>
 * [1,3,5,5,6,9]
 * <p>
 * []
 * [0,6]
 */
public class Id04MedianNumber {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
        int[] nums2 = {};
        Id04MedianNumber id04MedianNumber = new Id04MedianNumber();
        System.out.println(id04MedianNumber.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] numbers1, int[] numbers2) {

        int[] longer, shorter;
        if (numbers1.length > numbers2.length) {
            longer = numbers1;
            shorter = numbers2;
        } else if (numbers1.length == numbers2.length) {
            if (numbers1[0] < numbers2[0]) {
                longer = numbers2;
                shorter = numbers1;
            } else {
                longer = numbers1;
                shorter = numbers2;
            }
        } else {
            longer = numbers2;
            shorter = numbers1;
        }
        List<Integer> newList = new ArrayList<>();

        int[] pos = calPos(numbers1.length + numbers2.length);
        int k = -1;
        //短的字符串是否已经加入完毕
        boolean shortFlag = false;
        for (int i = 0; i < longer.length; i++) {
            //是否已经到达合成的有序数组中间点
            if (newList.size() >= pos[pos.length - 1] + 1) {
                return calDoubleVal(newList, pos);
            }
            if (k >= shorter.length - 1 && shortFlag) {
                newList.add(longer[i]);
                if (newList.size() >= pos[pos.length - 1] + 1) {
                    return calDoubleVal(newList, pos);
                }
                continue;
            }
            //短的数组为空数组
            if (shorter.length == 0) {
                newList.add(longer[i]);
                if (newList.size() >= pos[pos.length - 1] + 1) {
                    return calDoubleVal(newList, pos);
                }
                continue;
            }
            for (int j = k == -1 ? 0 : k; j < shorter.length; j++) {
                if (shorter[j] < longer[i]) {
                    //遍历短的数组,值小于长的数组
                    newList.add(shorter[j]);
                    if (newList.size() >= pos[pos.length - 1] + 1) {
                        return calDoubleVal(newList, pos);
                    }
                    k = j;//标记处理到最新的游标
                    if (j == shorter.length - 1) {
                        shortFlag = true;
                        newList.add(longer[i]);
                        if (newList.size() >= pos[pos.length - 1] + 1) {
                            return calDoubleVal(newList, pos);
                        }
                    }
                } else if (shorter[j] == longer[i]) {
                    newList.add(shorter[j]);
                    newList.add(longer[i]);
                    if (newList.size() >= pos[pos.length - 1] + 1) {
                        return calDoubleVal(newList, pos);
                    }
                    k = j + 1;
                    if (j == shorter.length - 1) {
                        shortFlag = true;
                    }
                    break;
                } else {
                    newList.add(longer[i]);
                    if (newList.size() >= pos[pos.length - 1] + 1) {
                        return calDoubleVal(newList, pos);
                    }
                    k = j;
                    break;
                }

            }
        }
        return 0;
    }

    private double calDoubleVal(List<Integer> integers, int[] pos) {
        if (pos.length == 2) {
            return (integers.get(pos[0]) + integers.get(pos[1])) * 1.0 / 2;
        } else {
            return integers.get(pos[0]) * 1.0;
        }
    }


    private int[] calPos(int total) {
        //偶数
        if (total % 2 == 0) {
            int[] result = {total / 2 - 1, total / 2};
            return result;
        } else {
            int[] result = {(total - 1) / 2};
            return result;
        }
    }


}
