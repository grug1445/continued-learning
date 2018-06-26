package tech.grug.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * Created by feichen on 2018/6/26.
 */
public class CuratorLockTest {

    //// TODO: 2018/6/26  这里有问题,不能执行,后续再查找问题
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);

        //创建分布式锁, 锁空间的根节点路径为/curator/lock
        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");
        try {
            mutex.acquire();
            //获得了锁, 进行业务流程
            System.out.println(Thread.currentThread().getName() + " Enter mutex");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //完成业务流程, 释放锁
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //关闭客户端
        client.close();

        TimeUnit.MINUTES.sleep(1);
    }
}
