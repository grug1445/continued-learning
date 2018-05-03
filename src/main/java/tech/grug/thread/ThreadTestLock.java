package tech.grug.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by feichen on 2018/4/29.
 * <p>
 * 相比使用Object的wait()/notify()，
 * 使用Condition的await()/signal()这种方式能够更加安全和高效地实现线程间协作。
 * Condition是个接口，基本的方法就是await()和signal()方法。
 * Condition依赖于Lock接口，生成一个Condition的基本代码是lock.newCondition() 。
 * 必须要注意的是，Condition 的 await()/signal() 使用都必须在lock保护之内，也就是说，必须在lock.lock()和lock.unlock之间才可以使用。
 */
public class ThreadTestLock {

    static class MyService {
        private Lock lock = new ReentrantLock();

        public Condition conditionA = lock.newCondition();
        public Condition conditionB = lock.newCondition();

        public void awaitA() {
            lock.lock();
            try {
                System.out.println("begin awaitA时间为 "
                        + System.currentTimeMillis()
                        + " threadName= "
                        + Thread.currentThread().getName());
                conditionA.await();
                System.out.println("end awaitA时间为 "
                        + System.currentTimeMillis()
                        + " threadName= "
                        + Thread.currentThread().getName());

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void awaitB() {
            lock.lock();
            try {
                System.out.println("begin awaitB 时间为 "
                        + System.currentTimeMillis()
                        + " threadName= "
                        + Thread.currentThread().getName());
                conditionB.await();
                System.out.println("end awaitB 时间为 "
                        + System.currentTimeMillis()
                        + " threadName= "
                        + Thread.currentThread().getName());

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void signalALL_A() {
            try {
                lock.lock();
                System.out.println("signalALL_A 时间为 "
                        + System.currentTimeMillis()
                        + " threadName= "
                        + Thread.currentThread().getName());
                conditionA.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void signalALL_B() {
            try {
                lock.lock();
                System.out.println("signalALL_B 时间为 "
                        + System.currentTimeMillis()
                        + " threadName= "
                        + Thread.currentThread().getName());
                conditionB.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ThreadA extends Thread {
        private MyService myService;

        public ThreadA(MyService service) {
            this.myService = service;
        }

        @Override
        public void run() {
            myService.awaitA();
        }
    }

    static class ThreadB extends Thread {
        private MyService myService;

        public ThreadB(MyService service) {
            this.myService = service;
        }

        @Override
        public void run() {
            myService.awaitB();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();

        ThreadA threadA = new ThreadA(service);

        threadA.setName("a");
        threadA.start();

        ThreadB threadB = new ThreadB(service);

        threadB.setName("b");
        threadB.start();

        Thread.sleep(2000);

        service.signalALL_B();


    }
}
