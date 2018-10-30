package com.grug.anyTech;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by feichen on 2018/5/15.
 */
public class StreamTest {

    public static void main(String[] args) {
        List<Integer> listOfIntegers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println(listOfIntegers);

        listOfIntegers
                .stream()
                .forEach(integer -> System.out.print(integer + " "));
        System.out.println("");

        System.out.println("listOfIntegers sorted in reverse order");
        Comparator<Integer> normal = Integer::compare;
        Comparator<Integer> reverse = normal.reversed();

        Collections.sort(listOfIntegers, reverse);

        listOfIntegers
                .stream()
                .forEach(integer -> System.out.print(integer + " "));
        System.out.println("");

        System.out.println("Parallel stream");
        listOfIntegers
                .parallelStream()
                .forEach(integer -> System.out.print(integer + " "));
        System.out.println("");


        System.out.println("another Parallel stream");
        listOfIntegers
                .parallelStream()
                .forEach(integer -> System.out.print(integer + " "));
        System.out.println("");

        System.out.println(" Parallel stream with forEachOrdered");
        listOfIntegers
                .parallelStream()
                .forEachOrdered(integer -> System.out.print(integer + " "));
        System.out.println("");


        listOfIntegers = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            listOfIntegers.add(new Random().nextInt());
        }
        System.out.println("start parallel stream" + System.currentTimeMillis());
        listOfIntegers
                .parallelStream()
                .forEach(integer -> System.out.print(" " + integer));
        System.out.println();
        System.out.println("end parallel stream" + System.currentTimeMillis());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("start " + System.currentTimeMillis());
        listOfIntegers
                .stream()
                .forEach(integer -> System.out.print(" " + integer));
        System.out.println();
        System.out.println("end " + System.currentTimeMillis());


    }

}
