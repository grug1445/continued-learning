package com.grug.algorithm;

/**
 * 问题：在两个输入的数组中除了一个数字之外其余数字的值和顺序都相同，
 * 第一个数组比第二个数组多一个数字。请问如何找出第一个数组中多出的数字的下标？
 * 例如如果输入两个数组{2, 4, 6, 8, 9, 10, 12}和{2, 4, 6, 8, 10, 12}，则输出4，该下标对应的数字是9。
 */
public class FindTheNumber {

    //时间复杂度为O(n)
    private static int find(int[] array1, int[] array2) {
        int[] bigArray;
        int[] smallArray;
        if (array1.length > array2.length) {
            bigArray = array1;
            smallArray = array2;
        } else {
            bigArray = array2;
            smallArray = array1;
        }

        for (int i = 0; i < bigArray.length; i++) {
            if (i == bigArray.length - 1) {
                return bigArray.length - 1;
            }
            if (bigArray[i] != smallArray[i]) {
                return i;
            }
        }
        return 0;
    }

    //时间复杂度为O(log n)
    private static int findExtra(int[] array1, int[] array2) {
        int[] bigArray;
        int[] smallArray;
        if (array1.length > array2.length) {
            bigArray = array1;
            smallArray = array2;
        } else {
            bigArray = array2;
            smallArray = array1;
        }
        int start = 0;
        int end = bigArray.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid == bigArray.length - 1) {
                return mid;
            }
            if (bigArray[mid] != smallArray[mid]) {
                if (mid == 0 || bigArray[mid-1] == smallArray[mid-1]) {
                    return mid;
                }
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        int[] big = {2, 4, 6, 4,8, 10, 12};
        int[] small = {2, 4, 6, 8, 10, 12};
//        System.out.print(find(big, small));
        System.out.print(findExtra(big,small));
    }
}
