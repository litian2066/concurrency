package com.concurrency.example.singleton;

import com.concurrency.annotations.Recommend;
import com.concurrency.annotations.ThreadSafe;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import lombok.extern.slf4j.Slf4j;

/**
    使用枚举方式来定义单例模式
    相对于饿汉模式的线程安全
    相对于懒汉模式的调用的时候才初始化 不会造成资源的浪费
 */
@Slf4j
@ThreadSafe
@Recommend
public class SingletonExample7 {

    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample7 sinleton;
        // JVM保证只调用一次
        Singleton() {
            sinleton = new SingletonExample7();
        }
        public SingletonExample7 getInstance() {
            return sinleton;
        }
    }
}
