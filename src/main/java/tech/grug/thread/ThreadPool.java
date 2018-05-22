package tech.grug.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by feichen on 2018/5/22.
 */
public class ThreadPool {

    /**
     * 线程池 coreSize,当poolSize=coreSize,又有新的任务进来时,这时的任务会放入queue中,
     * 当poolSize=coreSize ,queue 队列已经满时,这里会申请新的线程,
     * 如果 poolSize=maxSize 且queue队列满时,如果还有新的任务,这里会抛java.util.concurrent.RejectedExecutionException
     *
     * @param args
     */
    public static void main(String[] args) {

        int coreSize = 1;
        int maxSize = 10;
        int keepAliveTime = 300;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(80);

        ThreadPoolExecutor poolExecutor =
                new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, TimeUnit.MILLISECONDS, blockingQueue);

        for (int i = 0; i < 100; i++) {
            poolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

    }
}
