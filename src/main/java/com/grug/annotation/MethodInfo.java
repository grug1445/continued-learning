package com.grug.annotation;

import java.lang.annotation.*;

//指明拥有这个注解的元素可以被javadoc此类的工具文档化
@Documented
//指明该类型的注解可以注解的程序元素的范围.该元注解的取值可以为TYPE,METHOD,CONSTRUCTOR,FIELD等
@Target(ElementType.METHOD)
//指明该注解类型被自动继承
@Inherited
//指明了该Annotation被保留的时间长短。RetentionPolicy取值为SOURCE,CLASS,RUNTIME。
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
    String author() default "grug";
    String date();
    int revision() default 1;
    String commonts();
}
