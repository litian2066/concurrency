package com.concurrency.example.singleton;

import com.concurrency.annotations.NotThreadSafe;
import com.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 *  饿汉式单例模式
 *  类装载的时候初始化 是线程安全的
 *  如果只是类的加载没有实际的调用的话会造成资源的浪费
 *      所以要保证私有构造函数没有太多的处理
 *      一定会被调用 否则会造成资源的浪费
 */
@Slf4j
@ThreadSafe
public class SingletonExample2 {

    private SingletonExample2() {

    }

    private static SingletonExample2 singletonExample1 = new SingletonExample2();

    public static SingletonExample2 getInstance() {
        return singletonExample1;
    }
}
