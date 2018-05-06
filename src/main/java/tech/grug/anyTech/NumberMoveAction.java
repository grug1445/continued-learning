package tech.grug.anyTech;

/**
 * Created by feichen on 2018/5/4.
 */
public class NumberMoveAction {

    public static void main(String[] args) {
        System.out.println("10       " + Integer.toBinaryString(10));
        System.out.println("9        " + Integer.toBinaryString(9));
        System.out.println("7        " + Integer.toBinaryString(7));
        System.out.println("-5        " + Integer.toBinaryString(-5));
        System.out.println("-5&7        " + Integer.toBinaryString(-5 & 7) + "    " + (-5 & 7));
        System.out.println("-5|7        " + Integer.toBinaryString(-5 | 7) + "    " + (-5 | 7));

        System.out.println("10<<1    " + Integer.toBinaryString(10 << 1) + "    " + (10 << 1));
        System.out.println("10>>2    " + Integer.toBinaryString(10 >> 2) + "    " + (10 >> 2));
        System.out.println("10&9     " + Integer.toBinaryString(10 & 9) + "    " + (10 & 9));
        System.out.println("10|9     " + Integer.toBinaryString(10 | 9) + "    " + (10 | 9));
        System.out.println("10^9     " + Integer.toBinaryString(10 ^ 9) + "    " + (10 ^ 9));
        System.out.println("~7       " + Integer.toBinaryString(~7) + "    " + (~7));

        System.out.println("-100     " + Integer.toBinaryString(-100));
        System.out.println("-100>>1  " + Integer.toBinaryString(-100 >> 1) + "    " + (-100 >> 1));
        System.out.println("-100<<1  " + Integer.toBinaryString(-100 << 1) + "    " + (-100 << 1));
        System.out.println("-100>>>1 " + Integer.toBinaryString(-100 >>> 1) + "    " + (-100 >>> 1));
    }
}
