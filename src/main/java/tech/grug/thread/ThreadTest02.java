package tech.grug.thread;

/**
 * Created by feichen on 2018/4/29.
 */
public class ThreadTest02 {

    public static Object object = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("thread" + Thread.currentThread().getName() + " get the lock");
                try {
                    System.out.println("thread" + Thread.currentThread().getName() + "blocked and release lock");
                    object.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread" + Thread.currentThread().getName() + "  done");
            }
        }
    }

    static class Thread2 extends Thread {

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("thread" + Thread.currentThread().getName() + " get the lock");
                System.out.println("thread" + Thread.currentThread().getName() + " wake the waiting thread");
                object.notify();
                System.out.println("thread" + Thread.currentThread().getName() + "  done");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();

        Thread.sleep(2000);

        thread2.start();
    }

}
