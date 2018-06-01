package tech.grug.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * Created by feichen on 2018/6/1.
 * 软引用用来描述一些有用但非必须的对象.
 * 软引用对象,如果内存充足,则不会回收.如果内存不足,则会回收.
 *
 * 主要用于内存敏感的调整缓存.如在android系统中,图片加载.
 *
 */

public class SoftReferenceTest {

    private static ReferenceQueue<MyObject> softRefQueue = new ReferenceQueue<>();

    public static class MyObject {

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("MyObject finalize is called ");
        }

        @Override
        public String toString() {
            return "I am MyObject";
        }
    }

    public static class CheckRefQueue implements Runnable {
        Reference<MyObject> objectReference = null;

        @Override
        public void run() {
            try {
                objectReference = (Reference<MyObject>) softRefQueue.remove();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            if (objectReference != null) {
                System.out.println("object for soft reference is" + objectReference.get());
            }
        }
    }

    public static void main(String[] args){
        MyObject myObject=new MyObject();
        SoftReference<MyObject> softRef=new SoftReference<>(myObject,softRefQueue);

        new Thread(new CheckRefQueue()).start();

        myObject=null;
        System.gc();

        System.out.println("after gc :soft get= "+softRef.get());
        System.out.println("分配大内存  ");
        byte[] bytes=new byte[5*1024*928];

        System.out.println("after new byte[]:soft get "+softRef.get());
        System.gc();
    }
}
