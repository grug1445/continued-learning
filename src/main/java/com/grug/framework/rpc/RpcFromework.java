package com.grug.framework.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by feichen on 2018/5/7.
 * <p>
 * http://javatar.iteye.com/blog/1123915
 */
public class RpcFromework {

    /**
     * 暴露服务
     *
     * @param service
     * @param port
     * @throws Exception
     */
    public static void export(final Object service, int port) throws Exception {
        if (service == null) {
            throw new IllegalArgumentException("service instance  == null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }

        System.out.println("Export service " + service.getClass().getName() + "on port " + port);

        //socket服务端
        ServerSocket serverSocket = new ServerSocket(port);

        for (; ; ) {
            try {
                final Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            System.out.println("Server 解析请求:  ");
                            String methodName = input.readUTF();
                            System.out.println("methodName :" + methodName);

                            Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                            System.out.println("parameterType :" + Arrays.toString(parameterTypes));

                            Object[] arguments = (Object[]) input.readObject();
                            System.out.println("arguments :" + Arrays.toString(arguments));

                            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                            try {
                                Method method = service.getClass().getMethod(methodName, parameterTypes);
                                Object result = method.invoke(service, arguments);

                                System.out.println(" server 处理并响应请求 :");
                                System.out.println("result is : " + result);
                                output.writeObject(result);
                            } catch (Exception e) {
                                output.writeObject(e);
                            } finally {
                                output.close();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            input.close();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     * 引用服务
     *
     * @param interfaceClass
     * @param host
     * @param port
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws Exception {
        if (interfaceClass == null) {
            throw new IllegalArgumentException("InterfaceClass ==null");
        }
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException("the " + interfaceClass.getName() + " must be interface class");
        }
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("host == null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("invalid port " + port);
        }
        System.out.println("get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);

        T proxy = (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket(host, port);
                        try {
                            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                            try {
                                System.out.println("client发送请求");
                                output.writeUTF(method.getName());
                                System.out.println("method name :" + method.getName());
                                output.writeObject(method.getParameterTypes());
                                System.out.println("parameterTypes :" + Arrays.toString(method.getParameterTypes()));
                                output.writeObject(args);
                                System.out.println("args :" + Arrays.toString(args));


                                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                                try {
                                    Object result = input.readObject();
                                    if (result instanceof Throwable) {
                                        throw (Throwable) result;
                                    }
                                    System.out.println("client 收到响应 :");
                                    System.out.println("result : " + result);
                                    return result;
                                } finally {
                                    input.close();
                                }
                            } finally {
                                output.close();
                            }
                        } finally {
                            socket.close();
                        }
                    }
                });

        return proxy;
    }
}
