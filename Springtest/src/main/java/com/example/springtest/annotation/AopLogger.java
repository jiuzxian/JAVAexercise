package com.example.springtest.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)// @Target注解用于指定自定义注解可以应用于哪些元素（目标）。ElementType.METHOD：可以应用于方法。
@Retention(RetentionPolicy.RUNTIME)// 用于指定自定义注解的保留策略，即注解在什么时候生效。RetentionPolicy.RUNTIME：注解会被保留在class文件中，并在运行时可以通过反射机制访问。
@Documented
public @interface AopLogger {
    String value() default "";
}
