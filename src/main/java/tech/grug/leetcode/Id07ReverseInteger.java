package tech.grug.leetcode;

/**
 * Created by feichen on 2018/4/28.
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
