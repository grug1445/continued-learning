package tech.grug.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by feichen on 2018/5/2.
 */
public class ThreadProviderConsumer2 {

    static class MyService {
        private ReentrantLock lock = new ReentrantLock();

        private Condition conditionA = lock.newCondition();
        private Condition conditionB = lock.newCondition();

        private boolean hasValue = false;


        public void set() {

            try {
                lock.lock();
                while ((hasValue)) {
                    System.out.println("provider 线程 " + Thread.currentThread().getName() + " await");
                    conditionA.await();
                }

                System.out.println(" providing 线程 " + Thread.currentThread().getName() + " 生产中");
                Thread.sleep(1000);

                hasValue = true;
                System.out.println(" 线程 " + Thread.currentThread().getName() + " 生产完毕");
                System.out.println("provider wake all threads " + Thread.currentThread().getName() + " ....");
                conditionA.signalAll();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void get() {

            try {
                lock.lock();
                while (!hasValue) {
                    System.out.println(" consumer 线程 " + Thread.currentThread().getName() + " await");
                    conditionB.await();
                }

                System.out.println(" consumer 线程 " + Thread.currentThread().getName() + " 消费中");
                Thread.sleep(1000);

                hasValue = false;
                System.out.println(" 线程 " + Thread.currentThread().getName() + "消费完毕");
                System.out.println("  consumer wake all threads " + Thread.currentThread().getName() + " ....");
                conditionB.signalAll();

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    static class MyPushThread extends Thread {

        private MyService myService;

        public MyPushThread(MyService myService, String name) {
            super(name);
            this.myService = myService;
        }

        @Override
        public void run() {
            while (true) {
                myService.set();
            }
        }
    }


    static class MyPopThread extends Thread {

        private MyService myService;

        public MyPopThread(MyService myService, String name) {
            super(name);
            this.myService = myService;
        }

        @Override
        public void run() {
            while (true) {
                myService.get();
            }
        }
    }

    public static void main(String[] args) {
        MyService myService = new MyService();

        MyPushThread[] pushThreads = new MyPushThread[10];
        MyPopThread[] popThreads = new MyPopThread[10];

        for (int i = 0; i < 10; i++) {
            pushThreads[i] = new MyPushThread(myService, "ThreadPush-" + i);
            popThreads[i] = new MyPopThread(myService, "ThreadPop-" + i);
            pushThreads[i].start();
            popThreads[i].start();
        }
    }

}
