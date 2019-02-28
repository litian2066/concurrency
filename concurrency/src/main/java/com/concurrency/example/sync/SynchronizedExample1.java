package com.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示synchronized关键字
 * 子类继承该类是方法是带不出sysn关键字的 sysn是不属于方法声明的一部分
 */
@Slf4j
public class SynchronizedExample1 {

    // 修饰代码块 作用于调用的对象
    private void test1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1: {}", i);
            }
        }
    }

    // 修饰方法 作用于调用对象
    private synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2: {}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        SynchronizedExample1 synchronizedExample2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test1();
        });
        executorService.execute(() -> {
            synchronizedExample2.test2();
        });
        executorService.shutdown();
    }

}
