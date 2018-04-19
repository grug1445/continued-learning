package tech.grug.annotation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class AnnotationTest {
    public static void main(String[] args) {

    }


    @Override
    @MethodInfo(author = "test", date = "2018-4-11", revision = 2, commonts = "test")
    public String toString() {
        return "Override toString method";
    }


    @Deprecated
    @MethodInfo(date = "2018-4-11", commonts = "commonts")
    public static void oldMethod() {
        System.out.println("old method,do not use it");
    }


    @SuppressWarnings({"uncheck", "deprecation"})
    @MethodInfo(date = "2018-4-12", commonts = "commonts test", revision = 10)
    public static void genericTest() throws FileNotFoundException {
        List list = new ArrayList<>();
        list.add("abc");
        oldMethod();
    }


}
