package com.grug.thread.helper;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by feichen on 2018/6/26.
 */
public class CyclicBarrierTest {

    static class Write extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Write(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 正在写入数据");
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " 写入完成,等待其它线程执行");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("所有子线程执行完成,开始执行主线程");
        }
    }

    public static void main(String[] args) {
        int n = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n, () -> {
            System.out.println("all done");
        });

        for (int i = 0; i < n; i++) {
            Write write = new Write(cyclicBarrier);
            write.start();
        }
    }
}
