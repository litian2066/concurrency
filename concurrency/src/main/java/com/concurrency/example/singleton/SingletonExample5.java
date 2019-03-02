package com.concurrency.example.singleton;

import com.concurrency.annotations.NotRecommend;
import com.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉式单例模式
 *  第一次调用的时候初始化
 *  双重单例模式
 *  线程不安全:
 *      CPU的指令说起
 *      new的操作 要执行
 *      1. memory = allocate() 分配对象内存空间
 *      2. ctorInstance() 初始化对象
 *      3.instance = memory 设置对象指向刚分配的内存
 *      多线程的情况下可能会发生指令重排序:
 *      1. 3. 2
 *      所以在多线程的情况下 第一个线程还没有初始化对象 线程二 会判断已经有这个对象  就直接返回了 所以会导致问题的发生
 *      于是使用volatile防止指令重排序
 *      volatitle + 双重检测
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class SingletonExample5 {

    private SingletonExample5() {

    }

    private volatile static SingletonExample5 singletonExample1 = null;

    public static SingletonExample5 getInstance() {
        if (singletonExample1 == null) { // 双重检测机制
            synchronized (SingletonExample5.class) { // 同步锁
                singletonExample1 = new SingletonExample5();
            }
        }
        return singletonExample1;
    }
}
