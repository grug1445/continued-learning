package tech.grug.leetcode;

/**
 * Created by feichen on 2018/6/17.
 * <p>
 * <p>
 * 求解一个给定的方程，将x以字符串"x=#value"的形式返回。该方程仅包含'+'，' - '操作，变量 x 和其对应系数。
 * <p>
 * 如果方程没有解，请返回“No solution”。
 * <p>
 * 如果方程有无限解，则返回“Infinite solutions”。
 * <p>
 * 如果方程中只有一个解，要保证返回值 x 是一个整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "x+5-3+x=6+x-2"
 * 输出: "x=2"
 * 示例 2:
 * <p>
 * 输入: "x=x"
 * 输出: "Infinite solutions"
 * 示例 3:
 * <p>
 * 输入: "2x=x"
 * 输出: "x=0"
 * 示例 4:
 * <p>
 * 输入: "2x+3x-6x=x+2"
 * 输出: "x=-1"
 * 示例 5:
 * <p>
 * 输入: "x=x+2"
 * 输出: "No solution"
 */


public class SolveEquation {

    /**
     * 方程没有解
     */
    private static final String NO_SOLUTIONS = "No solution";

    /**
     * 方程有无限解
     */
    private static final String INFINITE_SOLUTIONS = "Infinite solutions";


    class EquationSide {
        /**
         * 有几个x
         */
        private int countX;

        /**
         * 常数
         */
        private int constantNo;

        public int getCountX() {
            return countX;
        }

        public void setCountX(int countX) {
            this.countX = countX;
        }

        public int getConstantNo() {
            return constantNo;
        }

        public void setConstantNo(int constantNo) {
            this.constantNo = constantNo;
        }

        @Override
        public String toString() {
            return "EquationSide{" +
                    "countX=" + countX +
                    ", constantNo=" + constantNo +
                    '}';
        }
    }


    /**
     * 转换
     *
     * @param equation
     * @return
     */
    private EquationSide convert(String equation) {
        String[] strings = equation.split("=");
        EquationSide finalEquation = new EquationSide();
        if (strings.length != 2) {
            return finalEquation;
        }
        String left = strings[0];
        String right = strings[1];
        EquationSide leftEquation = convertString(left);
        EquationSide rightEquation = convertString(right);

        finalEquation.setCountX(leftEquation.getCountX() - rightEquation.getCountX());
        finalEquation.setConstantNo(leftEquation.getConstantNo() - rightEquation.getConstantNo());
        return finalEquation;
    }

    /**
     * @param s
     * @return
     */
    private EquationSide convertString(String s) {
        EquationSide equationSide = new EquationSide();
        if (s.length() == 1) {
            if (s.equals("x")) {
                equationSide.setCountX(1);
            } else {
                equationSide.setConstantNo(Integer.valueOf(s));
            }
            return equationSide;
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ) {
            if (i == chars.length - 1) {
                break;
            }
            for (int j = i + 1; j < chars.length; j++) {
                if (j == chars.length - 1) {
                    if (chars[j] == 'x') {
                        if (j >= 1 && chars[j - 1] != '+' && chars[j - 1] != '-') {
                            equationSide.countX += Integer.valueOf(s.substring(i, j));
                        } else if (j >= 1 && chars[j - 1] == '+') {
                            equationSide.countX++;
                        } else if (j >= 1 && chars[j - 1] == '-') {
                            equationSide.countX--;
                        }
                    } else {
                        equationSide.constantNo += Integer.valueOf(s.substring(i));
                    }
                    i = j;
                    break;
                }
                if (chars[j] == '+' || chars[j] == '-') {
                    if (chars[j - 1] == 'x') {
                        if (j >= 2 && chars[j - 2] != '+' && chars[j - 2] != '-') {
                            equationSide.countX += Integer.valueOf(s.substring(i, j - 1));
                        } else if (j >= 2 && chars[j - 2] == '-') {
                            equationSide.countX--;
                        } else {
                            equationSide.countX++;
                        }
                    } else {
                        equationSide.constantNo += Integer.valueOf(s.substring(i, j));
                    }
                    i = j;
                    break;
                }
            }
        }
        return equationSide;
    }

    public String solveEquation(String equation) {
        EquationSide finalResult = convert(equation);
        if (finalResult.getCountX() == 0 && finalResult.getConstantNo() == 0) {
            return INFINITE_SOLUTIONS;
        } else if (finalResult.getCountX() == 0 && finalResult.getConstantNo() != 0) {
            return NO_SOLUTIONS;
        } else if (finalResult.getCountX() != 0 && finalResult.getConstantNo() == 0) {
            return "x=0";
        } else {
            return "x=" + -(finalResult.getConstantNo() / finalResult.getCountX());
        }
    }


    public static void main(String[] args) {
        SolveEquation solveEquation = new SolveEquation();
        System.out.println(solveEquation.solveEquation("x=x+2"));

    }
}
