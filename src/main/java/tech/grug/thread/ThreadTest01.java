package tech.grug.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feichen on 2018/4/29.
 */
public class ThreadTest01 {
    static class MyList {

        private volatile List<String> list = new ArrayList<>();

        public void add() {
            list.add("abc");
        }

        public int size() {
            return list.size();
        }
    }

    static class ThreadA extends Thread {
        private MyList list;

        public ThreadA(MyList list, String name) {
            super(name);
            this.list = list;
        }


        @Override
        public void run() {
            try {
                for (int i = 0; i < 3; i++) {
                    list.add();
                    System.out.println("添加了" + (i + 1) + "个元素");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    static class ThreadB extends Thread {
        private MyList list;

        public ThreadB(MyList list, String name) {
            super(name);
            this.list = list;
        }

        @Override
        public void run() {
            try {

                while (true) {
                    if (list.size() == 2) {
                        System.out.print("list size ==2 ,线程b要退出了");
                        throw new InterruptedException();
                    }
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        MyList service = new MyList();
        ThreadA threadA = new ThreadA(service, "A");
        ThreadB threadB = new ThreadB(service, "B");
        threadA.start();
        threadB.start();
    }


}
