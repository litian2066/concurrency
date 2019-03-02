package com.concurrency.example.singleton;

import com.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 *  饿汉式单例模式的另一种写法
 *  类装载的时候初始化 是线程安全的
 *  如果只是类的加载没有实际的调用的话会造成资源的浪费
 *      所以要保证私有构造函数没有太多的处理
 *      一定会被调用 否则会造成资源的浪费
 */
@Slf4j
@ThreadSafe
public class SingletonExample6 {

    private SingletonExample6() {

    }

    // 单例对象
    private static SingletonExample6 singletonExample1 = null;

    static {
        singletonExample1 = new SingletonExample6();
    }

    public static SingletonExample6 getInstance() {
        return singletonExample1;
    }
}
