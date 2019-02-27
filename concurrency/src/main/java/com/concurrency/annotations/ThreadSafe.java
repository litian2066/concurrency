package com.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标记课程安全的类的写法
 * 定义线程安全的注解标识
 * @author litian
 * @date 2019/2/27 15:18
 * @param
 * @return
 */
@Target(ElementType.TYPE) // 注解作用的目标
// 注解存在的范围 source: 编译的时候被忽略掉 需要用的时候采用runtime
// class:
// runtime: 注解未在class的字节码中存在 在运行时可以通过反射拿到
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {

    String  value() default "";
}
