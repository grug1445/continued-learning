package com.grug.unitTest;

import java.util.Collections;
import java.util.List;

/**
 * Created by feichen on 2018/9/22.
 */
public class TestInterfaceImpl implements TestInterface {

    private List<Integer> integerList = Collections.singletonList(1);

    @Override
    public int testAdd(int a, int b) {
        return a + b;
    }

    @Override
    public int listSize() {
        return integerList.size();
    }

    public static void main(String[] args) {
        TestInterfaceImpl testInterface = new TestInterfaceImpl();
        System.out.print(testInterface.listSize());
    }
}
