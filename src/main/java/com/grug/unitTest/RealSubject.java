package com.grug.unitTest;

/**
 * Created by feichen on 2018/9/22.
 */
public class RealSubject implements Subject {

    private TestInterface testInterface=new TestInterfaceImpl();
    public void request() {
        System.out.println(testInterface.testAdd(1,2));
    }
}
