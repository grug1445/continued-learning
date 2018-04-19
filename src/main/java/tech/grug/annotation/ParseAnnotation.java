package tech.grug.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ParseAnnotation {

    public static void main(String[] args) {

        try {
            for (Method method : ParseAnnotation.class
                    .getClassLoader()
                    .loadClass("AnnotationTest")
                    .getMethods()) {

                if (method.isAnnotationPresent(MethodInfo.class)) {
                    try {
                        for (Annotation annotation : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in method  :" + annotation);
                        }
                        MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
                        if (methodInfo.revision() == 1) {
                            System.out.println("Method revision No 1 : " + method);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
