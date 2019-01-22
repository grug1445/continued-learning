package com.grug.aviator;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by feichen on 2019/1/15.
 */
public class AviatorDemo {

    public static void main(String[] args) {
        String expression = "a+b-c";

        Map<String, Object> params = new HashMap<>();
        params.put("a", 1);
        params.put("b", 2);
        params.put("c", 5);

        long result = (long) AviatorEvaluator.execute(expression, params);
        System.out.println("result " + result);


    }
}
