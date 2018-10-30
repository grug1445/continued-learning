package com.grug.interview;

/**
 * Created by feichen on 2018/5/10.
 * 小明去附近的水果店买橙子，水果商贩只提供整袋购买，有每袋6个和每袋8个的包装（包装不可拆分）。
 * 可是小明只想购买恰好n个橙子，并且尽量少的袋数方便携带。如果不能购买恰好n个橙子，小明将不会购买。请根据此实现一个程序，要求：
 * 输入一个整数n，表示小明想要购买n（1≤n≤100）个橙子
 * 输出一个整数表示最少需要购买的袋数，如果不能买恰好n个橙子则输出-1
 * 例如，输入20，输出3。
 */
public class SougouTest {

    public int calculate(int n) {
        //奇数不可能分解成功
        if (n % 2 != 0
                || n < 6) {
            return -1;
        }
        int minCount = Integer.MAX_VALUE;
        //设6个装x个,8个装y个 则  0<=x<=16,0<=y<=12
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 13; j++) {
                if ((6 * i + 8 * j) == n) {
                    int total = i + j;
                    System.out.println(n + " " + "6*" + i + "+8*" + j);
                    if (total < minCount) {
                        minCount = total;
                    }
                }
            }
        }
        if (minCount == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minCount;
        }
    }


    public static void main(String[] args) {
        SougouTest sougouTest = new SougouTest();
        for (int i = 1; i < 101; i++) {
            System.out.println(i + " " + sougouTest.calculate(i));
        }
    }
}
