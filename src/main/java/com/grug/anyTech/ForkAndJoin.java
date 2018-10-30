package com.grug.anyTech;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by feichen on 2018/5/15.
 * <p>
 * 工作窃取算法 空闲的线程从双端队列中取得任务进行执行.双端队列,窃取的任务从队尾窃取,被窃取的线程从队列头部执行
 */
public class ForkAndJoin extends RecursiveTask<Long> {


    private int start;
    private int end;

    /**
     * 阈值
     */
    private static final int THRESHOLD = 5;

    public ForkAndJoin(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;

        boolean canHandle = (end - start) <= THRESHOLD;
        //直接处理
        if (canHandle) {
            System.out.println("handle thread name is" + Thread.currentThread().getName());
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            System.out.println("thread name is" + Thread.currentThread().getName());
            //拆分任务
            int middle = (start + end) / 2;
            ForkAndJoin leftForkAndJoin = new ForkAndJoin(start, middle);
            ForkAndJoin rightForkAndJoin = new ForkAndJoin(middle + 1, end);
            invokeAll(leftForkAndJoin, rightForkAndJoin);

//            //任务执行
//            leftForkAndJoin.fork();
//            rightForkAndJoin.fork();

            long leftResult = leftForkAndJoin.join();
            long rightResult = rightForkAndJoin.join();

            sum = leftResult + rightResult;
        }
        return sum;
    }


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkAndJoin forkAndJoin = new ForkAndJoin(1, 1000);
        Future result = forkJoinPool.submit(forkAndJoin);
        try {
            System.out.println("result is " + result.get());
        } catch (InterruptedException | ExecutionException e) {

        }
    }
}
