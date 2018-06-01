package tech.grug.reference;

import com.alibaba.fastjson.JSON;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by feichen on 2018/6/1.
 * 虚引用,也叫幽灵引用.和没有引用一样,随时都可能被回收.
 * 虚引用get方法总是返回null.作用是跟踪垃圾回收过程,清理被回收对象相关的资源.
 *
 */
public class PhantomReferenceTest {
    private static ReferenceQueue<MyObject> weakRefQueue = new ReferenceQueue<>();

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
                objectReference = (Reference<MyObject>) weakRefQueue.remove();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            if (objectReference != null) {
                System.out.println("remove phantom ref is " + objectReference + " object for phantom reference is " +
                        objectReference.get());
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MyObject myObject = new MyObject();
        PhantomReference<MyObject> phantomRef = new PhantomReference<>(myObject, weakRefQueue);
        System.out.println("创建的虚引用为  " + JSON.toJSONString(phantomRef));

        new Thread(new CheckRefQueue()).start();
        myObject = null;
        TimeUnit.SECONDS.sleep(1);

        int i = 1;
        while (true) {
            System.out.println("第" + i++ + "次gc");
            System.gc();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
