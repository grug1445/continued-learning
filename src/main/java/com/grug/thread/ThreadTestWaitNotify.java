package com.grug.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feichen on 2018/4/29.
 */
public class ThreadTestWaitNotify {
    static class ValueObject {
        public static List<String> list = new ArrayList<>();
    }

    static class ThreadAdd extends Thread {

        private String lock;

        public ThreadAdd(String lock, String name) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                ValueObject.list.add("anyString");
                lock.notifyAll();
            }
        }
    }

    static class ThreadSubtract extends Thread {
        private String lock;

        public ThreadSubtract(String lock, String name) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                synchronized (lock) {

                    while (ValueObject.list.size() == 0) {
//                    if (ValueObject.list.size() == 0) { //if有问题
                        System.out.println("wait begin thread name " + Thread.currentThread().getName());
                        lock.wait();
                        System.out.println("end begin thread name " + Thread.currentThread().getName());
                    }
                }
                ValueObject.list.remove(0);
                System.out.println("list size =" + ValueObject.list.size());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String lock = "distributedLock";

        ThreadSubtract threadSubtract1 = new ThreadSubtract(lock, "subtract1");
        threadSubtract1.start();
        ThreadSubtract threadSubtract2 = new ThreadSubtract(lock, "subtract2");
        threadSubtract2.start();
        ThreadAdd add = new ThreadAdd(lock, "add");
        add.start();

    }


}
