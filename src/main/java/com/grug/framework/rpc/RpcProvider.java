package com.grug.framework.rpc;

/**
 * Created by feichen on 2018/5/7.
 */
public class RpcProvider {

    public static void main(String[] args) throws Exception {
        HelloService helloService = new HelloServiceImpl();
        RpcFromework.export(helloService, 1234);
    }
}
