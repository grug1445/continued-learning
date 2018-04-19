package tech.grug.leetcode;

/**
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * <p>
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 * <p>
 * eg. 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * Created by feichen on 2018/4/15.
 */
public class Id01FindSumNumber {
    public static void main(String[] args) {
        int[] input = {3, 3};
        Id01FindSumNumber id01FindSumNumber = new Id01FindSumNumber();
        int[] result = id01FindSumNumber.twoSum(input, 6);
        if (result.length != 0)
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }
    }


    public int[] twoSum(int[] nums, int target) {
        int[] posResult = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && (nums[i] + nums[j]) == target) {
                    posResult[0] = j;
                    posResult[1] = i;
                }
            }
        }
        return posResult;
    }
}
