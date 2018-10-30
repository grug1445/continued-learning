package com.grug.collections.delayQueue;

import java.util.concurrent.DelayQueue;

/**
 * Created by feichen on 2018/6/15.
 */
public class DelayQueueTest {

    public static void main(String[] args) {
        DelayQueue<Message> messageDelayQueue = new DelayQueue<Message>();
        Message message = new Message("first", 3000);
        Message message2 = new Message("second", 1000);
        messageDelayQueue.offer(message);
        messageDelayQueue.offer(message2);

        new Thread(new DelayQueueConsumer(messageDelayQueue)).start();

    }
}
