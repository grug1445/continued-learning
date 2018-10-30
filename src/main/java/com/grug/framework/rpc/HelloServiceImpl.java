package com.grug.framework.rpc;

/**
 * Created by feichen on 2018/5/7.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}
