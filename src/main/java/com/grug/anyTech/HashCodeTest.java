package com.grug.anyTech;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by feichen on 2018/5/31.
 */
public class HashCodeTest {

    public static void main(String[] args) {
        String test = "hello world ,你好";
        System.out.println(test.hashCode());
        List<Character> characters = test.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        System.out.println(characters.indexOf('你'));
        for (char c : test.toCharArray()) {
            System.out.print("'" + c + "',");
        }


    }
}
