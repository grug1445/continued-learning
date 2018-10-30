package com.grug.thread.helper;

import java.util.concurrent.Semaphore;

/**
 * Created by feichen on 2018/6/26.
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        //fair= true 表示等待时间越长,可以优先访问
        Semaphore semaphore = new Semaphore(2, true);
        for (int i = 0; i < 10; i++) {
            new Worker(i, semaphore).start();
        }
    }

    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("worker " + num + " working");
                Thread.sleep(3000);
                System.out.println("worker " + num + " done");
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
