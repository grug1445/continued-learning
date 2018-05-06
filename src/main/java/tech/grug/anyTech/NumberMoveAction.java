package tech.grug.anyTech;

/**
 * Created by feichen on 2018/5/4.
 */
public class NumberMoveAction {

    public static void main(String[] args) {
        int number = 10;
        System.out.println(Integer.toBinaryString(number));
        number = number << 1;
        System.out.println(Integer.toBinaryString(number));
        number = number >> 2;
        System.out.println(Integer.toBinaryString(number));
        number = -100;
        System.out.println(Integer.toBinaryString(number));
        number = number >>> 1;
        System.out.println(Integer.toBinaryString(number));
        number = -100;
        System.out.println(Integer.toBinaryString(number));
        number = number >> 1;
        System.out.println(Integer.toBinaryString(number));
    }
}
