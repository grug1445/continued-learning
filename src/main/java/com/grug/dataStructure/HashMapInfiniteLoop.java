package com.grug.dataStructure;

import java.util.HashMap;

/**
 * java1.7中会出现
 * Created by feichen on 2018/6/3.
 */
public class HashMapInfiniteLoop {
    private static HashMap<Integer, String> map = new HashMap<>(2, 0.75f);

    public static void main(String[] args) {

        map.put(5,"c");

        new Thread("tread1"){

            @Override
            public void run() {
                map.put(7,"b");
                System.out.println(map);
            }
        }.start();

        new Thread("thread2"){
            @Override
            public void run() {
                map.put(3,"a");
                System.out.println(map);
            }
        }.start();
    }
}
