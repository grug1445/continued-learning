package tech.grug.zookeeper.distributedLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by feichen on 2018/6/26.
 */
public interface DistributedLock {
    /**
     * 获取锁,如果没有获取锁,则一起等待
     * @throws Exception
     */
    void acquire() throws Exception;

    /**
     * 获取锁,如果没有得到锁,就在指定时间后超时
     * @param time 超时时间
     * @param timeUnit 时间单位
     * @return
     * @throws Exception
     */
    boolean acquire(long time, TimeUnit timeUnit) throws Exception;

    /**
     * 释放锁
     * @throws Exception
     */
    void release() throws Exception;
}
