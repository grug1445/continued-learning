package tech.grug.algorithm;

/**
 * Created by feichen on 2018/6/11.
 * <p>
 * https://blog.csdn.net/qq_34992845/article/details/53043339
 * <p>
 * 计算最大公约数与最小公倍数
 */

//Greatest Common Divisor(GCDAndLCM)  别    名 Highest Common Factor(HCF)
//Least Common Multiple
public class GCDAndLCM {

    //a b 为正整数
    public static int gcd(int a, int b) {
        if (a == b) {
            return a;
        }
        if (a < b) {
            return gcd(b, a);
        } else {
            if (((a & 1) == 0) && ((b & 1) == 0)) { //ab都为偶数
                return gcd(a >> 1, b >> 1) << 1;

            } else if (((a & 1) == 0) && ((b & 1) == 1)) { //a为偶数,b为奇数
                return gcd(a >> 1, b);
            } else if (((a & 1) == 1) && ((b & 1) == 0)) { //a为奇数,b为偶数
                return gcd(a, b >> 1);
            } else { //ab都为奇数
                return gcd(b, a - b);
            }
        }
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }


    public static void main(String[] args) {
        System.out.println(gcd(79899, 59833));
        System.out.println(lcm(7, 5));
    }

}
