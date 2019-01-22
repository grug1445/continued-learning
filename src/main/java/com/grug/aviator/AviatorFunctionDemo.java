package com.grug.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorLong;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by feichen on 2019/1/15.
 */
public class AviatorFunctionDemo {

    public static void main(String[] args) {
        AviatorEvaluator.addFunction(new MySumFunction());
        String expression = "my_sum(a,b,c)";
        Map<String, Object> params = new HashMap<>();
        params.put("a", 98);
        params.put("b", 2993);
        params.put("c", 399283);

        long result = (long) AviatorEvaluator.execute(expression, params);

        System.out.println("result is " + result);

    }

    static class MySumFunction extends AbstractFunction {

        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject a, AviatorObject b, AviatorObject c) {
            Number numberA = FunctionUtils.getNumberValue(a, env);
            Number numberB = FunctionUtils.getNumberValue(b, env);
            Number numberC = FunctionUtils.getNumberValue(c, env);

            long result = numberA.longValue() + numberB.longValue() + numberC.longValue();
            return AviatorLong.valueOf(result);
        }

        /**
         * 获取名称
         *
         * @return
         */
        public String getName() {
            return "my_sum";
        }
    }

}
