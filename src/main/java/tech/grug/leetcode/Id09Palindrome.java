package tech.grug.leetcode;

/**
 * Created by feichen on 2018/5/9.
 * <p>
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 * <p>
 * 你能不将整数转为字符串来解决这个问题吗？
 * <p>
 * <p>
 * 1 字符串
 * 2
 */
public class Id09Palindrome {


    public static void main(String[] args) {
        Id09Palindrome palindrome = new Id09Palindrome();
        System.out.println(palindrome.isPalindrome(2147483647));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int size = String.valueOf(x).length();
        int factor;
        if (size == 1) {
            return true;
        } else {
            factor = (int) Math.pow(10, size - 1) + 1;
        }
        //98 908 302 1000
        if ((x + 1) % factor == 0) return false;
        int result = x % factor;
        int resultSizeLimit = getResultLimit(size);
        if (result != 0) {
            if (result % 10 != 0
                    || result < resultSizeLimit) {
                return false;
            } else {
                x = division(result);
            }
        } else {
            return true;
        }
        return isPalindrome(x);
    }

    /**
     * 余数限制 100030001  余数30000>10000
     *
     * @return
     */
    private int getResultLimit(int size) {
        if (size % 2 == 0) {
            return (int) Math.pow(10, size / 2);
        } else {
            return (int) Math.pow(10, (size - 1) / 2);
        }
    }

    /**
     * make 330000->33
     *
     * @param x
     * @return
     */
    private int division(int x) {
        for (int i = String.valueOf(x).length(); i > 0; i--) {
            int tmp = (int) Math.pow(10, i - 1);
            if (x % tmp == 0) {
                return x / tmp;
            }
        }
        return 0;
    }
}
