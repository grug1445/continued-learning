package com.grug;

import com.grug.unitTest.ProxyHandler;
import com.grug.unitTest.RealSubject;
import com.grug.unitTest.Subject;
import com.grug.unitTest.TestInterface;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.lang.reflect.Proxy;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
/**
 * Created by feichen on 2018/9/22.
 */
//@RunWith(MockitoJUnitRunner.class)
public class InjectMockTest {

    @InjectMocks
    private RealSubject realSubject=new RealSubject();

    @Mock
    private TestInterface testInterface;


    @Test
    public void testProxyMock() {

        realSubject.request();
        ProxyHandler handler = new ProxyHandler(realSubject);   //2.创建调用处理器对象
        Subject proxySubject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                RealSubject.class.getInterfaces(),
                handler);    //3.动态生成代理对象
        when(testInterface.testAdd(anyInt(),anyInt())).thenReturn(100);
//        ReflectionTestUtils.setField(validationService, "countryService", countryService);
        proxySubject.request(); //4.通过代理对象调用方法
    }

}
