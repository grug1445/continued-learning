package com.grug.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feichen on 2018/4/29.
 */
public class ThreadProviderConsumer {

    static class MyStack {
        private List list = new ArrayList<>();

        //生产
        public synchronized void push() {
            try {
                while (list.size() == 2) {
                    System.out.println("队列已满,线程" + Thread.currentThread().getName() + " to wait");
                    this.wait();
                }
                list.add("anyString" + Math.random());
                System.out.println("线程 " + Thread.currentThread().getName() + " add complete");
//                this.notify();
                this.notifyAll();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }


        //消费
        public synchronized String pop() {
            String returnValue = "";

            try {

                while (list.size() == 0) {
                    System.out.println("队列已空 ,线程" + Thread.currentThread().getName() + " to wait");
                    this.wait();
                }
                returnValue = "" + list.get(0);
                list.remove(0);

                System.out.println("线程 " + Thread.currentThread().getName() + "  消费了,队列已空...");
                this.notifyAll();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            return returnValue;

        }

    }

    static class ProviderThread extends Thread {
        private MyStack myStack;

        public ProviderThread(MyStack myStack, String name) {
            super(name);
            this.myStack = myStack;
        }

        public void pushService() {
            myStack.push();
        }

        public void run() {
            while (true) {
                myStack.push();
            }
        }
    }

    static class ConsumerThread extends Thread {
        private MyStack myStack;

        public ConsumerThread(MyStack myStack, String name) {
            super(name);
            this.myStack = myStack;
        }

        public void popService() {
            myStack.pop();
        }

        public void run() {
            while (true) {
                myStack.pop();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyStack myStack = new MyStack();
        ProviderThread providerThread = new ProviderThread(myStack, "provider 1");

        providerThread.start();


        ConsumerThread consumerThread = new ConsumerThread(myStack, "consumer 1");

        consumerThread.start();
    }


}
