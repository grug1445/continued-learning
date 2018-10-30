package com.grug.collections.delayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by feichen on 2018/6/15.
 */
public class Message implements Delayed {


    private long delayTime;

    private String body;

    /**
     * 执行时间
     */
    private long executeTime;


    public String getBody() {
        return body;
    }

    public Message(String body, long delayTime) {
        this.body = body;
        this.delayTime = delayTime;
        this.executeTime = delayTime + System.currentTimeMillis();
    }

    /**
     * 剩余时间
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.executeTime - System.currentTimeMillis(), TimeUnit.MICROSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "Message{" +
                "delayTime=" + delayTime +
                ", body='" + body + '\'' +
                ", executeTime=" + executeTime +
                '}';
    }
}
