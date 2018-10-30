package com.grug.thread;

import java.util.concurrent.*;

/**
 * Created by feichen on 2018/5/22.
 */
public class ThreadPool {

    /**
     * @param args
     */
    public static void main(String[] args) {

                threadPool();

//        executorService();
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (Exception ex) {
        }
    }

    /**
     * 线程池 coreSize,当poolSize=coreSize,又有新的任务进来时,这时的任务会放入queue中,
     * 当poolSize=coreSize ,queue 队列已经满时,这里会申请新的线程,
     * 如果 poolSize=maxSize 且queue队列满时,如果还有新的任务,这里会抛java.util.concurrent.RejectedExecutionException
     */
    public static void threadPool() {
        int coreSize = 2;
        int maxSize = 2;
        int keepAliveTime = 300;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(8);

        ThreadPoolExecutor poolExecutor =
                new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, TimeUnit.MILLISECONDS, blockingQueue,new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 100; i++) {
            poolExecutor.execute(() -> {
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName());
            });
        }
    }

    public static void executorService() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);


        newSingleThreadExecutor.submit(() -> {
            System.out.println("single hello world");
        });

        cachedThreadPool.submit(() -> {
            System.out.println(" cached thread pool hello world");
        });

        Future<Object> future = fixedThreadPool.submit(() -> {
            return " fix thread pool hello world";
        });

        try {
            System.out.println((String) future.get());
        } catch (Exception ex) {

        }

        scheduledExecutorService.schedule(() -> {
            System.out.println("schedule hello world");
        }, 5, TimeUnit.SECONDS);
    }
}
