package com.grug.loadClass;

/**
 * Created by feichen on 2018/7/27.
 * <p>
 * 我们来看得到该结果的步骤。首先在准备阶段为类变量分配内存并设置类变量初始值，
 * 这样A和B均被赋值为默认值0，而后再在调用<clinit>（）方法时给他们赋予程序中指定的值。
 * 当我们调用Child.b时，触发Child的<clinit>（）方法，
 * 根据规则2，在此之前，要先执行完其父类Father的<clinit>（）方法，
 * 又根据规则1，在执行<clinit>（）方法时，
 * 需要按static语句或static变量赋值操作等在代码中出现的顺序来执行相关的static语句，因此当触发执行Father的<clinit>（）方法时，
 * 会先将a赋值为1，再执行static语句块中语句，将a赋值为2，而后再执行Child类的<clinit>（）方法，这样便会将b的赋值为2.
 * 如果我们颠倒一下Father类中“public static int a = 1;”语句和“static语句块”的顺序，程序执行后，则会打印出1。
 * 很明显是根据规则1，执行Father的<clinit>（）方法时，根据顺序先执行了static语句块中的内容，后执行了“public static int a = 1;”语句。
 * 另外，在颠倒二者的顺序之后，如果在static语句块中对a进行访问（比如将a赋给某个变量），
 * 在编译时将会报错，因为根据规则1，它只能对a进行赋值，而不能访问。
 */
public class LoadClassTest2 {
    static class Father {
        static {
            a = 2;
        }

        public static int a = 1;
    }

    static class Child extends Father {
        public static int b = a;
    }

    public static void main(String[] args) {
        System.out.println(Child.b);
    }

}
