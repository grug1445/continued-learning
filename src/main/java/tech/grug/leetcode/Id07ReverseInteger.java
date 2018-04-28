package tech.grug.leetcode;

/**
 * Created by feichen on 2018/4/28.
 *
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。

 示例 1:

 输入: 123
 输出: 321
 示例 2:

 输入: -123
 输出: -321
 示例 3:

 输入: 120
 输出: 21
 注意:

 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 */
public class Id07ReverseInteger {
    public int reverse(int x) {
        String xString = String.valueOf(x);
        char[] intChars = xString.toCharArray();

        boolean negative = false;
        int size = intChars.length;
        if (intChars[0] == '-') {
            negative = true;
            size -= 1;
        }
        int forsize;

        if (size % 2 == 1) {
            forsize = (size - 1) / 2;
        } else {
            forsize = size / 2;
        }
        for (int i = 0; i < forsize; i++) {
            if (negative) {
                char temp = intChars[i + 1];
                intChars[i + 1] = intChars[size - i];
                intChars[size - i] = temp;
            } else {
                char temp = intChars[i];
                intChars[i] = intChars[size - i - 1];
                intChars[size - i - 1] = temp;
            }
        }

        try {
            //// TODO: 2018/4/28 优化最大值判断 
            Integer result = Integer.valueOf(new String(intChars));
            return result;
        } catch (NumberFormatException e) {
            return 0;
        }

    }


    public static void main(String[] args) {
        Id07ReverseInteger id07ReverseInteger = new Id07ReverseInteger();
        id07ReverseInteger.reverse(Integer.MIN_VALUE);
//        System.out.println(Integer.valueOf("-0010"));
    }

}
