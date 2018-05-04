package tech.grug.thread;

/**
 * Created by feichen on 2018/5/4.
 * 这里验证jvm对变量可见性的优化.jvm会最大努力保证变量可见性
 */
public class ThreadTest03 {
    private static boolean flag = true;

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (flag) {
                    System.out.println("flag is true");
                    i++;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                flag = false;
            }
        }).start();
    }
}
