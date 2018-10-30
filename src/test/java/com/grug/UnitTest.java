package com.grug;

import com.grug.unitTest.RealSubject;
import com.grug.unitTest.TestInterface;
import com.grug.unitTest.TestInterfaceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * mock version 2.22.0
 * Created by feichen on 2018/9/22.
 */
//@RunWith(MockitoJUnitRunner.class)  ==  MockitoAnnotations.initMocks(this);
@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilTest.class)
public class UnitTest {

    @InjectMocks
    private TestInterface testInterface = new TestInterfaceImpl();

    @InjectMocks
    private RealSubject realSubject = new RealSubject();

    @Mock
    private List<Integer> list;

    @Mock
    private List<Integer> integerList;

    /**
     * MockitoAnnotations.initMocks(this) method has to be called to initialize annotated objects.
     */
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(UtilTest.class);
    }

    @Test
    public void testMockCreation() {
        // mock creation
        List mockedList = mock(List.class);

        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();

        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    /**
     * mock实现类
     */
    @Test
    public void testMockConcreteClass() {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");

        Assert.assertEquals("first", mockedList.get(0));
    }

    @Test
    public void testVerify() {
        List<String> mockedList = mock(LinkedList.class);
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");

        //c没有其它操作
        verifyNoMoreInteractions(mockedList);

    }

    @Test
    public void testStubbingWithExceptions() {
        doThrow(new RuntimeException("test exception")).when(integerList).clear();

        //following throws RuntimeException:
        try {
            integerList.clear();
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }
    }

    @Test
    public void testInOrderVerification() {
        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);

        //following will make sure that add is first called with "was added first, then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrderSecond = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        inOrderSecond.verify(firstMock).add("was called first");
        inOrderSecond.verify(secondMock).add("was called second");

        // Oh, and A + B can be mixed together at will
    }

    /**
     * spy()只模拟类中某一个方法,未spy()的方法仍然调用真实方法,
     * mock()模拟整个类的全部方法
     */
    @Test
    public void testSpy() {
        List list = new LinkedList();
        List spy = spy(list);
        List mock = mock(List.class);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);
        when(mock.size()).thenReturn(100);


        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        mock.add("one");
        mock.add("two");

        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

        //prints "null"
        System.out.println(mock.get(0));

        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());
        // prints 100
        System.out.println(mock.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");


    }

    /**
     * Sometimes it's impossible or impractical to use when(Object) for stubbing spies.
     * Therefore when using spies please consider doReturn|Answer|Throw() family of methods for stubbing
     */
    @Test
    public void testSpyRealObject() {
        List list = new LinkedList();
        List spy = spy(list);

        try {
            //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
            when(spy.get(0)).thenReturn("foo");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }

        //You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);
        assertEquals("foo", spy.get(0));
    }

    /**
     *
     */
    @Test
    public void testResetMock() {
        List mock = mock(List.class);
        when(mock.size()).thenReturn(10);
        mock.add(1);
        reset(mock);
        assertEquals(0, mock.size());
    }


    @Test
    public void testMocksAnnotation() {
        when(integerList.size()).thenReturn(100);
        assertEquals(100, integerList.size());
    }


    @Test
    public void testInjectMocksAnnotation() {
        assertEquals(0, testInterface.listSize());
        when(integerList.size()).thenReturn(50);
        assertEquals(50, testInterface.listSize());
    }

    /**
     * 必须在测试类前加
     *
     * @RunWith(PowerMockRunner.class)
     * @PrepareForTest(UtilTest.class) 其中UtilTest的为要mock的类.
     */
    @Test
    public void testPowerMockStatic() {
        PowerMockito.when(UtilTest.getInteger(Mockito.any())).thenReturn(5);
        //will print 5
        System.out.print(UtilTest.getInteger(9999));

    }
}
