package com.concurrency.example.atomicExample;

import com.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Atomic包的演示4
 * 原子性保证只执行了一次
 */
@Slf4j
@ThreadSafe
public class ConcurrencyAtomicExample5 {

    private static AtomicBoolean isHappen = new AtomicBoolean(false);

    // 请求总数
    private static Integer clientTotal = 5000;

    // 同时并发数
    private static Integer threadTotal = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量 允许并发数
        Semaphore semaphore = new Semaphore(threadTotal);
        // 创建闭锁
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        // 执行操作
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire(); // 允许线程执行
                    add();
                    semaphore.release(); // 释放线程
                } catch (Exception e) {
                    log.error("exception: ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("isHappen: {}", isHappen.get());
    }

    private  static void add() {
        if (isHappen.compareAndSet(false, true)) {
            log.info("execute");
        }
    }
}