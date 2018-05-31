package tech.grug.anyTech;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by feichen on 2018/5/28.
 */
public class FinalFinalyTest {


    public static void main(String[] args) {
        try {
            System.out.println("print sth");

            final List<String> strings = new ArrayList<>();
            strings.add("a");
            strings.add("b");
            System.out.println(JSON.toJSONString(strings));


//            strings = Arrays.asList("1", "2", "3"); error
//            System.exit(1);
        } finally {
            System.out.println("print finally");
        }


    }

}
