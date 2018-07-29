package tech.grug.loadClass;

/**
 * Created by feichen on 2018/7/27.
 * 对字段进行解析时，会先在本类中查找是否包含有简单名称和字段描述符都与目标相匹配的字段，
 * 如果有，则查找结束；如果没有，则会按照继承关系从上往下递归搜索该类所实现的各个接口和它们的父接口，
 * 还没有，则按照继承关系从上往下递归搜索其父类，直至查找结束
 *
 */
public class LoadClassTest {

    static class Super {
        public static int m = 11;

        static {
            System.out.println("run the super static block");
        }
    }

    static class Father extends Super {
        //        public static int m = 33;
        static {
            System.out.println("run the Father static block");
        }
    }

    static class Child extends Father {
        static {
            System.out.println("run the Child static block");
        }
    }

    public static void main(String[] args) {
        System.out.println(Child.m);
    }
}
