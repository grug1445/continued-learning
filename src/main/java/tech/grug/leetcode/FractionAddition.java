package tech.grug.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feichen on 2018/6/11.
 * <p>
 * <p>
 * 给定一个表示分数加减运算表达式的字符串，你需要返回一个字符串形式的计算结果。 这个结果应该是不可约分的分数，即最简分数。
 * 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。
 * <p>
 * 示例 1:
 * <p>
 * 输入:"-1/2+1/2"
 * 输出: "0/1"
 * 示例 2:
 * <p>
 * 输入:"-1/2+1/2+1/3"
 * 输出: "1/3"
 * 示例 3:
 * <p>
 * 输入:"1/3-1/2"
 * 输出: "-1/6"
 * 示例 4:
 * <p>
 * 输入:"5/3+1/3"
 * 输出: "2/1"
 * 说明:
 * <p>
 * 输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。
 * 输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
 * 输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
 * 输入的分数个数范围是 [1,10]。
 * 最终结果的分子与分母保证是 32 位整数范围内的有效整数。
 */
public class FractionAddition {

    static class Fraction {
        //符号
        TAG tag = TAG.POSITIVE;
        //分子
        int numerator;
        //分母
        int denominator;

        @Override
        public String toString() {
            return (tag == TAG.NEGATIVE ? "-" : "") + numerator + "/" + denominator;
        }
    }

    enum TAG {
        NEGATIVE,
        POSITIVE
    }

    public String fractionAddition(String expression) {
        List<Fraction> fractions = convert(expression);
        if (!fractions.isEmpty()) {
            fractions = calculate(fractions);
        }
        return !fractions.isEmpty() ? reduceFraction(fractions.get(0)).toString() : "";
    }

    /**
     * 计算
     *
     * @param fractions
     * @return
     */
    public static List<Fraction> calculate(List<Fraction> fractions) {
        if (fractions.isEmpty() || fractions.size() == 1) {
            return fractions;
        }
        Fraction last = fractions.get(fractions.size() - 1);
        Fraction last2 = fractions.get(fractions.size() - 2);

        int denominatorLcm = lcm(last.denominator, last2.denominator);

        int lastNumerator = last.numerator * (denominatorLcm / last.denominator);

        int last2Numerator = last2.numerator * (denominatorLcm / last2.denominator);

        if (last.tag == TAG.NEGATIVE) {
            lastNumerator = -lastNumerator;
        }

        if (last2.tag == TAG.NEGATIVE) {
            last2Numerator = -last2Numerator;
        }

        int numeratorResult = lastNumerator + last2Numerator;

        Fraction newFraction = new Fraction();
        newFraction.numerator = Math.abs(numeratorResult);
        newFraction.denominator = denominatorLcm;
        newFraction.tag = numeratorResult < 0 ? TAG.NEGATIVE : TAG.POSITIVE;

        fractions.remove(fractions.size() - 1);
        fractions.remove(fractions.size() - 1);

        fractions.add(newFraction);

        return calculate(fractions);
    }


    /**
     * 约分
     *
     * @param fraction
     * @return
     */
    public static Fraction reduceFraction(Fraction fraction) {
        if (fraction.numerator == 0) {
            fraction.denominator = 1;
            return fraction;
        }
        int gcd = gcd(fraction.numerator, fraction.denominator);
        if (gcd != 1) {
            fraction.numerator = fraction.numerator / gcd;
            fraction.denominator = fraction.denominator / gcd;
        }
        return fraction;
    }


    /**
     * 最大公约数
     *
     * @param a
     * @param b
     * @return
     */
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

    /**
     * 最小公倍数
     *
     * @param a
     * @param b
     * @return
     */
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    /**
     * 转换
     *
     * @param expression
     * @return
     */
    public static List<Fraction> convert(String expression) {
        List<Fraction> fractions = new ArrayList<>();
        char[] chars = expression.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '/') {
                Fraction fraction = new Fraction();

                List<Character> numeratorCharList = new ArrayList<>();
                //分子处理
                for (int j = i - 1; j >= 0; j--) {
                    if (Character.isDigit(chars[j])) {
                        numeratorCharList.add(0, chars[j]);
                    } else {
                        if (chars[j] == '-') {
                            fraction.tag = TAG.NEGATIVE;
                        }
                        break;
                    }
                }
                List<Character> denominatorCharList = new ArrayList<>();
                for (int j = i + 1; j < chars.length; j++) {
                    if (Character.isDigit(chars[j])) {
                        denominatorCharList.add(chars[j]);
                    } else {
                        break;
                    }
                }
                String numeratorStr =
                        numeratorCharList.stream().map(e -> e.toString()).reduce((acc, e) -> acc + e).get();
                String denominatorStr =
                        denominatorCharList.stream().map(e -> e.toString()).reduce((acc, e) -> acc + e).get();

                fraction.numerator = Integer.valueOf(numeratorStr);
                fraction.denominator = Integer.valueOf(denominatorStr);
                fractions.add(fraction);
            }
        }
        return fractions;
    }

    public static void main(String[] args) {
        FractionAddition fractionAddition = new FractionAddition();
        System.out.println(fractionAddition.fractionAddition("-5/2+10/3+7/9"));
    }

}
