package com.grug.framework.rpc;

/**
 * Created by feichen on 2018/5/7.
 */
public class RpcConsumer {

    public static void main(String[] args) throws Exception {
        HelloService helloService = RpcFromework.refer(HelloService.class, "127.0.0.1", 1234);
        String result = helloService.hello("grug");
        System.out.println("client rcv message " + result);
    }
}
