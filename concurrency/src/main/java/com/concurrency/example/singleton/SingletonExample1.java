package com.concurrency.example.singleton;

import com.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉式单例模式
 *  第一次调用的时候初始化
 */
@Slf4j
@NotThreadSafe
public class SingletonExample1 {

    private SingletonExample1() {

    }

    private static SingletonExample1 singletonExample1 = null;

    public static SingletonExample1 getInstance() {
        if (singletonExample1 == null) {
            singletonExample1 = new SingletonExample1();
        }
        return singletonExample1;
    }
}
