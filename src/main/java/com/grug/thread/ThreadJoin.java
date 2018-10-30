package com.grug.thread;

/**
 * Created by feichen on 2018/5/3.
 */
public class ThreadJoin {

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("进入线程 " + Thread.currentThread().getName());
            try {

                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            System.out.println("线程" + Thread.currentThread().getName() + "执行完毕");

        }


        public static void main(String[] args) {
            System.out.println("进入线程 " + Thread.currentThread().getName());

            MyThread myThread = new MyThread();
            myThread.start();

            try {
                System.out.println("线程 " + Thread.currentThread().getName() + "等待");
                myThread.join();
                System.out.println("线程 " + Thread.currentThread().getName() + "继续执行");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
