package tech.grug.zookeeper.distributedLock;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by feichen on 2018/6/26.
 */
public class LockTest {
    public static void main(String[] args) throws Exception {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 3000);
        SimpleDistributedLock simpleDistributedLock = new SimpleDistributedLock(zkClient, "/locker");

        for (int i = 0; i < 10; i++) {
            try {
                simpleDistributedLock.acquire();
                System.out.println("正在进行运算 " + System.currentTimeMillis());
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                simpleDistributedLock.release();
                System.out.println("=============\r\n");

            }

        }
    }
}
