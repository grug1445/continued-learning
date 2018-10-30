package com.grug.designPattern;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by feichen on 2018/5/30.
 */
public class ProducerConsumer {

    private BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);

    public static void main(String[] args) {

        ProducerConsumer producerConsumer = new ProducerConsumer();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                producerConsumer.producer(String.valueOf(i));
            }
        });
        producer.setName("producer");

        Thread consumer1 = new Thread(() -> {
            while (true){
                producerConsumer.consumer();
            }
        });
        consumer1.setName("consumer1");
        Thread consumer2 = new Thread(() ->{
            while (true){
                producerConsumer.consumer();
            }
        });
        consumer2.setName("consumer2");

        producer.start();
        consumer1.start();
        consumer2.start();

    }


    public void producer(String text) {
        try {
            blockingQueue.put(text);
            System.out.println(Thread.currentThread().getName() + " produce "+text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void consumer() {
        try {
            System.out.println(Thread.currentThread().getName() + "  consume " + blockingQueue.take());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
