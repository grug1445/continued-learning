package com.grug.reference;

/**
 * Created by feichen on 2018/6/1.
 */
public class FinalReferenceTest {
    /**
     * Object ojb=new Object() 这类引用.只要强引用存在,jvm永远不会回收掉补引用的对象.
     * 强引用的特点:
     * 1.强引用可以直接访问对象.
     * 2.强引用指向的对象任何时候都不会被系统回收.jvm宁愿抛出OOM也不会回收强引用对象.
     * 3.强引用可能引起OOM.
     *
     */
}
