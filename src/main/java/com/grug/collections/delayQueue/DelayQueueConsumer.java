package com.grug.collections.delayQueue;

import java.util.concurrent.DelayQueue;

/**
 * Created by feichen on 2018/6/15.
 */
public class DelayQueueConsumer implements Runnable {

    private DelayQueue<Message> messageDelayQueue;

    public DelayQueueConsumer(DelayQueue<Message> messageDelayQueue) {
        this.messageDelayQueue = messageDelayQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message msg = messageDelayQueue.take();
                System.out.println(msg.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
}
