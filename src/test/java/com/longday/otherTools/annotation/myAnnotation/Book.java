package com.longday.otherTools.annotation.myAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/3
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Book {
    String name();
    String[] authors();
    double price();
    String address() default "中国";
}
