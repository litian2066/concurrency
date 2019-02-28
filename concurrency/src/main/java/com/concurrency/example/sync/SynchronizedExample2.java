package com.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示synchronized关键字
 * 子类继承该类是方法是带不出sysn关键字的 sysn是不属于方法声明的一部分
 */
@Slf4j
public class SynchronizedExample2 {

    // 修饰一个类 作用于所有对象
    private static void test1(int j) {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1: {} - {}",j, i);
            }
        }
    }

    // 修饰静态方法 作用于所有对象
    private static synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2: {} - {}",j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 synchronizedExample1 = new SynchronizedExample2();
//        SynchronizedExample2 synchronizedExample2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test1(1);
        });
        executorService.execute(() -> {
            synchronizedExample1.test2(2);
        });
        executorService.shutdown();
    }

}
