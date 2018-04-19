package tech.grug.anyTech;

import java.util.LinkedHashSet;

public class Singleton {
//    private  volatile static Singleton instance;

    /**
     * 最佳实践,内部类实现
     */
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton() {
    }

    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

/*
//这个方法线程不安全,执行结果会有4次初始化
public static  Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }*/

//    //线程安全,但是运行效率太低
//    public static synchronized Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }


/*    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }*/


    public static void main(String[] args) {
        LinkedHashSet set=new LinkedHashSet();
        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(() -> {
                Singleton.getInstance();
            });
            thread.start();
        }
    }
}
