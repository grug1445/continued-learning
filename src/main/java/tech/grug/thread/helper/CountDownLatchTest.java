package tech.grug.thread.helper;

import java.util.concurrent.CountDownLatch;

/**
 * Created by feichen on 2018/6/26.
 */
public class CountDownLatchTest {


    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread thread = new Thread(() -> {
            try {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(3000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行结束");
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(1000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行结束");
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            thread.start();
            thread2.start();
            //wait 阻塞线程
            countDownLatch.await();
            System.out.println("子线程执行完成");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
